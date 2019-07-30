package com.pl.adambartosik.ligrettocalculator.viewmodel.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import androidx.recyclerview.widget.RecyclerView
import com.pl.adambartosik.ligrettocalculator.R
import com.pl.adambartosik.ligrettocalculator.model.tables.Game
import kotlinx.android.synthetic.main.item_game_dashboard.view.*
import org.greenrobot.eventbus.EventBus

class AdapterOfGames : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private lateinit var view: GameItemViewHolder
    private var dataArray: List<Game>

    init {
        dataArray = ArrayList()
    }

    override fun getItemCount(): Int {
        return dataArray.size
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if(holder is GameItemViewHolder){
            holder.bindView(dataArray[position])
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        view = GameItemViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_game_dashboard, parent, false)
        )
        return view
    }

    fun setList(array: List<Game>){
        dataArray = array
        notifyDataSetChanged()
    }

    class GameItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)  {

        fun bindView(game: Game){
            itemView.title_of_game_tv_igd.text = game.name
            itemView.time_creation_tv_igd.text = game.createdAtInMilis.toString()

            itemView.icon_menu_fl_gi.icon_menu_fl_gi.setOnClickListener {
                val animation = AnimationUtils.loadAnimation(itemView.context, R.anim.click)
                animation.setAnimationListener(object : Animation.AnimationListener {
                    override fun onAnimationStart(animation: Animation) {}

                    override fun onAnimationEnd(animation: Animation) {
                        EventBus.getDefault().post(
                            EventMenuGameClicked(
                                game
                            )
                        )
                    }

                    override fun onAnimationRepeat(animation: Animation) {}
                })
                itemView.icon_menu_fl_gi.startAnimation(animation)
            }
        }
    }

    class EventMenuGameClicked(val game: Game)
}