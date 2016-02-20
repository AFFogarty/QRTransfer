package com.mchacks.qrtransfer;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.ClipData;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.mchacks.qrtransfer.processing.QRProcessor;
import com.nononsenseapps.filepicker.FilePickerActivity;
import android.widget.ImageView;

import com.google.zxing.WriterException;

import com.google.common.io.Files;
import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;



public class SendFileActivity extends AppCompatActivity {

    private static final int FILE_CODE = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_send_file);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // This always works
                Intent i = new Intent(view.getContext(), FilePickerActivity.class);
                // This works if you defined the intent filter
                // Intent i = new Intent(Intent.ACTION_GET_CONTENT);

                // Set these depending on your use case. These are the defaults.
                i.putExtra(FilePickerActivity.EXTRA_ALLOW_MULTIPLE, false);
                i.putExtra(FilePickerActivity.EXTRA_ALLOW_CREATE_DIR, false);
                i.putExtra(FilePickerActivity.EXTRA_MODE, FilePickerActivity.MODE_FILE);

                // Configure initial directory by specifying a String.
                // You could specify a String like "/storage/emulated/0/", but that can
                // dangerous. Always use Android's API calls to get paths to the SD-card or
                // internal memory.
                i.putExtra(FilePickerActivity.EXTRA_START_PATH, Environment.getExternalStorageDirectory().getPath());

                startActivityForResult(i, FILE_CODE);


//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
            }
        });
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

    }

    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == FILE_CODE && resultCode == Activity.RESULT_OK) {
            if (data.getBooleanExtra(FilePickerActivity.EXTRA_ALLOW_MULTIPLE, false)) {
                // For JellyBean and above
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                    ClipData clip = data.getClipData();

                    if (clip != null) {
                        for (int i = 0; i < clip.getItemCount(); i++) {
                            Uri uri = clip.getItemAt(i).getUri();
                            // Do something with the URI
                            handleLoadedFileUri(uri);
                        }
                    }
                    // For Ice Cream Sandwich
                } else {
                    ArrayList<String> paths = data.getStringArrayListExtra
                            (FilePickerActivity.EXTRA_PATHS);

                    if (paths != null) {
                        for (String path : paths) {
                            Uri uri = Uri.parse(path);
                            // Do something with the URI
                            handleLoadedFileUri(uri);
                        }
                    }
                }

            } else {
                Uri uri = data.getData();
                // Do something with the URI
                handleLoadedFileUri(uri);
            }
        }
    }

    /**
     * After the file URI has been chosen, do what happens next
     *
     * @param uri
     */
    void handleLoadedFileUri(Uri uri) {
        File f1 = new File(uri.getPath());
        String encoded_string = parse_file(f1);
        String substring = encoded_string.substring(0, 1000);

        try {
            Bitmap bmp = QRProcessor.generateQrCode(substring);
            ImageView iv = (ImageView) findViewById(R.id.imageView);
            iv.setImageBitmap(bmp);
        } catch (WriterException e) {
            e.printStackTrace();
        }

        /*for (int i = 0; i < encoded_string.length(); i += 100)
        {
            bmp = QRProcessor.generateQrCode()
        }*/

    }

    String parse_file(File f) {
        try {
            byte[] file_bytes = Files.toByteArray(f);
            String encoded_string = new String(file_bytes, "ISO-8859-1");
            return encoded_string;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "";
    }

    byte[] parse_string(String s)
    {
        byte[] data;
        try{
            data = s.getBytes("ISO-8859-1");
            return data;
        } catch (UnsupportedEncodingException e)
        {
            e.printStackTrace();
        }
        return new byte[0];
    }
}