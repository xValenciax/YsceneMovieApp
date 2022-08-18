package com.example.ycseneapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;

import com.smarteist.autoimageslider.IndicatorView.animation.type.IndicatorAnimationType;
import com.smarteist.autoimageslider.SliderAnimations;
import com.smarteist.autoimageslider.SliderView;

import java.util.ArrayList;
import java.util.List;

import me.relex.circleindicator.CircleIndicator;

public class HomePageActivity extends AppCompatActivity {

    SliderView sliderView;
    SliderView sliderView2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);


        sliderView = (SliderView) findViewById(R.id.image_slider);
        sliderView2 = (SliderView) findViewById(R.id.image_slider2);

        SliderAdapter sliderAdapter = new SliderAdapter(this, getTopRatedListPhoto());
        sliderView.setSliderAdapter(sliderAdapter);
        sliderView.setIndicatorAnimation(IndicatorAnimationType.DROP);
        sliderView.setSliderTransformAnimation(SliderAnimations.DEPTHTRANSFORMATION);
        sliderView.startAutoCycle();
        SliderAdapter sliderAdapter2 = new SliderAdapter(this, getPopularListPhoto());
        sliderView2.setSliderAdapter(sliderAdapter2);
        sliderView2.setIndicatorAnimation(IndicatorAnimationType.DROP);
        sliderView2.setSliderTransformAnimation(SliderAnimations.DEPTHTRANSFORMATION);
        sliderView2.startAutoCycle();

    }

    public List<Photo> getTopRatedListPhoto(){
        List<Photo> list = new ArrayList<Photo>();

        list.add(new Photo("http://image.tmdb.org/t/p/w500/q6y0Go1tsGEsmtFryDOJo3dEmqu.jpg"));
        list.add(new Photo("http://image.tmdb.org/t/p/w500/3bhkrj58Vtu7enYsRolD1fZdja1.jpg"));
        list.add(new Photo("http://image.tmdb.org/t/p/w500/sF1U4EUQS8YHUYjNl3pMGNIQyr0.jpg"));
        list.add(new Photo("http://image.tmdb.org/t/p/w500/hek3koDUyRQk7FIhPXsa6mT2Zc3.jpg"));

        return list;
    }

    public List<Photo> getPopularListPhoto(){
        List<Photo> list = new ArrayList<Photo>();

        list.add(new Photo("http://image.tmdb.org/t/p/w500/ujr5pztc1oitbe7ViMUOilFaJ7s.jpg"));
        list.add(new Photo("http://image.tmdb.org/t/p/w500/pIkRyD18kl4FhoCNQuWxWu5cBLM.jpg"));
        list.add(new Photo("http://image.tmdb.org/t/p/w500/wKiOkZTN9lUUUNZLmtnwubZYONg.jpg"));
        list.add(new Photo("http://image.tmdb.org/t/p/w500/kAVRgw7GgK1CfYEJq8ME6EvRIgU.jpg"));

        return list;
    }
}