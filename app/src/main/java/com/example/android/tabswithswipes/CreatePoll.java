package com.example.android.tabswithswipes;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;

public class CreatePoll extends AppCompatActivity {

    private static ImageView closeShare, addImage, loadedImage;
    private static TextView post;
    private final int PICK_IMAGE_REQUEST = 71;
    private Uri filePath;
    private FrameLayout imageFrameLayout;
    private EditText editCaption;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_poll);
        closeShare = (ImageView)findViewById(R.id.close_share);
        addImage = (ImageView)findViewById(R.id.add_image);
        loadedImage = (ImageView)findViewById(R.id.loaded_image);
        post = (TextView)findViewById(R.id.share_poll);
        imageFrameLayout = (FrameLayout)findViewById(R.id.frame_image_layout);
        editCaption = (EditText)findViewById(R.id.editCaption);

        closeShare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        addImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.setType("image/*");
                intent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(Intent.createChooser(intent, "Select Picture"), PICK_IMAGE_REQUEST);
                imageFrameLayout.setBackgroundColor(Color.WHITE);
            }
        });
        post.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(CreatePoll.this, "Posting the poll", Toast.LENGTH_SHORT).show();
            }
        });
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK
                && data != null && data.getData() != null )
        {
            filePath = data.getData();
            try {
                Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), filePath);
                loadedImage.setImageBitmap(bitmap);
            }
            catch (IOException e)
            {
                e.printStackTrace();
            }
        }
    }
}
