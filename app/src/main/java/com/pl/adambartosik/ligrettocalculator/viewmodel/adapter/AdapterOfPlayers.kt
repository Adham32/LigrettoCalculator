package com.pl.adambartosik.ligrettocalculator.viewmodel.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import androidx.recyclerview.widget.RecyclerView
import com.pl.adambartosik.ligrettocalculator.R
import com.pl.adambartosik.ligrettocalculator.model.tables.Player
import kotlinx.android.synthetic.main.item_game_dashboard.view.*
import kotlinx.android.synthetic.main.item_player_dashboard.view.*
import org.greenrobot.eventbus.EventBus

class AdapterOfPlayers : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private var dataArray: List<Player>

    init {
        dataArray = ArrayList()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        var view = PlayerItemViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.item_player_dashboard,
                parent,
                false
            )
        )
        return view
    }

    override fun getItemCount(): Int {
        return dataArray.size
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if(holder is PlayerItemViewHolder){
            holder.bindView(dataArray[position])
        }
    }


    fun setData(playersList: List<Player>) {
        this.dataArray = playersList
        notifyDataSetChanged()
    }

    fun getDataList(): List<Player> {
        return dataArray
    }


    class PlayerItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)  {

        fun bindView(player: Player){
            itemView.player_name_tv_ipd.text = player.name

            itemView.icon_menu_fl_ipd.setOnClickListener {
                val animation = AnimationUtils.loadAnimation(itemView.context, R.anim.click)
                animation.setAnimationListener(object : Animation.AnimationListener {
                    override fun onAnimationStart(animation: Animation) {}

                    override fun onAnimationEnd(animation: Animation) {
                        EventBus.getDefault().post(
                            EventMenuPlayerClicked(
                                player
                            )
                        )
                    }

                    override fun onAnimationRepeat(animation: Animation) {}
                })
                itemView.icon_menu_fl_gi.startAnimation(animation)
            }
        }
    }

    class EventMenuPlayerClicked(val player: Player)
}