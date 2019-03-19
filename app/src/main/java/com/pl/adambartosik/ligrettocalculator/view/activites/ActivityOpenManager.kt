package com.pl.adambartosik.ligrettocalculator.view.activites

import android.app.Activity
import android.content.Intent
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.pl.adambartosik.ligrettocalculator.R

abstract class ActivityOpenManager {

    companion object {

        fun openMainActivity(activity: AppCompatActivity){
            Log.i(activity.getString(R.string.tag_activity_open_manager), "Process open activity: MainActivity")
            val intent = Intent(activity, MainActivity::class.java)
            activity.startActivity(intent)
            activity.finish()
        }

        fun openNewGameActivity(activity: Activity) {
            Log.i(activity.getString(R.string.tag_activity_open_manager), "Process open activity: CreateActivity")
            val intent = Intent(activity, CreateActivity::class.java)
            intent.putExtra("option", 1)
            activity.startActivity(intent)
        }
    }


}