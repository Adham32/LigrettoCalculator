package com.pl.adambartosik.ligrettocalculator.view.activites

import android.app.Activity
import android.content.Intent
import android.os.Bundle
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

        fun openNewGameActivity(activity: Activity, bundle: Bundle?) {
            Log.i(activity.getString(R.string.tag_activity_open_manager), "Process open activity: CreateActivity")
            val intent = Intent(activity, CreateActivity::class.java)
            if(bundle != null){
                intent.putExtra("gameBundle", bundle)
            }
            activity.startActivity(intent)
        }
    }


}