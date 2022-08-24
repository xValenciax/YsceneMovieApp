package com.example.ycseneapplication;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

public class MovieViewAdapter extends BaseAdapter {

    private Context context;
    private List<String> mMoviesTitle;
    private List<Photo> mMoviesPhotos;

    private LayoutInflater inflater;

    public MovieViewAdapter(Context context, List<String> mMoviesTitle, List<Photo> mMoviesPhotos) {
        this.context = context;
        this.mMoviesTitle = mMoviesTitle;
        this.mMoviesPhotos = mMoviesPhotos;
    }

    @Override
    public int getCount() {
        if(mMoviesTitle != null)
            return mMoviesTitle.size();
        return 0;
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        if(inflater == null)
            inflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
        if(view == null)
            view =  inflater.inflate(R.layout.grid_item, null);

            ImageView poster = (ImageView) view.findViewById(R.id.Poster);
            TextView title = (TextView) view.findViewById(R.id.moviename);

            String currentTitle = mMoviesTitle.get(i);
            Photo currentPoster = mMoviesPhotos.get(i);

            title.setText(currentTitle);
            Glide.with(context).load(currentPoster.getResourceUrl()).into(poster);
        return view;

    }
}















//RecyclerView.Adapter<MovieViewAdapter.MovieViewViewHolder>@NonNull
////    @Override
////    public MovieViewViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
////        Context context = parent.getContext();
////        LayoutInflater layoutInflater = LayoutInflater.from(context);
////
////        View MovieView = layoutInflater.inflate(R.layout.recycler_item, parent, false);
////        MovieViewViewHolder viewHolder = new MovieViewViewHolder(MovieView);
////
////        return viewHolder;
////    }
////
////    @Override
////    public void onBindViewHolder(@NonNull MovieViewViewHolder holder, int position) {
////        String currentTitle = mMoviesTitle.get(position);
////        Photo currentPoster = mMoviesPhotos.get(position);
////
////        holder.title.setText(currentTitle);
////        Glide.with(context).load(currentPoster.getResourceUrl()).into(holder.poster);
////
////    }
////
////    @Override
////    public int getItemCount() {
////        if(mMoviesTitle != null)
////            return mMoviesTitle.size();
////        return 0;
////    }
