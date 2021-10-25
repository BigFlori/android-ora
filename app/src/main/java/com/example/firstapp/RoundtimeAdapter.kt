package com.example.firstapp

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class RoundtimeAdapter(private val roundtimeList: List<RoundtimeItem>) : RecyclerView.Adapter<RoundtimeAdapter.RoundtimeViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RoundtimeViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.roundtime_item, parent, false)
        return RoundtimeViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: RoundtimeViewHolder, position: Int) {
        val currentItem = roundtimeList[position]
        holder.roundtimeIndex.text = convertNumber(currentItem.index)
        holder.roundtimeDate.text = currentItem.date
    }

    override fun getItemCount() = roundtimeList.size

    private fun convertNumber(number: Int): String {
        if(number < 10 && number > -1)
            return "0$number"
        else
            return "$number"
    }

    class RoundtimeViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val roundtimeIndex: TextView = itemView.findViewById(R.id.roundtime_index)
        val roundtimeDate: TextView = itemView.findViewById(R.id.roundtime_date)
    }
}