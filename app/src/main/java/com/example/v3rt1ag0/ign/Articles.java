package com.example.v3rt1ag0.ign;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.example.v3rt1ag0.ign.models.*;
import com.example.v3rt1ag0.ign.network.PostsApi;
import com.example.v3rt1ag0.ign.network.RetrofitInstance;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

/**
 * Created by v3rt1ag0 on 3/24/19.
 */

public class Articles extends Fragment
{

    RecyclerAdapter myAdapter;
    RecyclerView recyclerView;
    List<Information> info;
    Boolean nexttexnupdate = false;
    int curSize = 0;

    public Articles()
    {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        View rootView = inflater.inflate(R.layout.articles_fragment, container, false);
        info = new ArrayList<>();
        return rootView;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState)
    {
        super.onActivityCreated(savedInstanceState);
        setRecyclerView();
    }


    private void setRecyclerView()
    {

        LinearLayoutManager lm  = new LinearLayoutManager(getActivity());
        recyclerView = getActivity().findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(lm);
        fetch(0);
        recyclerView.addOnScrollListener(new EndlessRecyclerViewScrollListener(lm)
        {
            @Override
            public void onLoadMore(int page, int totalItemsCount)
            {
                curSize = myAdapter.getItemCount();
                nexttexnupdate = true;
                fetch(page * 10);
            }
        });


    }

    private void fetch(int index)
    {

        Retrofit retrofit = RetrofitInstance.getRetrofitInstance();
        PostsApi articleAPI = retrofit.create(PostsApi.class);
        Call<Article> call = articleAPI.getArticles(index);
        call.enqueue(new Callback<Article>()
        {
            @Override
            public void onResponse(Call<Article> call, Response<Article> response)
            {
                if (response.isSuccessful())
                {
                    Article article = response.body();
                    //String id = article.getData().get(0);

                    for (int i = 0; i < response.body().getData().size(); i++)
                    {
                        Article.Info info2 = article.getData().get(i);
                        if (info2.getContentType().equals("article"))
                            info.add(new Information(info2.getMetaData().getTitle(),
                                    info2.getMetaData().getContent(),
                                    info2.getMetaData().getDate(),
                                    info2.getThumbnails()[0].getUrl(),
                                    info2.getId()));

                        //  Log.d("Response", String.valueOf(article.getData().get(i).getMetaData().getTitle()));
                        //Log.d("Response", String.valueOf(article.getData().get(i).getMetaData().getVideoTitle()));
                    }
                    if (nexttexnupdate)   //add 10 more data
                    {
                        myAdapter.notifyItemRangeInserted(curSize, info.size() - 1);
                        nexttexnupdate = false;
                    } else
                    {
                        myAdapter = new RecyclerAdapter(info);
                        recyclerView.setAdapter(myAdapter);
                    }

                } else
                {
                    Log.d("Response", "failure");
                }

            }

            @Override
            public void onFailure(Call<Article> call, Throwable t)
            {
                Log.d("Error", "Reached");
            }
        });
    }
}

