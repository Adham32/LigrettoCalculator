package com.pl.adambartosik.ligrettocalculator.view.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import androidx.fragment.app.Fragment
import com.pl.adambartosik.ligrettocalculator.R
import com.pl.adambartosik.ligrettocalculator.view.dialogFragment.CreateUserDialogFragmentBottom
import kotlinx.android.synthetic.main.fragment_menu_game_dashboard.*
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe

class PlayerMenuFragment: Fragment() {


    companion object {
        fun newInstance() = PlayerMenuFragment()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        var view =  inflater.inflate(R.layout.fragment_menu_player_dashboard, container, false)
        EventBus.getDefault().register(this)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initialButtonCreateNewPlayer()
    }

    override fun onDestroy() {
        super.onDestroy()
        EventBus.getDefault().unregister(this)
    }

    private lateinit var dialog: CreateUserDialogFragmentBottom

    private fun initialButtonCreateNewPlayer(){
        val animation = AnimationUtils.loadAnimation(context, R.anim.click)
        animation.setAnimationListener(object : Animation.AnimationListener{
            override fun onAnimationEnd(animation: Animation?) {
               // open dialog
                dialog = CreateUserDialogFragmentBottom()
                dialog.show(this@PlayerMenuFragment.fragmentManager, "O")
            }
            override fun onAnimationStart(animation: Animation?) { }
            override fun onAnimationRepeat(animation: Animation?) { }
        })

        button_create_new_game_btn_gmd.setOnClickListener {
            button_create_new_game_btn_gmd.startAnimation(animation)
        }
    }

    @Subscribe
    fun registerEventCreateUser(event: CreateUserDialogFragmentBottom.EventCreateUser){
        dialog.dismiss()
    }
}