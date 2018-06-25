package com.fomchenkovoutlook.artem.android_web_browser_example

import android.support.annotation.NonNull
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.EditText

/**
 * Set url in search field
 * @param etWebSite search field
 * */
class WebBrowserViewClient(@NonNull private val etWebSite: EditText): WebViewClient() {

    override fun onPageFinished(view: WebView, url: String) {
        super.onPageFinished(view, url)
        etWebSite.setText(url)
    }

}