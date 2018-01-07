package com.fomchenkovoutlook.artem.android_web_browser_example;

import android.annotation.SuppressLint;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.EditText;
import android.widget.ImageButton;

public class WebBrowserActivity
        extends AppCompatActivity {

    private EditText etWebSite;

    private ImageButton ibGoToWebSite;

    private WebView wvWebSite;

    private void goToWebSite(String address) {
        if (!address.startsWith("https://")
                || !address.startsWith("https://")) {
            address = "https://" + address;
        }

        wvWebSite.loadUrl(address);
    }

    @SuppressLint("SetJavaScriptEnabled")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_browser);

        etWebSite = findViewById(R.id.et_web_site);

        ibGoToWebSite = findViewById(R.id.ib_go_to_web_site);
        ibGoToWebSite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goToWebSite(etWebSite.getText().toString());
            }
        });

        wvWebSite = findViewById(R.id.wv_web_site);
        wvWebSite.setWebViewClient(new WebViewClient());
        wvWebSite.getSettings().setJavaScriptEnabled(true);
        wvWebSite.setVerticalScrollBarEnabled(true);
        wvWebSite.setHorizontalScrollBarEnabled(true);
    }
}
