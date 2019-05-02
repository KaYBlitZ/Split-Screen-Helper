package com.kennethliang.splitscreenhelper;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewById(R.id.saucy_btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showGitHubPage();
            }
        });
    }

    private void showGitHubPage() {
        Intent i = new Intent(Intent.ACTION_VIEW, Uri.parse("https://github.com/KaYBlitZ/Split-Screen-Helper"));
        startActivity(i);
    }
}
