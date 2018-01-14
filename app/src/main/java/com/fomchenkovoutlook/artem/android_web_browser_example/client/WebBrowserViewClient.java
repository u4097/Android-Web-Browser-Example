package com.fomchenkovoutlook.artem.android_web_browser_example.client;

import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.EditText;

// Custom client:
public class WebBrowserViewClient
        extends WebViewClient {

    private EditText etWebSite;

    public WebBrowserViewClient(EditText etWebSite) {
        this.etWebSite = etWebSite;
    }

    @Override
    public void onPageFinished(WebView view, String url) {
        super.onPageFinished(view, url);

        etWebSite.setText(url);
    }
}
