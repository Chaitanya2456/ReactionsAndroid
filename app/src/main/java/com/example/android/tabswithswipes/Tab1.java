package com.example.android.tabswithswipes;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import com.example.android.tabswithswipes.Utils.UniversalImageLoader;
import com.nostra13.universalimageloader.core.ImageLoader;

/**
 * Created by Chaitanya Shiva on 08-05-2018.
 */

public class Tab1 extends Fragment{
    ImageView tab1Image;
    @Nullable
    @Override

    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.tab1, container, false);
        Button uploadButton = (Button)v.findViewById(R.id.uploadBtn);
        tab1Image = (ImageView)v.findViewById(R.id.tab1ImageView);
        uploadButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            startActivity(new Intent(getActivity(), GalleryFragment.class));
            }
        });
        return v;
    }
}
