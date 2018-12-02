package com.fomchenkovoutlook.artem.android_web_browser_example

import android.annotation.SuppressLint
import android.app.DownloadManager
import android.content.Context
import android.net.Uri
import android.os.Bundle
import android.os.Environment
import android.support.annotation.NonNull
import android.support.v7.app.AppCompatActivity
import android.view.KeyEvent
import com.fomchenkovoutlook.artem.android_web_browser_example.utils.SwipesSupport
import kotlinx.android.synthetic.main.activity_web_browser.*

class WebBrowserActivity: AppCompatActivity() {

    private val HTTP_PROTOCOL = "http://"
    private val HTTPS_PROTOCOL = "https://"

    private fun loadWebSite(@NonNull address: String) {
        var loadingPage = address
        if (address.startsWith(HTTP_PROTOCOL) || address.startsWith(HTTPS_PROTOCOL)) {
            web_view.loadUrl(loadingPage)
        } else {
            loadingPage = HTTP_PROTOCOL + address
            web_view.loadUrl(loadingPage)
        }
    }

    private fun back() {
        web_view.goBack()
    }

    private fun forward() {
        web_view.goForward()
    }

    @SuppressLint("SetJavaScriptEnabled")
    private fun init() {
        open_link.setOnClickListener {
            loadWebSite(web_link.text.toString())
        }

        web_view.webViewClient = WebBrowserViewClient(web_link)
        web_view.settings.javaScriptEnabled = true
        web_view.isVerticalScrollBarEnabled = false
        web_view.isHorizontalScrollBarEnabled = false
        web_view.setOnTouchListener(object : SwipesSupport(this) {
            override fun onSwipeRight() {
                if (web_view.canGoBack()) {
                    back()
                }
            }

            override fun onSwipeLeft() {
                if (web_view.canGoForward()) {
                    forward()
                }
            }
        })
        web_view.setDownloadListener { url, _, _, _, _ ->
            val request = DownloadManager.Request(Uri.parse(url))
            request.allowScanningByMediaScanner()
            request.setNotificationVisibility(
                    DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED)

            // Set a filename:
            val filename = url.substring(url.lastIndexOf('/') + 1)
            request.setDestinationInExternalPublicDir(Environment.DIRECTORY_DOWNLOADS,
                    "${getString(R.string.app_name)}/$filename")
            val downloadManager = getSystemService(
                    Context.DOWNLOAD_SERVICE) as DownloadManager
            downloadManager.enqueue(request)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_web_browser)
        init()
    }

    override fun dispatchKeyEvent(event: KeyEvent): Boolean {
        return if (event.keyCode == KeyEvent.KEYCODE_BACK) true else super.dispatchKeyEvent(event)
    }

}