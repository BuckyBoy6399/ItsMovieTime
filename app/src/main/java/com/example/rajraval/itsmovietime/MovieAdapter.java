package com.example.rajraval.itsmovietime;

import android.content.Context;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.rajraval.itsmovietime.MovieModel;
import com.example.rajraval.itsmovietime.R;

import java.util.List;

public class MovieAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    Context context;
    LayoutInflater inflater;
    List<MovieModel> data;

    public MovieAdapter(Context context, List<MovieModel> data) {
        this.context = context;
        this.data = data;
        this.inflater=LayoutInflater.from(context);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = inflater.inflate(R.layout.item_movie,parent,false);
        MyHolder mv= new MyHolder(v);
        return mv;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        MovieModel mm = data.get(position);
        MyHolder myHolder = (MyHolder) holder;
        myHolder.movie_name.setText(mm.getName());
        Glide.with(context).load(mm.getImage()).into(myHolder.movie_image);
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public static class MyHolder extends RecyclerView.ViewHolder{
        TextView movie_name;
        ImageView movie_image;

        public MyHolder(View view){
            super(view);
            movie_name=view.findViewById(R.id.dmovies);
            movie_image=view.findViewById(R.id.gl);
        }
    }
}
