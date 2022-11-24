package com.example.nguoidung.Fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.denzcoskun.imageslider.ImageSlider;
import com.denzcoskun.imageslider.constants.ScaleTypes;
import com.denzcoskun.imageslider.models.SlideModel;
import com.example.nguoidung.R;

import java.util.ArrayList;


public class fragmentTrangChu extends Fragment {
    ImageSlider imageSlider;
    View view;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view =  inflater.inflate(R.layout.fragment_trang_chu, container, false);
        imageSlider = view.findViewById(R.id.fragment_home_img_slide);

        ArrayList<SlideModel> images = new ArrayList<>();
        images.add(new SlideModel(R.drawable.a1, ScaleTypes.CENTER_CROP));
        images.add(new SlideModel(R.drawable.a2,ScaleTypes.CENTER_CROP));
        images.add(new SlideModel(R.drawable.a3,ScaleTypes.CENTER_CROP));
        images.add(new SlideModel(R.drawable.a4,ScaleTypes.CENTER_CROP));

        imageSlider.setImageList(images);



        return view;
    }
}