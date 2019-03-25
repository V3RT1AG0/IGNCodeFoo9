package com.example.v3rt1ag0.ign;

/**
 * Created by v3rt1ag0 on 3/24/19.
 */

public class Information
{
    private String content, title, date, thumbnail,id;

    public Information(String title, String content, String date, String thumbnail,String id)
    {
        this.content = content;
        this.title = title;
        this.date = date;
        this.thumbnail = thumbnail;
        this.id = id;
    }

    public String getContent()
    {
        return content;
    }

    public String getDate()
    {
        return date;
    }

    public String getId()
    {
        return id;
    }

    public String getTitle()
    {
        return title;
    }

    public String getThumbnail()
    {
        return thumbnail;
    }
}

