package com.pl.adambartosik.ligrettocalculator.view.fragments.menu

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.Toast
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.pl.adambartosik.ligrettocalculator.R
import com.pl.adambartosik.ligrettocalculator.model.tables.Player
import com.pl.adambartosik.ligrettocalculator.view.dialogFragment.bottom.CreateUserDialogFragmentBottom
import com.pl.adambartosik.ligrettocalculator.view.dialogFragment.bottom.OptionsMenuDialogBottom
import com.pl.adambartosik.ligrettocalculator.viewmodel.adapter.AdapterOfPlayers
import com.pl.adambartosik.ligrettocalculator.viewmodel.PlayerViewModel
import kotlinx.android.synthetic.main.fragment_menu_game_dashboard.button_create_new_game_btn_gmd
import kotlinx.android.synthetic.main.fragment_menu_game_dashboard.players_list_rv_fmpd
import kotlinx.android.synthetic.main.fragment_menu_player_dashboard.*
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe

class MenuFragmentPlayer: Fragment() {


    companion object {
        fun newInstance() =
            MenuFragmentPlayer()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        var view =  inflater.inflate(R.layout.fragment_menu_player_dashboard, container, false)
        EventBus.getDefault().register(this)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initButtonCreateNewPlayer()
        initRecyclerView()
        initViewModels()
        initObserverForPlayers()
    }

    override fun onDestroy() {
        super.onDestroy()
        EventBus.getDefault().unregister(this)
    }

    private var playerInteraction: Player? = null
    private lateinit var adapter: AdapterOfPlayers
    private lateinit var mPlayerViewModel: PlayerViewModel
    private lateinit var dialog: DialogFragment
    private var isErrorState = false

    private fun initButtonCreateNewPlayer(){
        val animation = AnimationUtils.loadAnimation(context, R.anim.click)
        animation.setAnimationListener(object : Animation.AnimationListener{
            override fun onAnimationEnd(animation: Animation?) {
               // open dialog
                dialog = CreateUserDialogFragmentBottom()
                (dialog as CreateUserDialogFragmentBottom).setList(adapter.getDataList())
                dialog.show(this@MenuFragmentPlayer.fragmentManager, "O")
            }
            override fun onAnimationStart(animation: Animation?) { }
            override fun onAnimationRepeat(animation: Animation?) { }
        })

        button_create_new_game_btn_gmd.setOnClickListener {
            button_create_new_game_btn_gmd.startAnimation(animation)
        }
    }

    private fun initViewModels(){
        mPlayerViewModel = ViewModelProviders.of(this.activity!!).get(PlayerViewModel::class.java)
    }

    private fun initRecyclerView(){
        adapter = AdapterOfPlayers()
        players_list_rv_fmpd.layoutManager = LinearLayoutManager(context, RecyclerView.VERTICAL, false)
        players_list_rv_fmpd.adapter = adapter
    }

    private fun initObserverForPlayers(){
        mPlayerViewModel.getAllPlayers().observe(this, Observer { playersList ->
            if(playersList != null){
                if(playersList.isEmpty()){
                    showError(resources.getString(R.string.error_display_no_players))
                }else{
                    cleanError()
                }
                adapter.setData(playersList)
            }
        })
    }

    private fun showError(msg: String){
        isErrorState = true
        error_layout_fl_fmpd.visibility = View.VISIBLE
        error_msg_tv_fmpd.text = msg
    }

    private fun cleanError(){
        if(isErrorState){
            error_layout_fl_fmpd.visibility = View.GONE
            isErrorState = false
        }
    }

    @Subscribe
    fun registerEventCreateUser(event: CreateUserDialogFragmentBottom.EventCreateUser){
        if(mPlayerViewModel.insertNewPlayer(event.name)){
            // operation success
            dialog.dismiss()
        }
    }

    @Subscribe
    fun registerEventGameMenuClicked(event: AdapterOfPlayers.EventMenuPlayerClicked){
        showMenuOfPlayer()
        playerInteraction = event.player
    }

    private fun showMenuOfPlayer() {
        dialog = OptionsMenuDialogBottom()
        dialog.show(this@MenuFragmentPlayer.fragmentManager, "O")
    }

    @Subscribe
    fun registerEventOptionSelected(event: OptionsMenuDialogBottom.AdapterBottomMenu.EventOptionSelected){
        dialog.dismiss()
        when(event.option){
            OptionsMenuDialogBottom.AdapterBottomMenu.Option.EDIT -> {
                Toast.makeText(context, "EDIT - "+ playerInteraction!!.name, Toast.LENGTH_SHORT).show()
            }
            OptionsMenuDialogBottom.AdapterBottomMenu.Option.DELETE -> {
                mPlayerViewModel.deletePlayer(playerInteraction!!)
                playerInteraction = null
            }
        }
    }
}