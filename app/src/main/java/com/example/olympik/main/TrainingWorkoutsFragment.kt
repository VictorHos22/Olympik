package com.example.olympik.main

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.olympik.R
import com.example.olympik.common.Adapter.AdapterTraining
import com.example.olympik.common.model.Training
import com.example.olympik.register.FragmentAttachListener
import kotlinx.android.synthetic.main.fragment_training_workouts.*

class TrainingWorkoutsFragment : Fragment(R.layout.fragment_training_workouts) {

    private var fragmentAttachListener : MainFragmentAttachListener? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        rv_training.layoutManager = LinearLayoutManager(context)
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
        training_fab_add.setOnClickListener{
            Log.d("TAG", "onViewCreated: dentro do onclick")
            fragmentAttachListener?.goToCreateNewTraining()
        }

    }
    override fun onAttach(context: Context) {
        super.onAttach(context)
        if(context is MainFragmentAttachListener){
            fragmentAttachListener = context
        }
    }

}
