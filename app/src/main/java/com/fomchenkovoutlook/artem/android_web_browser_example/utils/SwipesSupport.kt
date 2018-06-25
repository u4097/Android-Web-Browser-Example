package com.fomchenkovoutlook.artem.android_web_browser_example.utils

import android.annotation.SuppressLint
import android.content.Context
import android.support.annotation.NonNull
import android.view.GestureDetector
import android.view.MotionEvent
import android.view.View

/** Class for add support left and right swipes
 * @param context need to create MeasureDetector variable
 * */
open class SwipesSupport(@NonNull private val context: Context): View.OnTouchListener {

    inner class GestureListener: GestureDetector.SimpleOnGestureListener() {

        private val SWIPE_DISTANCE_THRESHOLD = 100
        private val SWIPE_VELOCITY_THRESHOLD = 100

        override fun onFling(e1: MotionEvent?, e2: MotionEvent?, velocityX: Float, velocityY: Float): Boolean {
            val distanceX = e2!!.x - e1!!.x
            val distanceY = e2.y - e1.y
            if (Math.abs(distanceX) > Math.abs(distanceY)
                    && Math.abs(distanceX) > SWIPE_DISTANCE_THRESHOLD
                    && Math.abs(velocityX) > SWIPE_VELOCITY_THRESHOLD) {
                if (distanceX > 0) {
                    onSwipeRight()
                } else {
                    onSwipeLeft()
                }
                return true
            }
            return false
        }

    }

    private var gestureDetector: GestureDetector? = null

    init {
        gestureDetector = GestureDetector(context, GestureListener())
    }

    /** On left swipe action */
    internal open fun onSwipeLeft() {}

    /** On right swipe action */
    internal open fun onSwipeRight() {}

    @SuppressLint("ClickableViewAccessibility")
    override fun onTouch(v: View?, event: MotionEvent?): Boolean {
        return gestureDetector!!.onTouchEvent(event)
    }

}