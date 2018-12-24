package com.aliyilmaz.kata.kata.models;

import com.google.gson.annotations.SerializedName;

public class Content {
    @SerializedName("title")
    private String title;
    @SerializedName("target")
    private String target;

    public String getTitle ()
    {
        return title;
    }

    public void setTitle (String title)
    {
        this.title = title;
    }

    public String getTarget ()
    {
        return target;
    }

    public void setTarget (String target)
    {
        this.target = target;
    }

    @Override
    public String toString()
    {
        return "Class Content [title = "+title+", target = "+target+"]";
    }
}

