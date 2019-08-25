package com.pl.adambartosik.ligrettocalculator.viewmodel.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import androidx.recyclerview.widget.RecyclerView
import com.pl.adambartosik.ligrettocalculator.LigrettoCalculator
import com.pl.adambartosik.ligrettocalculator.R
import com.pl.adambartosik.ligrettocalculator.model.entity.GameEntity
import com.pl.adambartosik.ligrettocalculator.model.tables.Game
import kotlinx.android.synthetic.main.item_game_dashboard.view.*
import org.greenrobot.eventbus.EventBus

class AdapterOfGames : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private lateinit var dataArray: List<GameEntity>
    private lateinit var view: GameItemViewHolder

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

    fun setList(array: List<GameEntity>) {
        dataArray = array
        notifyDataSetChanged()
    }

    class GameItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)  {

        fun bindView(game: GameEntity){
            val animation = AnimationUtils.loadAnimation(itemView.context, R.anim.click)
            itemView.title_of_game_tv_igd.text = game.game!!.name
            itemView.time_creation_tv_igd.text = game.game!!.createdAtInMilis.toString()
            itemView.number_of_rounds_tv_igd.text = game.gameRoundList!!.size.toString()
            itemView.number_of_players_tv_igd.text = LigrettoCalculator.getContext().getString(R.string.label_players, game.gameToPlayerList!!.size)

            // 3 dots - game menu clicked
            itemView.icon_menu_fl_gi.icon_menu_fl_gi.setOnClickListener {
                animation.setAnimationListener(object : Animation.AnimationListener {
                    override fun onAnimationStart(animation: Animation) {}

                    override fun onAnimationEnd(animation: Animation) {
                        EventBus.getDefault().post(
                            EventMenuGameClicked(
                                game.game!!
                            )
                        )
                    }

                    override fun onAnimationRepeat(animation: Animation) {}
                })
                itemView.icon_menu_fl_gi.startAnimation(animation)
            }

            // card view - game entity clicked
            itemView.game_entity_cv_igd.setOnClickListener {

                animation.setAnimationListener(object : Animation.AnimationListener {
                    override fun onAnimationStart(animation: Animation) {}

                    override fun onAnimationEnd(animation: Animation) {
                        EventBus.getDefault().post(
                            EventGameClicked(
                                game.game!!
                            )
                        )
                    }

                    override fun onAnimationRepeat(animation: Animation) {}
                })
                itemView.game_entity_cv_igd.startAnimation(animation)
            }

        }
    }

    class EventMenuGameClicked(val game: Game)
    class EventGameClicked(val game: Game)
}