package com.example.ycseneapplication;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import com.bumptech.glide.Glide;
import java.util.List;
import com.smarteist.autoimageslider.SliderViewAdapter;

public class SliderAdapter extends SliderViewAdapter<SliderAdapter.Holder> {

    List<Photo> imgs;
    Context context;

    public SliderAdapter(Context context, List<Photo> imgs){
        this.context = context;
        this.imgs = imgs;
    }

    @Override
    public Holder onCreateViewHolder(ViewGroup parent) {

        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.slider_item, parent, false);
        return new Holder(view);
    }

    @Override
    public void onBindViewHolder(Holder viewHolder, int position) {
        Glide.with(context).load(imgs.get(position).getResourceUrl()).into(viewHolder.imageView);
    }

    @Override
    public int getCount() {
        return imgs.size();
    }

    public class Holder extends SliderViewAdapter.ViewHolder{

        ImageView imageView;

        public Holder(View viewItem){
            super(viewItem);
            imageView = viewItem.findViewById(R.id.image_view);
        }

    }
}














//
//import androidx.annotation.NonNull;
//import androidx.cardview.widget.CardView;
//import androidx.viewpager.widget.PagerAdapter;
//import java.util.PrimitiveIterator;
//
//public class SliderAdapter extends PagerAdapter {
//
//    private Context context;
//    private List<Photo> mListphotos;
//
//    public SliderAdapter(Context context, List<Photo> mListphotos) {
//        this.context = context;
//        this.mListphotos = mListphotos;
//    }
//
//    @NonNull
//    @Override
//    public Object instantiateItem(@NonNull ViewGroup container, int position) {
//        View view = LayoutInflater.from(container.getContext()).inflate(R.layout.slider_item, container, false);
//        ImageView img =  (ImageView) view.findViewById(R.id.image_view);
//
//        Photo photo = mListphotos.get(position);
//        if(photo != null){
//            Glide.with(context).load(photo.getResourceUrl()).into(img);
//        }
//        container.addView(view);
//
//        return view;
//    }
//
//    @Override
//    public int getCount() {
//        if(mListphotos != null)
//            return mListphotos.size();
//        return 0;
//    }
//
//    @Override
//    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
//        return view == object;
//    }
//
//    @Override
//    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
//        container.removeView((View) object);
//    }
//}
