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
import android.widget.EditText
import android.widget.ImageButton
import com.fomchenkovoutlook.artem.android_web_browser_example.utils.SwipesSupport

class WebBrowserActivity: AppCompatActivity() {

    private val HTTP_PROTOCOL = "http://"
    private val HTTPS_PROTOCOL = "https://"

    private var etWebSite: EditText? = null
    private var wvWebBrowserView: WebBrowserView? = null

    /**
     * Load entered web address
     * @param address entered web address
     */
    private fun loadWebSite(@NonNull address: String) {
        var loadingPage = address
        if (!address.startsWith(HTTP_PROTOCOL) || !address.startsWith(HTTPS_PROTOCOL)) {
            loadingPage = HTTP_PROTOCOL + address
        }
        wvWebBrowserView!!.loadUrl(loadingPage)
    }

    /**
     * Back to previous web address
     */
    private fun back() {
        wvWebBrowserView!!.goBack()
        etWebSite!!.setText(wvWebBrowserView!!.url)
    }

    /**
     * Forwand to next web address
     */
    private fun forward() {
        wvWebBrowserView!!.goForward()
        etWebSite!!.setText(wvWebBrowserView!!.url)
    }

    @SuppressLint("SetJavaScriptEnabled")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_web_browser)

        etWebSite = findViewById(R.id.et_web_site)

        val ibGoToWebSite = findViewById<ImageButton>(R.id.ib_go_to_web_site)
        ibGoToWebSite.setOnClickListener {
            loadWebSite(etWebSite!!.text.toString())
        }

        wvWebBrowserView = findViewById(R.id.wv_web_site)
        wvWebBrowserView!!.webViewClient = WebBrowserViewClient(etWebSite!!)
        wvWebBrowserView!!.settings.javaScriptEnabled = true
        wvWebBrowserView!!.isVerticalScrollBarEnabled = false
        wvWebBrowserView!!.isHorizontalScrollBarEnabled = false
        wvWebBrowserView!!.setOnTouchListener(object : SwipesSupport(this) {
            override fun onSwipeRight() {
                // If can go back on swipe:
                if (wvWebBrowserView!!.canGoBack()) {
                    back()
                }
            }

            override fun onSwipeLeft() {
                // If can go forward on swipe:
                if (wvWebBrowserView!!.canGoForward()) {
                    forward()
                }
            }
        })
        wvWebBrowserView!!.setDownloadListener { url, _, _, _, _ ->
            val request = DownloadManager.Request(Uri.parse(url))
            request.allowScanningByMediaScanner()
            request.setNotificationVisibility(
                    DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED)

            // Set a filename:
            val filename = url.substring(url.lastIndexOf('/') + 1)
            request.setDestinationInExternalPublicDir(Environment.DIRECTORY_DOWNLOADS,
                    "android-web-browser-example/$filename")
            val downloadManager = getSystemService(
                    Context.DOWNLOAD_SERVICE) as DownloadManager
            downloadManager.enqueue(request)
        }
    }

    override fun dispatchKeyEvent(event: KeyEvent): Boolean {
        return if (event.keyCode == KeyEvent.KEYCODE_BACK) true else super.dispatchKeyEvent(event)
    }

}