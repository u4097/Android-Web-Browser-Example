package com.fomchenkovoutlook.artem.android_web_browser_example.view;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.AttributeSet;
import android.webkit.WebView;

public class WebBrowserView
        extends WebView {

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
