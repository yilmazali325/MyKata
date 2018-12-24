package com.aliyilmaz.kata.kata.models;

import com.google.gson.annotations.SerializedName;


public class JSONModel {
    @SerializedName("content")
    private Content[] content;
    @SerializedName("title")
    private String title;
    @SerializedName("bottomDescription")
    private String bottomDescription;
    @SerializedName("promoMessage")
    private String promoMessage;
    @SerializedName("backgroundImage")
    private String backgroundImage;
    @SerializedName("topDescription")
    private String topDescription;

    public Content[] getContent ()
    {
        return content;
    }

    public void setContent (Content[] content)
    {
        this.content = content;
    }

    public String getTitle ()
    {
        return title;
    }

    public void setTitle (String title)
    {
        this.title = title;
    }

    public String getBottomDescription ()
    {
        return bottomDescription;
    }

    public void setBottomDescription (String bottomDescription)
    {
        this.bottomDescription = bottomDescription;
    }

    public String getPromoMessage ()
    {
        return promoMessage;
    }

    public void setPromoMessage (String promoMessage)
    {
        this.promoMessage = promoMessage;
    }

    public String getBackgroundImage ()
    {
        return backgroundImage;
    }

    public void setBackgroundImage (String backgroundImage)
    {
        this.backgroundImage = backgroundImage;
    }

    public String getTopDescription ()
    {
        return topDescription;
    }

    public void setTopDescription (String topDescription)
    {
        this.topDescription = topDescription;
    }

    @Override
    public String toString()
    {
        return "Class JSONModel [content = "+content+", title = "+title+", bottomDescription = "+bottomDescription+", promoMessage = "+promoMessage+", backgroundImage = "+backgroundImage+", topDescription = "+topDescription+"]";
    }
}