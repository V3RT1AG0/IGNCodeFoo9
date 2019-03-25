package com.example.v3rt1ag0.ign.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by v3rt1ag0 on 3/24/19.
 */

public class Article
{
    @SerializedName("data")
    @Expose
    private List<Info> data = new ArrayList<>();

    public List<Info> getData()
    {
        return data;
    }

    public void setData(List<Info> data)
    {
        this.data = data;
    }

    public static class Info
    {

        @SerializedName("contentId")
        @Expose
        private String id;

        @SerializedName("contentType")
        @Expose
        private String contentType;

        @SerializedName("metadata")
        @Expose
        private metaData metaData;

        @SerializedName("thumbnails")
        @Expose
        private Thumbnail[] thumbnails;

        public String getId()
        {
            return id;
        }

        public void setId(String id)
        {
            this.id = id;
        }

        public String getContentType()
        {
            return contentType;
        }

        public metaData getMetaData()
        {
            return metaData;
        }

        public Thumbnail[] getThumbnails()
        {
            return thumbnails;
        }

        public static class Thumbnail
        {
            @SerializedName("url")
            @Expose
            private String url;

            public String getUrl()
            {
                return url;
            }
        }

        public static class metaData
        {
            @SerializedName("description")
            @Expose
            private String content;
            @SerializedName("headline")
            @Expose
            private String title;
            @SerializedName("title")
            @Expose
            private String videoTitle;
            @SerializedName("publishDate")
            @Expose
            private String date;


            public String getContent()
            {
                return content;
            }

            public String getVideoTitle()
            {
                return videoTitle;
            }

            public String getDate()
            {
                return date;
            }

            public String getTitle()
            {
                return title;
            }
        }
    }


}
