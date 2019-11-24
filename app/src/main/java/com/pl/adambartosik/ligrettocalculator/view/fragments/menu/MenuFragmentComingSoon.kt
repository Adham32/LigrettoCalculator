package com.pl.adambartosik.ligrettocalculator.view.fragments.menu

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.pl.adambartosik.ligrettocalculator.R

class MenuFragmentComingSoon: Fragment() {

    companion object {
        fun newInstance() =
            MenuFragmentComingSoon()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        var view =  inflater.inflate(R.layout.fragment_menu_coming_soon, container, false)
        return view
    }
}