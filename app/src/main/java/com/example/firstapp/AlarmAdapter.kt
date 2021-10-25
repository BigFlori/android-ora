package com.example.firstapp

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Switch
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class AlarmAdapter(private val alarmList: List<AlarmItem>) : RecyclerView.Adapter<AlarmAdapter.AlarmViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AlarmViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.alarm_item, parent, false)
        return AlarmViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: AlarmViewHolder, position: Int) {
        val currentItem = alarmList[position]
        holder.alarmDate.text = convertNumber(currentItem.hour) + ":" + convertNumber(currentItem.min)
        holder.alarmType.text = currentItem.type
        holder.isActive.isChecked = currentItem.isActive
    }

    override fun getItemCount() = alarmList.size

    private fun convertNumber(number: Int): String {
        if(number < 10 && number > -1)
            return "0$number"
        else
            return "$number"
    }

    class AlarmViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val alarmDate: TextView = itemView.findViewById(R.id.alarmDate)
        val alarmType: TextView = itemView.findViewById(R.id.alarmType)
        val isActive: Switch = itemView.findViewById(R.id.alarmSwitch)
    }
}