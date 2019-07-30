package com.pl.adambartosik.ligrettocalculator

import android.app.Application
import android.content.Context

class LigrettoCalculator : Application() {


    init {
        instance = this
    }

    companion object {
        private var instance: LigrettoCalculator? = null

        fun getContext() : Context {
            return instance!!.applicationContext
        }
    }


}