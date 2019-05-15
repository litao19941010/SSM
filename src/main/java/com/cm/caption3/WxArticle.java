package com.cm.caption3;

/**
 * 图文model
 */
public class WxArticle {

    public WxArticle(){}
    //图文消息名称
    private String title;
    //图文消息描述
    private String description;
    //图片链接，支持JPG，PNG格式，较好的效果为大图640*320，小图80*80
    private String picurl;
   //点击图文消息跳转链接
    private String url;

    public String getPicurl() {
        return picurl;
    }

    public void setPicurl(String picurl) {
        this.picurl = picurl;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public WxArticle(String title, String description, String url,String picurl) {
        super();
        this.title = title;
        this.description = description;
        this.url = url;
        this.picurl = picurl;
    }
}
