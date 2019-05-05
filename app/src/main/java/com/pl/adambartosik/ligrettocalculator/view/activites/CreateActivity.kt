package com.pl.adambartosik.ligrettocalculator.view.activites

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import com.pl.adambartosik.ligrettocalculator.R
import com.pl.adambartosik.ligrettocalculator.view.fragments.NewGameFragment
import kotlinx.android.synthetic.main.activity_create.*

class CreateActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create)

        // set icon arrow back
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)

        initialButtonNext()
        setTitleName()
        setFragment()

    }

    private fun setTitleName(){
        setTitle(R.string.title_new_game)
    }

    private fun setFragment() {
        supportFragmentManager.beginTransaction().add(container_fl_ac.id, NewGameFragment.newInstance()).commit()
    }

    private fun initialButtonNext() {
        val animation = AnimationUtils.loadAnimation( this@CreateActivity.applicationContext, R.anim.click)
        animation.setAnimationListener(object : Animation.AnimationListener{
            override fun onAnimationEnd(animation: Animation?) {
               closeActivity()
            }
            override fun onAnimationStart(animation: Animation?) { }
            override fun onAnimationRepeat(animation: Animation?) { }
        })

        /*
        button_next_btn_ac.setOnClickListener {
        button_next_btn_ac.startAnimation(animation)
        }
        */
    }

    private fun closeActivity(){
        finish()
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                onBackPressed()
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }
}
