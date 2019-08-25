package com.pl.adambartosik.ligrettocalculator.viewmodel.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import androidx.recyclerview.widget.RecyclerView
import com.pl.adambartosik.ligrettocalculator.R
import com.pl.adambartosik.ligrettocalculator.model.tables.CardDeck
import com.pl.adambartosik.ligrettocalculator.model.tables.Player
import kotlinx.android.synthetic.main.item_card_deck_to_select.view.*
import kotlinx.android.synthetic.main.item_player_to_select.view.*
import kotlinx.android.synthetic.main.item_player_to_select.view.player_name_tv_ipts
import kotlinx.android.synthetic.main.item_player_to_select_new.view.*
import org.greenrobot.eventbus.EventBus

class AdapterOfCardDeckToSelect : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private var dataArray: List<CardDeck>

    init {
        dataArray = ArrayList()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return CardDeckToSelectViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.item_card_deck_to_select,
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int {
        return dataArray.size
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as CardDeckToSelectViewHolder).bindView(dataArray[position])
    }

    fun setData(playersList: List<CardDeck>) {
        this.dataArray = playersList
        notifyDataSetChanged()
    }

    class CardDeckToSelectViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)  {

        fun bindView(cardDeck: CardDeck){
            itemView.icon_iv_icdts.setImageResource(cardDeck.resID)
            itemView.title_tv_icdts.text = cardDeck.name

            itemView.main_frame_cl_icdts.setOnClickListener {
                val animation = AnimationUtils.loadAnimation(itemView.context, R.anim.click)
                animation.setAnimationListener(object : Animation.AnimationListener {
                    override fun onAnimationStart(animation: Animation) {}

                    override fun onAnimationEnd(animation: Animation) {
                        EventBus.getDefault().post(
                            EventSelectedCardDeckClicked(
                                cardDeck
                            )
                        )
                    }

                    override fun onAnimationRepeat(animation: Animation) {}
                })
                it.startAnimation(animation)
            }
        }
    }



    class EventSelectedCardDeckClicked(val cardDeck: CardDeck?)
}