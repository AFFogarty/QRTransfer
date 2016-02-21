package com.mchacks.qrtransfer;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.hardware.Camera;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Surface;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.VideoView;

import java.io.File;
import java.io.IOException;

import static com.mchacks.qrtransfer.CameraPreview.getCameraInstance;

public class ReceiveFileActivity extends AppCompatActivity {

    static final int REQUEST_VIDEO_CAPTURE = 1;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_receive_file);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        SurfaceView cameraSurfaceView = (SurfaceView) findViewById(R.id.surfaceView);

        // Create an instance of Camera
        Camera mCamera = getCameraInstance();

        // Create our Preview view and set it as the content of our activity.
        CameraPreview mPreview = new CameraPreview(this, mCamera);
        FrameLayout preview = (FrameLayout) findViewById(R.id.cameraPreview);
        preview.addView(mPreview);

//
//        // Install a SurfaceHolder.Callback so we get notified when the
//        // underlying surface is created and destroyed.
//        mHolder = cameraSurfaceView.getHolder();
//        mHolder.addCallback(this);
//        mHolder.setType(SurfaceHolder.SURFACE_TYPE_PUSH_BUFFERS);
//
//
//


        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                // If the user can record a video, let them.  Otherwise display a message
//                if (getPackageManager().hasSystemFeature(PackageManager.FEATURE_CAMERA)) {
//                    Intent takeVideoIntent = new Intent(MediaStore.ACTION_VIDEO_CAPTURE);
//                    if (takeVideoIntent.resolveActivity(getPackageManager()) != null) {
//                        startActivityForResult(takeVideoIntent, REQUEST_VIDEO_CAPTURE);
//                    }
//                } else {
//                    Snackbar.make(view, "Please enable video recording permissions!", Snackbar.LENGTH_LONG)
//                            .setAction("Action", null).show();
//                }
            }
        });
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }
}


