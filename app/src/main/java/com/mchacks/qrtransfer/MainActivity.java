package com.mchacks.qrtransfer;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
    }

    /**
     * Launch the SendFileActivity.
     *
     * @param view the main view
     */
    public void onClickSendFileButton(View view) {
        Intent intent = new Intent(view.getContext(), SendFileActivity.class);
        startActivity(intent);
    }

    /**
     * Launch the ReceiveFileActivity.
     *
     * @param view the main view
     */
    public void onClickReceiveFileButton(View view) {
        Intent intent = new Intent(view.getContext(), ReceiveFileActivity.class);
        startActivity(intent);
    }
}
