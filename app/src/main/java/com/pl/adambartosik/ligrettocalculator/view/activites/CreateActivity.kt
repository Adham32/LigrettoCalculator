package com.pl.adambartosik.ligrettocalculator.view.activites

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import androidx.fragment.app.DialogFragment
import com.pl.adambartosik.ligrettocalculator.R
import com.pl.adambartosik.ligrettocalculator.view.dialogFragment.bottom.CreateUserDialogFragmentBottom
import com.pl.adambartosik.ligrettocalculator.view.dialogFragment.bottom.SelectPlayerDialogFragmentBottom
import com.pl.adambartosik.ligrettocalculator.view.fragments.NewGameFragment
import com.pl.adambartosik.ligrettocalculator.viewmodel.adapter.AdapterOfPlayersInCreateGame
import com.pl.adambartosik.ligrettocalculator.viewmodel.adapter.AdapterOfPlayersToSelect
import kotlinx.android.synthetic.main.activity_create.*
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe

class CreateActivity : AppCompatActivity() {

    private lateinit var dialog: DialogFragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create)

        EventBus.getDefault().register(this@CreateActivity)
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


    @Subscribe
    fun reciveEventOpenSelectPlayerDialog( event: AdapterOfPlayersInCreateGame.EventOpenSelectPlayerDialog){
        // open dialog sheet bottom
        dialog = SelectPlayerDialogFragmentBottom()
        dialog.show(supportFragmentManager, "O")
    }

    @Subscribe
    fun reciveEventSelectedPlayerClicked( event: AdapterOfPlayersToSelect.EventSelectedPlayerClicked){
        /// close dialog, get player
        var player = event.player

        if(player == null){
            dialog.dismiss()
            this@CreateActivity.onBackPressed()
        }else{
            dialog.dismiss()
        }
    }
}
