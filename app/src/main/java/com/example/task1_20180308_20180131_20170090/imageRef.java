package com.example.task1_20180308_20180131_20170090;

public class imageRef {
    private String imageUrl;
    public imageRef(){

    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public imageRef(String url)
    {
        this.imageUrl=url;
    }
}
