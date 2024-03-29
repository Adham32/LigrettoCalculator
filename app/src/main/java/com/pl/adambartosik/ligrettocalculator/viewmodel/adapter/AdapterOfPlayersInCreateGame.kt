package com.pl.adambartosik.ligrettocalculator.viewmodel.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import androidx.recyclerview.widget.RecyclerView
import com.pl.adambartosik.ligrettocalculator.LigrettoCalculator
import com.pl.adambartosik.ligrettocalculator.R
import com.pl.adambartosik.ligrettocalculator.model.entity.GameToPlayerEntity
import kotlinx.android.synthetic.main.item_player_new_game.view.*
import kotlinx.android.synthetic.main.item_player_new_game_add.view.*
import org.greenrobot.eventbus.EventBus

class AdapterOfPlayersInCreateGame : RecyclerView.Adapter<RecyclerView.ViewHolder>()  {

    private var dataArray: List<GameToPlayerEntity>

    init {
        dataArray = ArrayList()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        var view =
            when(viewType) {
                0 -> AddNewPlayerViewHolder (
                        LayoutInflater.from(parent.context).inflate(
                        R.layout.item_player_new_game_add,
                        parent,
                        false)
                    )
                else -> GameToPlayerViewHolder(
                LayoutInflater.from(parent.context).inflate(
                    R.layout.item_player_new_game,
                    parent,
                    false)
                )
            }
        return view

    }

    override fun getItemCount(): Int {
        return dataArray.size + 1
    }

    fun setData(playersList: List<GameToPlayerEntity>) {
        this.dataArray = playersList
        notifyDataSetChanged()
    }

    override fun getItemViewType(position: Int): Int {
        return when (position) {
            0 -> 0
            else -> 1
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when(holder){
            is GameToPlayerViewHolder -> {
                holder.bindView(dataArray[position-1])
            }
            is AddNewPlayerViewHolder -> {
                holder.bindView()
            }
        }
    }

    class GameToPlayerViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)  {

        fun bindView(gameToPlayer: GameToPlayerEntity){
            itemView.player_name_tv_ipng.text = gameToPlayer.player!!.name
            itemView.card_deck_icon_iv_ipng.setImageResource(gameToPlayer.cardDeck!!.resID)
        }
    }

    class AddNewPlayerViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bindView() {
            val animation = AnimationUtils.loadAnimation(LigrettoCalculator.getContext(), R.anim.click)
            animation.setAnimationListener(object : Animation.AnimationListener{
                override fun onAnimationEnd(animation: Animation?) {
                    EventBus.getDefault().post(EventOpenSelectPlayerDialog())
                }
                override fun onAnimationStart(animation: Animation?) { }
                override fun onAnimationRepeat(animation: Animation?) { }
            })

            itemView.main_cv_ipnga.setOnClickListener {
                it.startAnimation(animation)
            }
        }
    }

    class EventOpenSelectPlayerDialog()
}