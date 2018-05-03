package com.fomchenkovoutlook.artem.android_web_browser_example;

import android.annotation.SuppressLint;
import android.content.Context;
import android.support.annotation.NonNull;
import android.util.AttributeSet;
import android.webkit.WebView;

// View:
public class WebBrowserView extends WebView {

    public static final String HTTP_PROTOCOL = "http://";
    public static final String HTTPS_PROTOCOL = "https://";

    public WebBrowserView(Context context) {
        super(context);
    }

    public WebBrowserView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public WebBrowserView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @SuppressLint("ClickableViewAccessibility")
    @Override
    public boolean performClick() {
        return true;
    }
}
