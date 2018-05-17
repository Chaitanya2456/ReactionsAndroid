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
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.android.tabswithswipes.Utils.CircleProgressBarDrawable;
import com.facebook.drawee.view.SimpleDraweeView;

import java.io.File;

/**
 * Created by Chaitanya Shiva on 08-05-2018.
 */

public class Tab2 extends Fragment{
    SimpleDraweeView draweeView;
    Button uploadBtnTab2;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.tab2, container, false);
        draweeView = (SimpleDraweeView)v.findViewById(R.id.tab2DraweeView);
        uploadBtnTab2 = (Button)v.findViewById(R.id.uploadBtnTab2);
        uploadBtnTab2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(checkWriteExternalPermission()){
                        Intent intent = new Intent(getActivity().getBaseContext(), GalleryFragment.class);
                        intent.putExtra("SenderKeyTab2", "requestFromTab2");
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
        if(preferences.contains("imgUriTab2")){
            data = preferences.getString("imgUriTab2", null);
            Uri imageUri = Uri.fromFile(new File(data));
            draweeView.getHierarchy().setProgressBarImage(new CircleProgressBarDrawable());
            draweeView.setImageURI(imageUri);
        }
    }
}
