package com.example.olympik.main

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.olympik.R
import com.example.olympik.common.Adapter.AdapterTraining
import com.example.olympik.common.model.Training
import kotlinx.android.synthetic.main.fragment_training_workouts.*

class TrainingWorkoutsFragment : Fragment(R.layout.fragment_training_workouts) {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        rv_training.layoutManager = LinearLayoutManager(requireContext())
        rv_training.setHasFixedSize(true)

        val listTraining: MutableList<Training> = mutableListOf()
        val adapterTraining = AdapterTraining(requireContext(), listTraining)
        rv_training.adapter = adapterTraining

        val training1 = Training(
            R.drawable.ic_play,
            "Peito e tríceps",
            R.drawable.ic_more
        )
        listTraining.add(training1)
    }
}