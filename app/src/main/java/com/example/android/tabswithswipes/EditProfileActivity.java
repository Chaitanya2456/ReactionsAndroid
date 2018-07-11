package com.example.android.tabswithswipes;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.facebook.drawee.view.SimpleDraweeView;

public class EditProfileActivity extends AppCompatActivity {

    private static EditText editName, editBio;
    private static Button changeImage;
    private static ImageView saveChanges;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);
        editName = (EditText) findViewById(R.id.edit_name);
        editBio = (EditText)findViewById(R.id.edit_bio);
        changeImage = (Button) findViewById(R.id.change_profile_image);
        saveChanges = (ImageView) findViewById(R.id.saveChanges);
        final SharedPreferences preferences = getSharedPreferences("MyPref", 0);
        final SharedPreferences.Editor editor = preferences.edit();
        changeImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder alertDialog = new AlertDialog.Builder(EditProfileActivity.this);
                alertDialog.setTitle("IMAGE UPLOAD");
                alertDialog.setMessage("How do you want to upload?");
                alertDialog.setIcon(R.drawable.upload);
                alertDialog.setPositiveButton("CAMERA UPLOAD", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                });
                alertDialog.setNegativeButton("GALLERY UPLOAD", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                });
                alertDialog.show();
            }
        });

        saveChanges.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(editName.getText().toString()==null && editBio.getText().toString()==null){
                    finish();
                }
                if(editName.getText().toString()!=null&&editName.getText().toString()!=""&&editName.getText().toString().length()>0){
                    editor.putString("userName", editName.getText().toString().trim());
                }
                Log.d("msgbio", editBio.getText().toString());
                if(editBio.getText().toString()!=null&&editBio.getText().toString()!=""&&editBio.getText().toString().length()>0){
                    Log.d("msgbio", editBio.getText().toString());
                    editor.putString("userBio", editBio.getText().toString().trim());
                    Log.d("checkUser", "bio is null");
                }
                editor.commit();
                finish();
            }
        });
    }
}
