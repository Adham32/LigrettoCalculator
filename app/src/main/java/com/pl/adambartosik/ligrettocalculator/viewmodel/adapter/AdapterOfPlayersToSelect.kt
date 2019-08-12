package com.pl.adambartosik.ligrettocalculator.viewmodel.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import androidx.recyclerview.widget.RecyclerView
import com.pl.adambartosik.ligrettocalculator.R
import com.pl.adambartosik.ligrettocalculator.model.tables.Player
import kotlinx.android.synthetic.main.item_player_to_select.view.*
import kotlinx.android.synthetic.main.item_player_to_select.view.player_name_tv_ipts
import kotlinx.android.synthetic.main.item_player_to_select_new.view.*
import org.greenrobot.eventbus.EventBus

class AdapterOfPlayersToSelect : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private var dataArray: List<Player>

    init {
        dataArray = ArrayList()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        var view =
            when(viewType){
                0 ->
                    AddNewPlayerViewHolder(
                        LayoutInflater.from(parent.context).inflate(
                            R.layout.item_player_to_select_new,
                            parent,
                            false
                        )
                    )

                else ->
                    PlayerToSelectViewHolder(
                        LayoutInflater.from(parent.context).inflate(
                            R.layout.item_player_to_select,
                            parent,
                            false
                        )
                    )

            }
        return view
    }

    override fun getItemCount(): Int {
        return dataArray.size + 1
    }

    override fun getItemViewType(position: Int): Int {
        return when (position) {
            dataArray.size-> 0
            else -> 1
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when(holder){
            is PlayerToSelectViewHolder -> {
                holder.bindView(dataArray[position])
            }
            is AddNewPlayerViewHolder -> {
                holder.bindView()
            }
        }
    }

    fun setData(playersList: List<Player>) {
        this.dataArray = playersList
        notifyDataSetChanged()
    }

    class PlayerToSelectViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)  {

        fun bindView(player: Player){
            itemView.player_name_tv_ipts.text = player.name

            itemView.main_frame_cl_ipts.setOnClickListener {
                val animation = AnimationUtils.loadAnimation(itemView.context, R.anim.click)
                animation.setAnimationListener(object : Animation.AnimationListener {
                    override fun onAnimationStart(animation: Animation) {}

                    override fun onAnimationEnd(animation: Animation) {
                        EventBus.getDefault().post(
                            EventSelectedPlayerClicked(
                                player
                            )
                        )
                    }

                    override fun onAnimationRepeat(animation: Animation) {}
                })
                it.startAnimation(animation)
            }
        }
    }

    class AddNewPlayerViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)  {
        fun bindView(){
            itemView.main_frame_cl_iptsn.setOnClickListener {
                val animation = AnimationUtils.loadAnimation(itemView.context, R.anim.click)
                animation.setAnimationListener(object : Animation.AnimationListener {
                    override fun onAnimationStart(animation: Animation) {}

                    override fun onAnimationEnd(animation: Animation) {
                        EventBus.getDefault().post(
                            EventSelectedPlayerClicked(null)
                        )
                    }

                    override fun onAnimationRepeat(animation: Animation) {}
                })
                it.startAnimation(animation)
            }
        }
    }

    class EventSelectedPlayerClicked(val player: Player?)
}