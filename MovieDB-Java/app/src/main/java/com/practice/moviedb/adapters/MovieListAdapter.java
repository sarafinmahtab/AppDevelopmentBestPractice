package com.practice.moviedb.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.practice.moviedb.R;
import com.practice.moviedb.activities.MovieActivity;
import com.practice.moviedb.models.Result;
import com.practice.moviedb.models.TopRatedMovie;

import java.util.ArrayList;
import java.util.List;

public class MovieListAdapter extends RecyclerView.Adapter<MovieListAdapter.MovieListViewHolder> {

    private Context context;
    private TopRatedMovie topRatedMovie;
    private List<Result> movieList;

    public MovieListAdapter(Context context) {
        this.context = context;
    }

    @NonNull
    @Override
    public MovieListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context).inflate(R.layout.layout_movie_item, parent, false);

        return new MovieListViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MovieListViewHolder holder, int position) {
        final Result result = movieList.get(position);

        holder.movieTitleTextView.setText(result.getTitle());
        holder.releasedDateTextView.setText(String.format("Released Date : %s", result.getReleaseDate()));

        Glide.with(context)
                .load("https://image.tmdb.org/t/p/w600_and_h900_bestv2" + result.getPosterPath())
                .placeholder(R.drawable.ic_movie_poster)
                .into(holder.moviePosterImageView);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, MovieActivity.class);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return movieList.size();
    }

    class MovieListViewHolder extends RecyclerView.ViewHolder {

        private ImageView moviePosterImageView;

        private TextView movieTitleTextView;
        private TextView releasedDateTextView;

        MovieListViewHolder(@NonNull View itemView) {
            super(itemView);

            moviePosterImageView = itemView.findViewById(R.id.movie_poster);

            movieTitleTextView = itemView.findViewById(R.id.movie_title);
            releasedDateTextView = itemView.findViewById(R.id.movie_released);
        }
    }

    public void setTopRatedMovie(TopRatedMovie topRatedMovie) {
        this.topRatedMovie = topRatedMovie;

        if (topRatedMovie.getResults() == null) {
            this.movieList = new ArrayList<>();
        } else {
            this.movieList = topRatedMovie.getResults();
        }
    }
}
