package com.mchacks.qrtransfer;

import android.graphics.Bitmap;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;

import com.google.zxing.WriterException;
import com.mchacks.qrtransfer.processing.BitmapProcessor;
import com.mchacks.qrtransfer.processing.QRProcessor;
import com.mchacks.qrtransfer.util.Constants;

import java.util.LinkedList;

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
        LinkedList<Bitmap> qrCodes = new LinkedList<>();

        // Generate some junk files
        for (int i = 0; i < 5; i++) {
            qrCodes.add(QRProcessor.generateQrCode("!" + i + "..." + i + "..." + i + "!"));
        }

        // Final red image at end
        qrCodes.add(BitmapProcessor.createImage(Constants.qrDimension, Constants.qrDimension, Color.RED));

        final ImageView iv = (ImageView) findViewById(R.id.imageView);

        Handler[] handlers = new Handler[qrCodes.size()];

        for (int i = 0; i < qrCodes.size(); i++) {
            handlers[i] = new Handler();
            final Bitmap qrCode = qrCodes.get(i);
            handlers[i].postDelayed(new Runnable() {
                public void run() {
                    iv.setImageBitmap(qrCode);
                }
            }, i *3000);
        }
    }

}
