package com.ifts.takemypetufficiale;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class Activity_login extends AppCompatActivity {
    private WebView login;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        login = (WebView) findViewById(R.id.login);
        login.setWebViewClient(new WebViewClient());
        //login.loadUrl("http://www.google.com");
        //login.loadUrl("https://bootsnipp.com/snippets/vl4R7");
        //login.loadUrl("https://bootsnipp.com/snippets/z8l2X");
        login.loadUrl("https://pet/provaLogin");

        WebSettings webSettings = login.getSettings();
        webSettings.setJavaScriptEnabled(true);
    }

    @Override
    public void onBackPressed() {
        if(login.canGoBack()){
            login.goBack();
        } else{
            super.onBackPressed();
        }
    }
}
