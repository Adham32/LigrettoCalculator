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

    private var currentFragmentType: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create)

        // set icon arrow back
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)

        initialButtonNext()
        currentFragmentType = intent.getIntExtra("option",1)
        setTitleName()
        setFragment()

    }

    private fun setTitleName(){
        when(currentFragmentType){
            1 -> {
                setTitle(R.string.title_new_game)
            }
            2 -> {
                setTitle(R.string.title_new_player)
            }
        }
    }

    private fun setFragment() {
        when(currentFragmentType){
            1 -> {
                supportFragmentManager.beginTransaction().add(container_fl_ac.id, NewGameFragment.newInstance())
                    .commit()
            }
            2 -> {

            }
        }
    }

    private fun initialButtonNext() {
        val animation = AnimationUtils.loadAnimation( this@CreateActivity.applicationContext, R.anim.click)
        animation.setAnimationListener(object : Animation.AnimationListener{
            override fun onAnimationEnd(animation: Animation?) {
                when(currentFragmentType){
                    1 -> closeActivity()
                }
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
