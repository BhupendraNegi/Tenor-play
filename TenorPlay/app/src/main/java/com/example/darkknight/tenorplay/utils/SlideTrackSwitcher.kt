package com.example.darkknight.tenorplay.utils

import android.view.GestureDetector
import android.view.MotionEvent
import android.view.View
import com.example.darkknight.tenorplay.fragments.SongPlayingFragment

class SlideTrackSwitcher : View.OnTouchListener {

    private var mDetector: GestureDetector? = null
    private var mView: View? = null

    fun attach(v: View) {
        mView = v
        mDetector = GestureDetector(v.context, SwipeListener())
        v.setOnTouchListener(this)
    }

    override fun onTouch(v: View, event: MotionEvent): Boolean {
        return mDetector!!.onTouchEvent(event)
    }

    private inner class SwipeListener : GestureDetector.SimpleOnGestureListener() {

        override fun onFling(e1: MotionEvent, e2: MotionEvent, velocityX: Float, velocityY: Float): Boolean {
            var result = false
            try {
                val diffY = e2.y - e1.y
                val diffX = e2.x - e1.x
                if (Math.abs(diffX) > Math.abs(diffY)) {
                    if (Math.abs(diffX) > SWIPE_THRESHOLD && Math.abs(velocityX) > SWIPE_VELOCITY_THRESHOLD) {
                        if (diffX > 0) {
                            onSwipeRight()
                        } else {
                            onSwipeLeft()
                        }
                    }
                    result = true
                } else if (Math.abs(diffY) > SWIPE_THRESHOLD && Math.abs(velocityY) > SWIPE_VELOCITY_THRESHOLD) {
                    if (diffY > 0) {
                        onSwipeBottom()
                    } else {
                        onSwipeTop()
                    }
                }
                result = true

            } catch (exception: Exception) {
                exception.printStackTrace()
            }

            return result
        }

        override fun onDown(e: MotionEvent): Boolean {
            return true
        }


        override fun onSingleTapConfirmed(e: MotionEvent): Boolean {
            onClick()
            return super.onSingleTapConfirmed(e)
        }
    }

    fun onSwipeRight() {
        SongPlayingFragment.Staticated.playNext("PlayNextNormal")
    }

    fun onSwipeLeft() {
        SongPlayingFragment.Staticated.playPrevious("PlayNextNormal")
    }

    fun onSwipeTop() {}

    fun onSwipeBottom() {}

    fun onClick() {

    }

    companion object {

        private val SWIPE_THRESHOLD = 200
        private val SWIPE_VELOCITY_THRESHOLD = 100
    }
}
