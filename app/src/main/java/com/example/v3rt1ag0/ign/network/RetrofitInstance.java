package com.example.v3rt1ag0.ign.network;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by v3rt1ag0 on 3/24/19.
 */

public class RetrofitInstance
{
    private static Retrofit retrofit;
    private static final String BASE_URL = "https://ign-apis.herokuapp.com";

    public static Retrofit getRetrofitInstance()
    {
        if (retrofit == null)
        {
            retrofit = new retrofit2.Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }
}
