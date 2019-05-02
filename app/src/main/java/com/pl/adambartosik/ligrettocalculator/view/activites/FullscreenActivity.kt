package com.pl.adambartosik.ligrettocalculator.view.activites

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.SystemClock
import android.view.View
import kotlinx.android.synthetic.main.activity_fullscreen.*
import android.view.animation.AnimationUtils
import kotlinx.android.synthetic.main.fragment_just_play.view.*
import android.view.animation.Animation
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.pl.adambartosik.ligrettocalculator.R
import com.pl.adambartosik.ligrettocalculator.viewmodel.StarterViewModel


class FullscreenActivity : AppCompatActivity() {
    private val mShowHandler = Handler()
    private val mHideHandler = Handler()
    private var mVisible: Boolean = false
    private var lastClickTime: Long = 0
    private val mShowRunnable = Runnable { show() }
    private val mHideRunnable = Runnable { hide() }


    private lateinit var starterViewModel: StarterViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_fullscreen)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        auth_fragment_af.view!!.visibility = View.INVISIBLE
        addListenerToAnimClickedNext()

    }

    private fun addListenerToAnimClickedNext(){
        auth_fragment_af.view!!.start_btn_fjp.setOnClickListener {
            if (SystemClock.elapsedRealtime() - lastClickTime > 1000) {
                lastClickTime = SystemClock.elapsedRealtime()
                val animation = AnimationUtils.loadAnimation(applicationContext, R.anim.click)
                auth_fragment_af.view!!.start_btn_fjp.startAnimation(animation)
                delayedHide(400)
            }
        }
    }

    override fun onPostCreate(savedInstanceState: Bundle?) {
        super.onPostCreate(savedInstanceState)
        delayedShow(1000)
    }


    private fun hide() {
        mVisible = false
        val bottomOut = AnimationUtils.loadAnimation(applicationContext, R.anim.abc_slide_out_bottom)
        bottomOut.setAnimationListener(object: Animation.AnimationListener{
            override fun onAnimationRepeat(animation: Animation?) {}
            override fun onAnimationStart(animation: Animation?) {}
            override fun onAnimationEnd(animation: Animation?) {
                auth_fragment_af.view!!.visibility = View.INVISIBLE
                ActivityOpenManager.openMainActivity(this@FullscreenActivity)
            }
        })

        auth_fragment_af.view!!.startAnimation(bottomOut)
    }

    private fun show() {
        mVisible = true
        val bottomUp = AnimationUtils.loadAnimation(applicationContext, R.anim.abc_slide_in_bottom)
        auth_fragment_af.view!!.startAnimation(bottomUp)
        auth_fragment_af.view!!.visibility = View.VISIBLE

    }

    private fun delayedShow(delayMillis: Int){
        mShowHandler.removeCallbacks(mShowRunnable)
        mShowHandler.postDelayed(mShowRunnable, delayMillis.toLong())
    }

    private fun delayedHide(delayMillis: Int) {
        mHideHandler.removeCallbacks(mHideRunnable)
        mHideHandler.postDelayed(mHideRunnable, delayMillis.toLong())
    }




}
