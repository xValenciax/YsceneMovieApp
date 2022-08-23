package com.example.ycseneapplication;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.List;

public class MovieViewAdapter extends RecyclerView.Adapter<MovieViewAdapter.MovieViewViewHolder> {

    Context context;
    List<String> mMoviesTitle;
    List<Photo> mMoviesPhotos;

    public MovieViewAdapter(Context context, List<String> mMoviesTitle, List<Photo> mMoviesPhotos) {
        this.context = context;
        this.mMoviesTitle = mMoviesTitle;
        this.mMoviesPhotos = mMoviesPhotos;
    }

    @NonNull
    @Override
    public MovieViewViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater layoutInflater = LayoutInflater.from(context);

        View MovieView = layoutInflater.inflate(R.layout.recycler_item, parent, false);
        MovieViewViewHolder viewHolder = new MovieViewViewHolder(MovieView);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MovieViewViewHolder holder, int position) {
        String currentTitle = mMoviesTitle.get(position);
        Photo currentPoster = mMoviesPhotos.get(position);

        holder.title.setText(currentTitle);
        Glide.with(context).load(currentPoster.getResourceUrl()).into(holder.poster);

    }

    @Override
    public int getItemCount() {
        return mMoviesTitle.size();
    }

    public class MovieViewViewHolder extends  RecyclerView.ViewHolder{
        TextView title;
        ImageView poster;

        public MovieViewViewHolder(@NonNull View itemView) {
            super(itemView);
            poster = itemView.findViewById(R.id.moviePoster);
            title = itemView.findViewById(R.id.movieTitle);
        }

    }
}
