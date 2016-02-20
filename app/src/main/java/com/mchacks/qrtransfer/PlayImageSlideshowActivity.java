package com.mchacks.qrtransfer;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;

import com.google.zxing.WriterException;
import com.mchacks.qrtransfer.processing.QRProcessor;

public class PlayImageSlideshowActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play_image_slideshow);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    playSlideShow();
                } catch (WriterException e) {
                    e.printStackTrace();
                }
            }
        });
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    void playSlideShow() throws WriterException {
        Bitmap bmp = QRProcessor.generateQrCode("Hello Andrew!");
        ImageView iv = (ImageView) findViewById(R.id.imageView);
        iv.setImageBitmap(bmp);
    }

}
