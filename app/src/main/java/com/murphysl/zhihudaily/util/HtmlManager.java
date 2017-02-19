package com.murphysl.zhihudaily.util;


import android.content.Context;

import com.murphysl.zhihudaily.api.Network;
import com.murphysl.zhihudaily.config.Constants;
import com.orhanobut.logger.Logger;

import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import io.reactivex.BackpressureStrategy;
import io.reactivex.Flowable;
import io.reactivex.FlowableEmitter;
import io.reactivex.FlowableOnSubscribe;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Request;
import okhttp3.Response;

/**
 * HtmlManager
 *
 * 线程问题
 *
 * @author: MurphySL
 * @time: 2017/2/3 14:21
 */


public class HtmlManager {

    private static final String CHANGE_CSS_TAG = "<style type=\"text/css\">div.headline{display:none;} %s</style>";

    private static final String CSS_TAG = "<style>div.headline{display:none;}</style><link rel=\"stylesheet\" type=\"text/css\" href=\"%s\"/>";

    public static final String MIME_TYPE = "text/html; charset=utf-8";

    public static final String ENCODING = "utf-8";

    private HtmlDataChangeListener dataChangeListener;

    private Context context;

    public HtmlManager(Context context){
        this.context = context;
    }

    public interface HtmlDataChangeListener{
        void changed(String data);
    }

    public void setOnDataChangeListener(String html, String originCss , HtmlDataChangeListener dataChangeListener){
        this.dataChangeListener = dataChangeListener;
        SharedPreferencesUtils sp = new SharedPreferencesUtils(context);

        if(sp.getSuffix().equals(Constants.SKIN_SUFFIX))
            changeCss(originCss , html);
        else
            dataChangeListener.changed(String.format(CSS_TAG , originCss) + html);
    }

    private void changeCss(final String url , final String html){
        Request request = new Request.Builder().url(url).build();
        Network.getInstance().configClient()
                .newCall(request).enqueue(new Callback() {
                    @Override
                    public void onFailure(Call call, IOException e) {
                        Logger.w("HtmlManager" + e.toString());
                    }

                    @Override
                    public void onResponse(Call call, Response response) throws IOException {
                        String originCss = response.body().string();
                        String newCss = replaceBackground(originCss);
                        String css = String.format(CHANGE_CSS_TAG, newCss);
                        dataChangeListener.changed(css + html);
                    }
                });
       /* Flowable.create(new FlowableOnSubscribe<Response>() {
            @Override
            public void subscribe(FlowableEmitter<Response> e) throws Exception {
                final Request request = new Request.Builder().url(url).build();
                Response response = Network.getInstance().configClient().newCall(request).execute();
                e.onNext(response);
            }
        } , BackpressureStrategy.BUFFER)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .map(new Function<Response, String>() {
                    @Override
                    public String apply(Response response) throws Exception {
                        String originCss = response.body().string();
                        return replaceBackground(originCss);
                    }
                }).subscribe(
                new Consumer<String>() {
                    @Override
                    public void accept(String s) throws Exception {
                        String css = String.format(CHANGE_CSS_TAG, s);
                        dataChangeListener.changed(css + html);
                    }
                },
                new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        Logger.w("HtmlManager" + throwable.toString());
                    }
                });*/
    }

    public static String replaceBackground(String originCss){
        String regix = "background:[\\s\\S]+?(#\\w+?);";
        Pattern pattern = Pattern.compile(regix);
        Matcher matcher = pattern.matcher(originCss);
        String newCss = matcher.replaceAll("background:#343434;");

        Logger.i(newCss);
        return newCss;
    }

}
