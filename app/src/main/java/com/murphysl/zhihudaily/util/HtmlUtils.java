package com.murphysl.zhihudaily.util;

import java.util.List;

/**
 * HtmlUtils
 *
 * @author: MurphySL
 * @time: 2017/2/3 14:21
 */


public class HtmlUtils {

    private static final String HIDE_HEADER_STYLE = "<style>div.headline{display:none;}</style>";

    private static final String CSS_TAG = "<link rel=\"stylesheet\" type=\"text/css\" href=\"%s\"/>";

    public static final String MIME_TYPE = "text/html; charset=utf-8";

    public static final String ENCODING = "utf-8";

    private static String createCssTag(List<String> urls) {
        final StringBuilder sb = new StringBuilder();
        for (String url : urls) {
            sb.append(url);
        }

        return String.format(CSS_TAG, sb);
    }

    public static String createHtmlData(String html, List<String> cssList) {
        final String css = HtmlUtils.createCssTag(cssList);
        return css+HIDE_HEADER_STYLE+html;
    }
}
