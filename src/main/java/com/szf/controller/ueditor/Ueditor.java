package com.szf.controller.ueditor;

public class Ueditor {

    /**
     * 状态：
     *      成功：SUCCESS;
     **/
    private String state;
    /**
     * 图片的url，前端通过该url获取图片并显示
     **/
    private String url;
    private String title;
    private String original;

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getOriginal() {
        return original;
    }

    public void setOriginal(String original) {
        this.original = original;
    }
}
