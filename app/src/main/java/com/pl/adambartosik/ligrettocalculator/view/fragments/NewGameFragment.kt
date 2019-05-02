package com.pl.adambartosik.ligrettocalculator.view.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.pl.adambartosik.ligrettocalculator.R
import com.pl.adambartosik.ligrettocalculator.viewmodel.onDialogAnswerResultListener
import kotlinx.android.synthetic.main.new_game_fragment.*

class NewGameFragment: Fragment(){

    companion object {
        fun newInstance() = NewGameFragment()
    }


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        var view =  inflater.inflate(R.layout.new_game_fragment, container, false)

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initText()
        initNumberOfPlayers()
        initInputName()
        initButtonNewPlayer()
        initRV()
    }

    private fun initInputName(){
        textInputLayout.hint = resources.getString(R.string.hint_name_of_the_game)
    }

    private fun initRV(){
        recyclerView_ngf.layoutManager = LinearLayoutManager(this.context, RecyclerView.VERTICAL,false)
    }

    private fun initButtonNewPlayer(){
        button_next_btn_ngf.text = resources.getString(R.string.button_new_player)
    }

    private fun initText(){
        label_player_list_tv_nfg.text = resources.getString(R.string.label_players_list)
    }

    private fun initNumberOfPlayers(){
        number_of_player_tv_ngf.text = "0/${sizeOfMaxPlayers()}"
    }

    private fun sizeOfMaxPlayers(): Int{
        return 12
    }
}