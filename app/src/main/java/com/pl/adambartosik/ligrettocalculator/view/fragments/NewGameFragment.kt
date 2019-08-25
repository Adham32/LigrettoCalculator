package com.pl.adambartosik.ligrettocalculator.view.fragments

import android.os.Bundle
import android.os.Handler
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.pl.adambartosik.ligrettocalculator.R
import com.pl.adambartosik.ligrettocalculator.model.tables.Game
import com.pl.adambartosik.ligrettocalculator.viewmodel.GameToPlayerViewModel
import com.pl.adambartosik.ligrettocalculator.viewmodel.GameViewModel
import com.pl.adambartosik.ligrettocalculator.viewmodel.adapter.AdapterOfPlayersInCreateGame
import kotlinx.android.synthetic.main.fragment_game_create_new.*
import java.util.*

class NewGameFragment() : Fragment(){

    private lateinit var currentGame: Game
    private lateinit var gameToPlayerViewModel: GameToPlayerViewModel
    private lateinit var gameViewModel: GameViewModel
    private lateinit var adapter: AdapterOfPlayersInCreateGame
    private var newGameName: String = ""
    private lateinit var extraBundle: Bundle

    companion object {
        fun newInstance() = NewGameFragment()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        var view =  inflater.inflate(R.layout.fragment_game_create_new, container, false)

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initText()
        initNumberOfPlayers(0)
        initInputName()
        initRV()
        initButtonCreateGame()
        gameViewModel = ViewModelProviders.of(this.activity!!).get(GameViewModel::class.java)
        gameToPlayerViewModel = ViewModelProviders.of(this.activity!!).get(GameToPlayerViewModel::class.java)
        observerToGame()
        observerToGTP()
        autoSaveName()
    }

    private fun initInputName(){
        name_of_the_game_til_fgcn.hint = resources.getString(R.string.hint_name_of_the_game)
    }

    private fun initRV(){
        adapter = AdapterOfPlayersInCreateGame()
        recyclerView_ngf.layoutManager = LinearLayoutManager(this.context, RecyclerView.VERTICAL,false)
        recyclerView_ngf.adapter = adapter
    }

    private fun initText(){
        label_player_list_tv_nfg.text = resources.getString(R.string.label_players_list)
    }

    private fun initNumberOfPlayers(num: Int){
        number_of_player_tv_ngf.text = "$num/${sizeOfMaxPlayers()}"
    }

    private fun sizeOfMaxPlayers(): Int{
        return 12
    }

    private fun observerToGTP(){
        gameToPlayerViewModel.getGameToPlayerByGameID(extraBundle.getLong("gameID").toInt()).observe(this, Observer {
            adapter.setData(it)
            initNumberOfPlayers(it.size)
        })
    }

    private fun observerToGame(){
        gameViewModel.getGameByID(extraBundle.getLong("gameID").toInt()).observe(this, Observer {
            if(it != null){
                currentGame = it
                if(it.name != "-"){
                    name_of_the_game_til_fgcn.editText!!.setText(it.name)
                }
            }
        })
    }

    private fun autoSaveName(){
        name_of_the_game_til_fgcn.editText!!.addTextChangedListener(object: TextWatcher{
            var timer: Timer? = null

            override fun afterTextChanged(s: Editable?) {
                    timer = Timer()
                    timer!!.schedule(object: TimerTask() {
                        override fun run() {
                            if(gameViewModel.checkAvailableOfGameName(s.toString())) {
                                currentGame.name = s.toString()
                                gameViewModel.update(currentGame)
                                name_of_the_game_til_fgcn.editText!!.error = null
                            }else{
                                this@NewGameFragment.activity!!.runOnUiThread {
                                    // validation error
                                    name_of_the_game_til_fgcn.editText!!.error = resources.getString(R.string.error_display_game_name_taken)
                                }
                            }
                        }
                    }, 800)
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int){
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                if(timer != null){
                    timer!!.cancel()
                }
            }

        })
    }

    private fun initButtonCreateGame(){
        val animation = AnimationUtils.loadAnimation(context, R.anim.click)
        animation.setAnimationListener(object : Animation.AnimationListener{
            override fun onAnimationEnd(animation: Animation?) {
                 // TODO OPEN NEXT ACTIVITY - GAME
            }
            override fun onAnimationStart(animation: Animation?) { }
            override fun onAnimationRepeat(animation: Animation?) { }
        })

        play_game_btn_ngf.setOnClickListener {
            play_game_btn_ngf.startAnimation(animation)
        }
    }

    fun setBundle(bundle: Bundle) {
        this.extraBundle = bundle
    }
}