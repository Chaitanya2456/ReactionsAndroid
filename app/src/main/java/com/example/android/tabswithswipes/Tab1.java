package com.example.android.tabswithswipes;

import android.Manifest;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
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
import android.widget.TextView;

import com.example.android.tabswithswipes.Utils.CircleProgressBarDrawable;
import com.example.android.tabswithswipes.Utils.UniversalImageLoader;
import com.facebook.drawee.view.SimpleDraweeView;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.io.File;

/**
 * Created by Chaitanya Shiva on 08-05-2018.
 */

public class Tab1 extends Fragment{
    SimpleDraweeView draweeView;
    Button uploadButton;
    @Nullable
    @Override

    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.tab1, container, false);
        draweeView = (SimpleDraweeView)v.findViewById(R.id.tab1DraweeView);
        uploadButton = (Button)v.findViewById(R.id.uploadBtn);
        uploadButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(checkWriteExternalPermission()){
                        Intent intent = new Intent(getActivity().getBaseContext(), GalleryFragment.class);
                        intent.putExtra("SenderKeyTab1", "requestFromTab1");
                        getActivity().startActivity(intent);

                }
            else{       if(Build.VERSION.SDK_INT > Build.VERSION_CODES.M){
                    requestPermissions(new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1001);
                }
                }
            }
        });
        return v;
    }
    private boolean checkWriteExternalPermission(){
        String permission = Manifest.permission.WRITE_EXTERNAL_STORAGE;
        if(Build.VERSION.SDK_INT > Build.VERSION_CODES.M){
        int res = getContext().checkCallingOrSelfPermission(permission);
        return (res== PackageManager.PERMISSION_GRANTED);}
        else return true;
    }

    @Override
    public void onResume() {
        super.onResume();
        final SharedPreferences preferences = getActivity().getSharedPreferences("MyPref",0);
        String data;
        if(preferences.contains("imgUriTab1")){
            data = preferences.getString("imgUriTab1",null);
            Uri imageUri = Uri.fromFile(new File(data));
            draweeView.getHierarchy().setProgressBarImage(new CircleProgressBarDrawable());
            draweeView.setImageURI(imageUri);
            uploadButton.setText("Change");
        }
    }
}
