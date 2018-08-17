package com.travis.movie.adapter;

import android.app.Activity;
import android.app.ActivityOptions;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Pair;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.squareup.picasso.Picasso;
import com.travis.movie.R;
import com.travis.movie.activity.MovieDetail;
import com.travis.movie.model.Movie;

import java.util.List;

/**
 * Created by LENOVO on 21-07-2018.
 */

public class MovieListAdapter extends RecyclerView.Adapter<MovieListAdapter.MyViewHolder> {
    List<Movie> movieList;
    Activity mContext;

    public MovieListAdapter(Activity mContext, List<Movie> movieList) {
        this.movieList = movieList;
        this.mContext = mContext;
    }

    @NonNull
    @Override
    public MovieListAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_movie_list_adapter, parent, false);
        return new MovieListAdapter.MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final MovieListAdapter.MyViewHolder holder, int position) {
        final Movie movie = movieList.get(position);
        holder.title.setText(movie.getTitle());

        Picasso.get().load(movie.getImg()).placeholder(R.drawable.black_gradient).into(holder.image);
        holder.view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Activity activityContext = mContext;
                final ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(
                        activityContext,
                        Pair.create((View) holder.image, holder.image.getTransitionName()));
                Intent intent = new Intent(activityContext, MovieDetail.class);
                intent.putExtra("id", movie.getId());
                mContext.startActivity(intent
                        .putExtra("shared_element_transition_name", view.getTransitionName()), options.toBundle());
            }
        });
    }

    @Override
    public int getItemCount() {
        return movieList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView title, release;
        ImageView image;
        RelativeLayout view;

        public MyViewHolder(View itemView) {
            super(itemView);
            view = itemView.findViewById(R.id.view);
            title = itemView.findViewById(R.id.title);
            image = itemView.findViewById(R.id.pic);
            release = itemView.findViewById(R.id.release);

        }
    }
}
