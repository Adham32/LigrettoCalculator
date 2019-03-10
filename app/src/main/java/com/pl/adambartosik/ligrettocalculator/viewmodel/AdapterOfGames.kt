package com.pl.adambartosik.ligrettocalculator.viewmodel

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.pl.adambartosik.ligrettocalculator.R
import com.pl.adambartosik.ligrettocalculator.model.tables.Game

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

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        view = GameItemViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.game_item, parent, false))
        return view
    }

    fun setList(array: List<Game>){
        dataArray = array
        notifyDataSetChanged()
    }

    class GameItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)  {

    }
}