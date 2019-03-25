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

import com.example.v3rt1ag0.ign.models.Article;
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

public class Videos extends Fragment
{
    RecyclerAdapter myAdapter;
    RecyclerView recyclerView2;
    List<Information> info2;
    Boolean nexttexnupdate = false;
    int curSize2 = 0;

    public Videos()
    {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        View rootView = inflater.inflate(R.layout.video_fragment, container, false);
        info2 = new ArrayList<>();
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
        recyclerView2 = getActivity().findViewById(R.id.recyclerView2);
        recyclerView2.setHasFixedSize(true);
        recyclerView2.setLayoutManager(new LinearLayoutManager(getActivity()));
        fetch(0);
        recyclerView2.addOnScrollListener(new EndlessRecyclerViewScrollListener(lm)
        {
            @Override
            public void onLoadMore(int page, int totalItemsCount)
            {
                curSize2 = myAdapter.getItemCount();
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
                        Article.Info info3 = article.getData().get(i);
                        if (info3.getContentType().equals("video"))
                            info2.add(new Information(info3.getMetaData().getTitle(),
                                    info3.getMetaData().getContent(),
                                    info3.getMetaData().getDate(),
                                    info3.getThumbnails()[0].getUrl(),
                                    info3.getId()));


                    }
                    if (nexttexnupdate)   //add 10 more data
                    {
                        myAdapter.notifyItemRangeInserted(curSize2, info2.size() - 1);
                        nexttexnupdate = false;
                    } else
                    {
                        myAdapter = new RecyclerAdapter(info2);
                        recyclerView2.setAdapter(myAdapter);
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
