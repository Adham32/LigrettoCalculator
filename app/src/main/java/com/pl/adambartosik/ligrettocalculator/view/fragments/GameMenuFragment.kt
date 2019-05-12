package com.pl.adambartosik.ligrettocalculator.view.fragments

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.Window
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.pl.adambartosik.ligrettocalculator.R
import com.pl.adambartosik.ligrettocalculator.model.tables.Game
import com.pl.adambartosik.ligrettocalculator.view.activites.ActivityOpenManager
import com.pl.adambartosik.ligrettocalculator.view.dialogFragment.OptionsMenuDialogBottom
import com.pl.adambartosik.ligrettocalculator.viewmodel.AdapterOfGames
import com.pl.adambartosik.ligrettocalculator.viewmodel.GameViewModel
import kotlinx.android.synthetic.main.fragment_menu_game_dashboard.*
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe


class GameMenuFragment: Fragment() {

    companion object {
        fun newInstance() = GameMenuFragment()
    }

    private lateinit var dialog: OptionsMenuDialogBottom
    private lateinit var gameViewModel: GameViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        var view =  inflater.inflate(R.layout.fragment_menu_game_dashboard, container, false)
        gameViewModel = ViewModelProviders.of(this.activity!!).get(GameViewModel::class.java)
        EventBus.getDefault().register(this@GameMenuFragment)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initialButtonCreateNewGame()
        initialRecyclerView()

    }

    override fun onDestroy() {
        super.onDestroy()
        EventBus.getDefault().unregister(this)
    }

    private fun initialRecyclerView() {
        var adapter = AdapterOfGames()
        recyclerview_list.layoutManager = LinearLayoutManager(context, RecyclerView.VERTICAL, false)
        recyclerview_list.adapter = adapter

        gameViewModel.gamesArray.observe(this, Observer { listOfGames ->
            if(listOfGames != null){
                adapter.setList(listOfGames)
                if(listOfGames.isNotEmpty()){
                    // game list entry
                    nsv_fmgd.smoothScrollTo(0,0)
                }else{
                    // game list is empty
                    Log.d(getString(R.string.tag_fragment_game_menu), "Game list is empty.")
                }
            }else{
                // game list is null
                Log.d(getString(R.string.tag_fragment_game_menu), "Game list is null.")
            }
        })
    }

    private fun initialButtonCreateNewGame(){
        val animation = AnimationUtils.loadAnimation(context, R.anim.click)
        animation.setAnimationListener(object : Animation.AnimationListener{
            override fun onAnimationEnd(animation: Animation?) {
                ActivityOpenManager.openNewGameActivity(this@GameMenuFragment.activity!!)
            }
            override fun onAnimationStart(animation: Animation?) { }
            override fun onAnimationRepeat(animation: Animation?) { }
        })

        button_create_new_game_btn_gmd.setOnClickListener {
           button_create_new_game_btn_gmd.startAnimation(animation)
        }
    }

    private fun showMenuOfGame(){
        dialog = OptionsMenuDialogBottom()
        dialog.show(this@GameMenuFragment.fragmentManager, "O")
    }

    private var gameInteraction: Game? = null

    @Subscribe
    fun registerEventGameMenuClicked(event: AdapterOfGames.EventMenuGameClicked){
        showMenuOfGame()
        gameInteraction = event.game
    }

    @Subscribe
    fun registerEventOptionSelected(event: OptionsMenuDialogBottom.AdapterBottomMenu.EventOptionSelected){
        dialog.dismiss()
        when(event.option){
            OptionsMenuDialogBottom.AdapterBottomMenu.Option.EDIT -> {
                Toast.makeText(context, "EDIT - "+ gameInteraction!!.name, Toast.LENGTH_SHORT).show()
            }
            OptionsMenuDialogBottom.AdapterBottomMenu.Option.DELETE -> {
                gameViewModel.deleteGame(gameInteraction!!)
                gameInteraction = null
            }
        }
    }
}