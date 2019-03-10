package com.pl.adambartosik.ligrettocalculator.view.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.pl.adambartosik.ligrettocalculator.R

class NewGameFragment: Fragment(){


    companion object {
        fun newInstance() = NewGameFragment()
    }


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        var view =  inflater.inflate(R.layout.new_game_fragment, container, false)

        return view
    }
}