package com.excellence.gstcalculator;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.daimajia.slider.library.SliderLayout;
import com.daimajia.slider.library.SliderTypes.BaseSliderView;
import com.daimajia.slider.library.SliderTypes.TextSliderView;
import com.daimajia.slider.library.Tricks.ViewPagerEx;

import java.util.HashMap;

public class TesttingActivity extends AppCompatActivity implements BaseSliderView.OnSliderClickListener, ViewPagerEx.OnPageChangeListener {

    SliderLayout mDemoSlider;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.test);


        mDemoSlider=new SliderLayout(getApplicationContext());
        //for slider
        HashMap<String, Integer> file_maps = new HashMap<String, Integer>();
//        file_maps.put("Aim",R.drawable.add_first);
//        file_maps.put("Bizwinger",R.drawable.add_bizbanner);
        file_maps.put("Resto Captain",R.mipmap.ic_launcher);
//        file_maps.put("Cable Care",R.drawable.cablecare);
        for (String name : file_maps.keySet()) {
            TextSliderView textSliderView = new TextSliderView(getApplicationContext());
            textSliderView
                    .description(name)
                    .image(file_maps.get(name))
                    .setScaleType(BaseSliderView.ScaleType.Fit) ;

            mDemoSlider.addSlider(textSliderView);
            mDemoSlider.setDuration(4000);
            mDemoSlider.addOnPageChangeListener(TesttingActivity.this);
        }
    }

    @Override
    public void onSliderClick(BaseSliderView slider) {

    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {

    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }
}
