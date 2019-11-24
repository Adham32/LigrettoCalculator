package com.pl.adambartosik.ligrettocalculator.view.activites

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import androidx.lifecycle.ViewModelProviders
import com.pl.adambartosik.ligrettocalculator.R
import com.pl.adambartosik.ligrettocalculator.view.fragments.game.GameFragmentPlayerScoreList
import com.pl.adambartosik.ligrettocalculator.view.fragments.game.GameFragmentRounds
import com.pl.adambartosik.ligrettocalculator.viewmodel.GameRoundViewModel
import com.pl.adambartosik.ligrettocalculator.viewmodel.GameToPlayerToGameRoundViewModel
import com.pl.adambartosik.ligrettocalculator.viewmodel.GameToPlayerViewModel
import kotlinx.android.synthetic.main.activity_game.*
import kotlinx.android.synthetic.main.fragment_game_create_new.*

class GameActivity : AppCompatActivity() {

    private lateinit var bundleGameName: String
    private var bundleGameID: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_game)
        if(this.intent.extras != null){
            checkBundle(intent.getBundleExtra("gameBundle"))
        }

        supportFragmentManager.beginTransaction().replace(fragment.id, GameFragmentRounds.newInstance()).commit()
        supportFragmentManager.beginTransaction().replace(fragment2.id, GameFragmentPlayerScoreList.newInstance()).commit()


        // set icon arrow back
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)

        setActivityTitle(bundleGameName)

        addListenToButtons()

    }

    private fun addListenToButtons() {
        val animation = AnimationUtils.loadAnimation(applicationContext, R.anim.click)
        animation.setAnimationListener(object : Animation.AnimationListener{
            override fun onAnimationEnd(animation: Animation?) {
               //TODO OPEN DIALOG TO ENTER PLAYERS SCORE


                var gameToPlayerViewModel = ViewModelProviders.of(this@GameActivity).get(GameToPlayerToGameRoundViewModel::class.java)
                var gameRoundViewModel = ViewModelProviders.of(this@GameActivity).get(GameRoundViewModel::class.java)

                gameRoundViewModel.insert(
                    "Round_01",
                    bundleGameID
                )

                gameToPlayerViewModel.insertNewGameToPlayerToRound(
                    1,
                    1,
                    15
                )
                gameToPlayerViewModel.insertNewGameToPlayerToRound(
                    1,
                    1,
                    20
                )
                gameToPlayerViewModel.insertNewGameToPlayerToRound(
                    1,
                    1,
                    -12
                )
            }
            override fun onAnimationStart(animation: Animation?) { }
            override fun onAnimationRepeat(animation: Animation?) { }
        })

        add_round_score_btn_ag.setOnClickListener {
            add_round_score_btn_ag.startAnimation(animation)
        }
    }

    /**
     * Receive and set data from Bundle
     * @param bundle Bundle?
     */
    private fun checkBundle(bundle: Bundle) {
        bundleGameID = bundle.getInt("gameID")
        bundleGameName = bundle.getString("gameName")!!
    }

    /**
     * Set title of activity in action bar
     * @param name String - title
     */
    private fun setActivityTitle(name: String){
        title = name
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                onBackPressed()
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }


    ////////////////////////////////////////////////////////////////////////////////////////////////
    //// NO NEED
    ////////////////////////////////////////////////////////////////////////////////////////////////


    /*

    private lateinit var gameViewModel: GameViewModel

    private fun setObservatory(){
        gameViewModel = ViewModelProviders.of(this).get(GameViewModel::class.java)
        gameViewModel.getGameByID(bundleGameID).observe(this, Observer {
            if(it != null){
                setActivityTitle(it.name)
            }
        })
    }

    */

}
