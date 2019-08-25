package com.pl.adambartosik.ligrettocalculator.view.activites

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import androidx.fragment.app.DialogFragment
import androidx.lifecycle.ViewModelProviders
import com.pl.adambartosik.ligrettocalculator.R
import com.pl.adambartosik.ligrettocalculator.model.tables.GameToPlayer
import com.pl.adambartosik.ligrettocalculator.view.dialogFragment.bottom.CreateUserDialogFragmentBottom
import com.pl.adambartosik.ligrettocalculator.view.dialogFragment.bottom.SelectCardDeckDialogFragmentBottom
import com.pl.adambartosik.ligrettocalculator.view.dialogFragment.bottom.SelectPlayerDialogFragmentBottom
import com.pl.adambartosik.ligrettocalculator.view.fragments.NewGameFragment
import com.pl.adambartosik.ligrettocalculator.viewmodel.GameToPlayerViewModel
import com.pl.adambartosik.ligrettocalculator.viewmodel.GameViewModel
import com.pl.adambartosik.ligrettocalculator.viewmodel.adapter.AdapterOfCardDeckToSelect
import com.pl.adambartosik.ligrettocalculator.viewmodel.adapter.AdapterOfPlayersInCreateGame
import com.pl.adambartosik.ligrettocalculator.viewmodel.adapter.AdapterOfPlayersToSelect
import kotlinx.android.synthetic.main.activity_create.*
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe

class CreateActivity : AppCompatActivity() {

    private lateinit var gameToPlayerViewModel: GameToPlayerViewModel
    private var gameId: Long = 0
    private var playerID: Int = 0
    private lateinit var gameViewModel: GameViewModel
    private lateinit var dialog: DialogFragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create)

        EventBus.getDefault().register(this@CreateActivity)
        // set icon arrow back
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)

        initialButtonNext()
        setTitleName()
        gameViewModel = ViewModelProviders.of(this).get(GameViewModel::class.java)
        createNewGame()
        gameToPlayerViewModel = ViewModelProviders.of(this).get(GameToPlayerViewModel::class.java)
    }

    private fun setTitleName(){
        setTitle(R.string.title_new_game)
    }

    private fun createNewGame(){
        gameId = gameViewModel.insertNewGame()

        var b = Bundle()
        b.putLong("gameID", gameId)

        setFragment(b)
    }

    private fun setFragment(bundle: Bundle?) {
        var fragment = NewGameFragment.newInstance()
        if(bundle != null){
            fragment.setBundle(bundle)
        }

        supportFragmentManager.beginTransaction().add(container_fl_ac.id, fragment).commit()
    }

    private fun initialButtonNext() {
        val animation = AnimationUtils.loadAnimation( this@CreateActivity.applicationContext, R.anim.click)
        animation.setAnimationListener(object : Animation.AnimationListener{
            override fun onAnimationEnd(animation: Animation?) {
               closeActivity()
            }
            override fun onAnimationStart(animation: Animation?) { }
            override fun onAnimationRepeat(animation: Animation?) { }
        })

        /*
        button_next_btn_ac.setOnClickListener {
        button_next_btn_ac.startAnimation(animation)
        }
        */
    }

    private fun closeActivity(){
        finish()
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


    @Subscribe
    fun reciveEventOpenSelectPlayerDialog( event: AdapterOfPlayersInCreateGame.EventOpenSelectPlayerDialog){
        // open dialog sheet bottom
        dialog = SelectPlayerDialogFragmentBottom()
        dialog.show(supportFragmentManager, "O")
    }

    @Subscribe
    fun reciveEventSelectedPlayerClicked( event: AdapterOfPlayersToSelect.EventSelectedPlayerClicked){
        // close dialog, get player
        var player = event.player

        if(player == null){
            dialog.dismiss()
            this@CreateActivity.onBackPressed()
        }else{
            playerID = player.id
            dialog.dismiss()
            openDialogSelectCardDeck()
        }
    }

    private fun openDialogSelectCardDeck(){
        dialog = SelectCardDeckDialogFragmentBottom()
        dialog.show(supportFragmentManager, "O")
    }


    @Subscribe
    fun reciveEventSelectedCardDeckClicked(event: AdapterOfCardDeckToSelect.EventSelectedCardDeckClicked){
        var cardDeck = event.cardDeck

        if(cardDeck == null){
            dialog.dismiss()
            this@CreateActivity.onBackPressed()
        }else{
            dialog.dismiss()
            insertNewGameToPlayer(cardDeck.id)
        }
    }

    private fun insertNewGameToPlayer(cardDeckID: Int){
        if(gameId != 0L){
            gameToPlayerViewModel.insertNewGameToPlayer(gameId.toInt(), playerID, cardDeckID)
        }
    }



}
