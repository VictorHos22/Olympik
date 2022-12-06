package com.example.olympik.common.Adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.olympik.R
import com.example.olympik.common.model.Measurement
import com.example.olympik.common.model.UserMeasurements
import com.example.olympik.measurement.view.MeasurementFragment
import kotlinx.android.synthetic.main.item_measurement_rv.view.*

class AdapterMeasurement(private val context: Context, private val measurement:
    MutableList<Measurement>) :RecyclerView.Adapter<AdapterMeasurement.MeasurementViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MeasurementViewHolder {
        val itemList = LayoutInflater.from(context).inflate(R.layout.item_measurement_rv,
        parent, false)
        val holder = MeasurementViewHolder(itemList)
        return holder
    }

    override fun onBindViewHolder(holder:MeasurementViewHolder, position: Int) {
        holder.txtMeasurement.text = measurement[position].measurement
        holder.txtSmallDescription.text = measurement[position].smallDescription
        holder.txtDate.text = measurement[position].date
    }

    override fun getItemCount(): Int = measurement.size

    inner class MeasurementViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        val txtMeasurement = itemView.txt_measurements
        val txtSmallDescription = itemView.txt_small_description
        val txtDate = itemView.txt_date
    }
}

