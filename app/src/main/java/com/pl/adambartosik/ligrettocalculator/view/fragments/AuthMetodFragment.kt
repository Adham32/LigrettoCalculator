package com.pl.adambartosik.ligrettocalculator.view.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.pl.adambartosik.ligrettocalculator.R
import com.pl.adambartosik.ligrettocalculator.viewmodel.StarterViewModel


class AuthMetodFragment : Fragment() {

    private lateinit var starterViewModel: StarterViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle? ): View? {
        // Inflate the layout for this fragment

        // WHEN AUTH BE CONNECTED:
        // return inflater.inflate(R.layout.fragment_welcome_auth_metod, container, false)
        starterViewModel = ViewModelProviders.of(this).get(StarterViewModel::class.java)
        return inflater.inflate(R.layout.fragment_welcome_just_play, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        generateThings()
    }

    private fun generateThings(){
        starterViewModel.gameStatusArray.observe( this, Observer {
            if(it != null){
                if(it.isEmpty()){
                    starterViewModel.generateStatus()
                }
            }
        })
    }

}
