package com.pl.adambartosik.ligrettocalculator

import android.app.Application
import android.content.Context

class LigrettoCalculator : Application() {

    var context: Context? = null

    override fun onCreate() {
        super.onCreate()
        context = applicationContext
    }


}