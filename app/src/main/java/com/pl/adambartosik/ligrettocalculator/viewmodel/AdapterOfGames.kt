package com.pl.adambartosik.ligrettocalculator.viewmodel

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.pl.adambartosik.ligrettocalculator.R
import com.pl.adambartosik.ligrettocalculator.model.tables.Game
import kotlinx.android.synthetic.main.item_game_dashboard.view.*

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
        view = GameItemViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_game_dashboard, parent, false))
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
        }
    }
}