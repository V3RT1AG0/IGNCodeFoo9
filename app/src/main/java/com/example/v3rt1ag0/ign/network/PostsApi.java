package com.example.v3rt1ag0.ign.network;

import com.example.v3rt1ag0.ign.models.Article;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by v3rt1ag0 on 3/24/19.
 */

public interface PostsApi
{
    @GET("/content")
    Call<Article> getArticles(@Query("startIndex") int start_index);
}
