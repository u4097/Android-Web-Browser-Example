package com.fomchenkovoutlook.artem.android_web_browser_example;

import android.support.annotation.NonNull;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.EditText;

// Custom client:
public class WebBrowserViewClient extends WebViewClient {

    private EditText etWebSite;

    public WebBrowserViewClient(@NonNull EditText etWebSite) {
        this.etWebSite = etWebSite;
    }

    @Override
    public void onPageFinished(WebView view, String url) {
        super.onPageFinished(view, url);
        etWebSite.setText(url);
    }
}
