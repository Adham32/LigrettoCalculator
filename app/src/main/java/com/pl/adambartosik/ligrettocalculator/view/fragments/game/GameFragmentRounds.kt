package com.pl.adambartosik.ligrettocalculator.view.fragments.game

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.pl.adambartosik.ligrettocalculator.R

class GameFragmentRounds : Fragment() {


    companion object {
        fun newInstance() =
            GameFragmentRounds()
    }


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        try{
            var view =  inflater.inflate(R.layout.fragment_game_rounds, container, false)

        // value_rounds_tv_fgr

        } catch (e :Exception ) {
            Log.e("SS", "onCreateView", e)
            throw e
        }
        return view
    }
}