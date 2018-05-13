package com.example.android.tabswithswipes;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Context;
import android.net.Uri;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.android.tabswithswipes.Utils.GridImageAdapter;
import com.example.android.tabswithswipes.Utils.UniversalImageLoader;
import com.facebook.drawee.view.SimpleDraweeView;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.ArrayList;

/**
 * Created by Chaitanya Shiva on 08-05-2018.
 */

public class GalleryFragment extends Activity {

    private GridView gridView;
    private ImageView galleryImage;
    private ProgressBar mProgressBar;
    private Spinner directorySpinner;
    private Context mContext = GalleryFragment.this;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_gallery);
        galleryImage = (SimpleDraweeView)findViewById(R.id.galleryImageView);
        gridView = (GridView)findViewById(R.id.gridView);
        directorySpinner = (Spinner)findViewById(R.id.spinnerDirectory);
        mProgressBar = (ProgressBar)findViewById(R.id.progressBar);
        mProgressBar.setVisibility(View.GONE);
        ImageView closeGallery = (ImageView)findViewById(R.id.closeGallery);
        closeGallery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        TextView nextScreen = (TextView) findViewById(R.id.tvNext);
        nextScreen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        Uri uri = Uri.parse("https://www.gstatic.com/webp/gallery/4.sm.jpg");
        galleryImage.setImageURI(uri);
        tempGridSetup();
    }
    private void setUpImageGrid(ArrayList<String> imgURLs){
        GridView gridView = (GridView)findViewById(R.id.gridView);
        GridImageAdapter adapter = new GridImageAdapter(mContext, R.layout.layout_grid_imageview,"", imgURLs);
        gridView.setAdapter(adapter);
    }
    private void tempGridSetup(){
        ArrayList<String> imgURLs = new ArrayList<>();
        imgURLs.add("https://www.gstatic.com/webp/gallery/4.sm.jpg");
        imgURLs.add("https://www.gstatic.com/webp/gallery3/1.sm.png");
        imgURLs.add("https://cdn.pixabay.com/photo/2018/05/10/09/07/bleeding-heart-3387085_1280.jpg");
        imgURLs.add("https://images.idgesg.net/images/article/2017/08/android_robot_logo_by_ornecolorada_cc0_via_pixabay1904852_wide-100732483-large.jpg");
        imgURLs.add("https://www.androidcentral.com/sites/androidcentral.com/files/styles/xlarge_wm_brw/public/article_images/2018/03/android-p-logo-pixel-2-xl-5.jpg?itok=DB93lUGS");
        imgURLs.add("https://www.androidcentral.com/sites/androidcentral.com/files/styles/xlarge_wm_brw/public/article_images/2018/03/android-p-virtual-notch.jpg?itok=EnKJzDgF");
        imgURLs.add("https://www.androidcentral.com/sites/androidcentral.com/files/styles/xlarge_wm_brw/public/article_images/2018/03/samsung-galaxy-s9-plus-black-4.jpg?itok=bTitZlS_");
        imgURLs.add("https://www.extremetech.com/wp-content/uploads/2017/03/smiling-android.jpg");

        setUpImageGrid(imgURLs);
    }
}