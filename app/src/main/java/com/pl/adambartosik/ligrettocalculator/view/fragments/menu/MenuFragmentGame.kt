package com.pl.adambartosik.ligrettocalculator.view.fragments.menu

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
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
import com.pl.adambartosik.ligrettocalculator.model.tables.GameStatus
import com.pl.adambartosik.ligrettocalculator.view.activites.ActivityOpenManager
import com.pl.adambartosik.ligrettocalculator.view.dialogFragment.bottom.OptionsMenuDialogBottom
import com.pl.adambartosik.ligrettocalculator.view.dialogFragment.bottom.OptionsMenuDialogBottomGame
import com.pl.adambartosik.ligrettocalculator.viewmodel.adapter.AdapterOfGames
import com.pl.adambartosik.ligrettocalculator.viewmodel.GameViewModel
import kotlinx.android.synthetic.main.fragment_menu_game_dashboard.*
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe


class MenuFragmentGame: Fragment() {

    companion object {
        fun newInstance() =
            MenuFragmentGame()
    }

    private var gameStatusList: List<GameStatus>? = null
    private lateinit var dialog: OptionsMenuDialogBottomGame
    private lateinit var gameViewModel: GameViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        var view =  inflater.inflate(R.layout.fragment_menu_game_dashboard, container, false)
        gameViewModel = ViewModelProviders.of(this.activity!!).get(GameViewModel::class.java)
        EventBus.getDefault().register(this@MenuFragmentGame)
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
        players_list_rv_fmpd.layoutManager = LinearLayoutManager(context, RecyclerView.VERTICAL, false)
        players_list_rv_fmpd.adapter = adapter

        gameViewModel.gameStatus.observe(this, Observer {
            gameStatusList = it
        })

        gameViewModel.gamesEntityArray.observe(this, Observer {
            adapter.setList(it)
        })

      /*  gameViewModel.gamesArray.observe(this, Observer { listOfGames ->
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
        })*/
    }

    private fun initialButtonCreateNewGame(){
        val animation = AnimationUtils.loadAnimation(context, R.anim.click)
        animation.setAnimationListener(object : Animation.AnimationListener{
            override fun onAnimationEnd(animation: Animation?) {
                ActivityOpenManager.openNewGameActivity(this@MenuFragmentGame.activity!!, null)
            }
            override fun onAnimationStart(animation: Animation?) { }
            override fun onAnimationRepeat(animation: Animation?) { }
        })

        button_create_new_game_btn_gmd.setOnClickListener {
           button_create_new_game_btn_gmd.startAnimation(animation)
        }
    }

    private fun showMenuOfGame(){
        dialog = OptionsMenuDialogBottomGame()
        dialog.show(this@MenuFragmentGame.fragmentManager, "O")
    }

    private var gameInteraction: Game? = null

    @Subscribe
    fun registerEventGameMenuClicked(event: AdapterOfGames.EventMenuGameClicked){
        showMenuOfGame()
        gameInteraction = event.game
    }


    /**
     * Action after click on Game CardView in GameMenuFragment
     * @param event EventGameClicked
     */
    @Subscribe
    fun recieveEventEventGameClicked(event: AdapterOfGames.EventGameClicked){
        var showError = true

        if(gameStatusList != null){

            // init gameStatus values

            val gameStatusCreated = gameStatusList!!.find { it.name.equals(resources.getString(R.string.game_status_created)) }
            val gameStatusInProgress = gameStatusList!!.find { it.name.equals(resources.getString(R.string.game_status_inprogress)) }
            val gameStatusEndGame = gameStatusList!!.find { it.name.equals(resources.getString(R.string.game_status_ended)) }

            // init action listener

            when (event.game.statusID ){
                gameStatusCreated?.id -> {
                    openNewGameActivityForGameID(event.game.id)
                    showError = false
                }
                gameStatusInProgress?.id -> {
                    var bundle = Bundle()
                    bundle.putInt("gameID", event.game.id)
                    bundle.putString("gameName", event.game.name)
                    showError = false
                    ActivityOpenManager.openGameActivity(this@MenuFragmentGame.activity!!, bundle, false)
                }
                gameStatusEndGame?.id -> {
                    //TODO - action - do nothing - game was end
                }
                else ->{
                    showError = true
                }
            }

        }

        if(showError){
            Toast.makeText(context, "Sorry...", Toast.LENGTH_SHORT).show()
        }
    }

    /**
     * Open NewGameActivity for specific Game ID
     * @param gameID Int
     */
    private fun openNewGameActivityForGameID(gameID: Int){
        var bundle = Bundle()
        bundle.putInt("gameID", gameID)
        ActivityOpenManager.openNewGameActivity(this@MenuFragmentGame.activity!!, bundle)
    }

    /**
     * Action for 3 dots menu - menu for Game Entity
     * @param event EventOptionSelected
     */
    @Subscribe
    fun registerEventOptionSelected(event: OptionsMenuDialogBottomGame.AdapterBottomMenu.EventOptionSelectedGame){
            dialog.dismiss()
            when(event.option){
                OptionsMenuDialogBottomGame.AdapterBottomMenu.Option.EDIT -> {
                    openNewGameActivityForGameID(gameInteraction!!.id)
                }
                OptionsMenuDialogBottomGame.AdapterBottomMenu.Option.DELETE -> {
                    Toast.makeText(context, "Option DELETE \n Coming soon.", Toast.LENGTH_SHORT).show()
                    // gameViewModel.deleteGame(gameInteraction!!)
                    // gameInteraction = null
                }
            }
    }
}