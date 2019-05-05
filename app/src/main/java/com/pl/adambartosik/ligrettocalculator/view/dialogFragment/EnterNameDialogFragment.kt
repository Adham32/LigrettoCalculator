package com.pl.adambartosik.ligrettocalculator.view.dialogFragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import androidx.fragment.app.DialogFragment
import androidx.lifecycle.ViewModelProviders
import com.pl.adambartosik.ligrettocalculator.R
import com.pl.adambartosik.ligrettocalculator.model.tables.Player
import com.pl.adambartosik.ligrettocalculator.viewmodel.PlayerViewModel
import kotlinx.android.synthetic.main.dialog_fragment_enter_name.*

class EnterNameDialogFragment(): DialogFragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        var view =  inflater.inflate(R.layout.dialog_fragment_enter_name, container, false)

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        title_tv_dfen.text = "Enter name"
        initButtons()
        mPlayerViewModel = ViewModelProviders.of(this).get(PlayerViewModel::class.java)
    }

    private fun initButtons(){
        val animationPos = AnimationUtils.loadAnimation( this.context, R.anim.click)
        val animationNeg = AnimationUtils.loadAnimation( this.context, R.anim.click)

        animationNeg.setAnimationListener(object: Animation.AnimationListener{
            override fun onAnimationRepeat(animation: Animation?) {}
            override fun onAnimationStart(animation: Animation?) {}
            override fun onAnimationEnd(animation: Animation?) {
                this@EnterNameDialogFragment.dismiss()
            }
        })

        animationPos.setAnimationListener(object: Animation.AnimationListener{
            override fun onAnimationRepeat(animation: Animation?) {}
            override fun onAnimationStart(animation: Animation?) {}
            override fun onAnimationEnd(animation: Animation?) {
                addNewPlayer()
            }
        })
        positive_btn_dfen.text = resources.getString(R.string.button_save)
        positive_btn_dfen.setOnClickListener {
            it.startAnimation(animationPos)
        }
        negative_btn_dfen.text = resources.getString(R.string.button_cancel)
        negative_btn_dfen.setOnClickListener {
            it.startAnimation(animationNeg)
        }
    }

    private lateinit var mPlayerViewModel: PlayerViewModel

    fun addNewPlayer(){
        if(!mPlayerViewModel.insertNewPlayer(input_name_of_game_tv_dfen.text.toString())){
            // show error
        }
    }


}