package com.example.olympik.common.Adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.olympik.R
import com.example.olympik.common.model.Training
import kotlinx.android.synthetic.main.item_training_rv.view.*

class AdapterTraining(private val context: Context, private val training: MutableList<Training>): RecyclerView.Adapter<AdapterTraining.TrainingViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TrainingViewHolder {
        val itemList = LayoutInflater.from(context).inflate(R.layout.item_training_rv, parent, false)
        val holder = TrainingViewHolder(itemList)
        return holder
    }

    override fun onBindViewHolder(holder: TrainingViewHolder, position: Int) {
        holder.iconPlay.setImageResource(training[position].iconPlay)
        holder.trainingName.text = training[position].trainingName
        holder.iconOptions.setImageResource(training[position].iconOptions)
    }

    override fun getItemCount(): Int = training.size

    inner class TrainingViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val iconPlay = itemView.training_btn
        val trainingName = itemView.training_name
        val iconOptions = itemView.btn_edit_training
    }
}