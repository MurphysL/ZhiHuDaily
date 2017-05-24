package com.murphysl.zhihudaily.data.bean;

import com.murphysl.zhihudaily.util.config.Constants;
import com.murphysl.zhihudaily.data.NewsBean;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * TimeBean
 *
 * @author: MurphySL
 * @time: 2017/2/4 19:40
 */


public class TimeBean implements NewsBean {

    private String time;

    private Date d;

    private String date;

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getDate() {
        if (time == null)
            return null;

        if(time.equals(Constants.LATEST_TIME))
            return time;

        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
            d = sdf.parse(time);
            SimpleDateFormat sdf2 = new SimpleDateFormat("MM月dd日 EEEE");
            date = sdf2.format(d);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return date;
    }
}
