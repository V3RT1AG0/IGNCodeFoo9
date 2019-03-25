package com.example.v3rt1ag0.ign;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;


public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.MyViewHolder>
{
    private List<Information> info;
    private Context context;
    private static LayoutInflater inflater = null;

    RecyclerAdapter(List<Information> info)
    {
        this.info = info;
        //context= MyApplication.getContext();
    }


    @Override
    public RecyclerAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i)
    {
        View v = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.article_card, viewGroup, false);
        MyViewHolder pvh = new MyViewHolder(v);
        return pvh;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerAdapter.MyViewHolder myViewHolder, int i)
    {
        Information in=info.get(i);
        myViewHolder.title.setText(in.getTitle());
        myViewHolder.description.setText(in.getContent());
        Picasso.get()
                .load(in.getThumbnail())
                .into(myViewHolder.poster);
    }

    @Override
    public long getItemId(int position)
    {
        return position;
    }

    @Override
    public int getItemCount()
    {
        return info.size();
    }


    class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener
    {
        TextView title, description, time;
        ImageView poster;

        public MyViewHolder(View itemView)
        {
            super(itemView);
            title = itemView.findViewById(R.id.article_title);
            description = itemView.findViewById(R.id.article_description);
            time = itemView.findViewById(R.id.date);
            poster = itemView.findViewById(R.id.image);
        }

        @Override
        public void onClick(View view)
        {
            switch (view.getId())
            {
            }
        }
    }


}

