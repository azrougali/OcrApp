package com.example.ocrapp;

import android.annotation.SuppressLint;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import com.websitebeaver.documentscanner.DocumentScanner;

public class camera_act extends AppCompatActivity {

    private ImageView croppedImageView;


    DocumentScanner documentScanner = new DocumentScanner(
            this,
            (croppedImageResults) -> {
                // display the first cropped image
                croppedImageView.setImageBitmap(
                        BitmapFactory.decodeFile(croppedImageResults.get(0))
                );
                return null;
            },
            (errorMessage) -> {
                // an error happened
                Log.v("documentscannerlogs", errorMessage);
                return null;
            },
            () -> {
                // user canceled document scan
                Log.v("documentscannerlogs", "User canceled document scan");
                return null;
            },
            null,
            null,
            null
    );

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_camera);

        croppedImageView = findViewById(R.id.cropped_image_view);

        // start document scan
        documentScanner.startScan();
    }
}
