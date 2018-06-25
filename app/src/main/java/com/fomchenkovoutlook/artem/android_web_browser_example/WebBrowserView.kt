package com.fomchenkovoutlook.artem.android_web_browser_example

import android.annotation.SuppressLint
import android.content.Context
import android.util.AttributeSet
import android.webkit.WebView

/**
 * Clickable WebView
 */
class WebBrowserView: WebView {

    constructor(context: Context?) : super(context)
    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs)
    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr)

    @SuppressLint("ClickableViewAccessibility")
    override fun performClick(): Boolean {
        return true
    }

}