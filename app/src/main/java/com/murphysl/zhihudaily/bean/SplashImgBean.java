package com.murphysl.zhihudaily.bean;

/**
 * SplashImgBean
 *
 * @author: MurphySL
 * @time: 2017/1/19 12:49
 */


public class SplashImgBean {

    /**
     * text : © Fido Dido
     * img : http://p2.zhimg.com/10/7b/107bb4894b46d75a892da6fa80ef504a.jpg
     */

    private String text;
    private String img;

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    @Override
    public String toString() {
        return "SplashImgBean{" +
                "text='" + text + '\'' +
                ", img='" + img + '\'' +
                '}';
    }
}
