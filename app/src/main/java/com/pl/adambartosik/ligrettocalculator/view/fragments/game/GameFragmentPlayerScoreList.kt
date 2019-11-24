package com.pl.adambartosik.ligrettocalculator.view.fragments.game

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.pl.adambartosik.ligrettocalculator.R
import com.pl.adambartosik.ligrettocalculator.viewmodel.GameRoundViewModel
import com.pl.adambartosik.ligrettocalculator.viewmodel.GameToPlayerToGameRoundViewModel
import com.pl.adambartosik.ligrettocalculator.viewmodel.GameToPlayerViewModel
import kotlinx.android.synthetic.main.fragment_game_player_score_list.*

class GameFragmentPlayerScoreList : Fragment() {


    companion object {
        fun newInstance() =
            GameFragmentPlayerScoreList()
    }



    private lateinit var gameToPlayerToGameRoundViewModel: GameToPlayerToGameRoundViewModel
    val STATUS_LOADING = 1
    val STATUS_NORMAL = 2

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        var view =  inflater.inflate(R.layout.fragment_game_player_score_list, container, false)



        var gameToPlayerViewModel = ViewModelProviders.of(this.activity!!).get(GameToPlayerViewModel::class.java)


        gameToPlayerViewModel.getGameToPlayerByGameID(1).observe(this, Observer { list ->
            if(list != null){
                list.forEach {
                    var playerRoundsToGameToPlayer = it.playerRoundsToGameToPlayer
                }
            }
        })

      /*  gameToPlayerToGameRoundViewModel = ViewModelProviders.of(this.activity!!).get(GameToPlayerToGameRoundViewModel::class.java)

        gameToPlayerToGameRoundViewModel.getGameToPlayerByGameID().observe(this, Observer { list ->
            if (list != null){
                if(list.isEmpty()){

                }else{
                    list.forEach {
                        var x = it.gameToPlayer!!.player!!.name
                    }
                }

            }
        })*/

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setViewState(STATUS_LOADING)
    }

    private fun setViewState(status: Int) {
        when(status){
            STATUS_LOADING -> {
                state_loading_cl_fgpsl.visibility = View.VISIBLE
                state_normal_cl_fgpsl.visibility = View.GONE
            }
            STATUS_NORMAL -> {
                state_loading_cl_fgpsl.visibility = View.GONE
                state_normal_cl_fgpsl.visibility = View.VISIBLE
            }
        }

    }
}