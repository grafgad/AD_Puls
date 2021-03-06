package com.example.adpuls.ui.advalue

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.adpuls.R
import com.example.adpuls.model.Value

class ValueAdapter : RecyclerView.Adapter<ValueAdapter.ValueViewHolder>() {

    private var valueList = emptyList<Value>()

    fun setData(list: List<Value>){
        valueList = list
    }

    class ValueViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        private val pressure = itemView.findViewById<TextView>(R.id.blood_pressure)
        private val pulse = itemView.findViewById<TextView>(R.id.pulse)
        private val date = itemView.findViewById<TextView>(R.id.date)
        fun onBind(value: Value){
            pressure.text = value.bloodPressure
            pulse.text = value.pulse
            date.text = value.date
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ValueViewHolder {
        val view =LayoutInflater.from(parent.context).inflate(R.layout.item_value, parent, false )
        return ValueViewHolder(view)
    }

    override fun onBindViewHolder(holder: ValueViewHolder, position: Int) {
        holder.onBind(valueList[position])
    }

    override fun getItemCount() = valueList.size
}