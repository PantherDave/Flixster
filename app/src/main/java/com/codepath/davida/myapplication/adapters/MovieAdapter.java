package com.codepath.davida.myapplication.adapters;

import android.content.Context;
import android.content.res.Configuration;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.codepath.davida.myapplication.model.Movie;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.codepath.davida.myapplication.R;

import java.util.List;

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.ViewHolder>{
    Context context;
    List<Movie> movies;

    public MovieAdapter(Context context, List<Movie> movies) {
        this.context = context;
        this.movies = movies;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Log.d("MovieAdapter", "onCreateViewHolder");
        View movieView = LayoutInflater.from(context).inflate(R.layout.item_movie, parent, false);
        return new ViewHolder(movieView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Log.d("MovieAdapter", "onBindViewHolder " + position);
        // Get movie at position
        Movie movie  = movies.get(position);
        // Bind movie data into VH
        holder.bind(movie);
    }

    @Override
    public int getItemCount() {
        return this.movies.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvTitle;
        TextView tvOverview;
        ImageView ivPoster;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            this.tvTitle = itemView.findViewById(R.id.tvTitle) ;
            this.tvOverview = itemView.findViewById(R.id.tvOverview) ;
            this.ivPoster = itemView.findViewById(R.id.ivPoster);
        }

        public void bind(Movie movie) {
            this.tvTitle.setText(movie.getTitle());
            this.tvOverview.setText(movie.getOverview());
            String imageUrl;
            // if phone in landscape
            // then imageUrl = backdrop
            // else imageUrl = poster
            if (context.getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE)
                imageUrl = movie.getBackdropPath();
            else
                imageUrl = movie.getPosterPath();

            Glide.with(context).load(imageUrl).into(ivPoster);
        }
    }
}
