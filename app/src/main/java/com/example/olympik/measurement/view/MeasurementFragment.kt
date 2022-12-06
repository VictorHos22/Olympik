package com.example.olympik.measurement.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.olympik.R
import com.example.olympik.common.Adapter.AdapterMeasurement
import com.example.olympik.common.model.Measurement
import kotlinx.android.synthetic.main.fragment_measurement.*

class MeasurementFragment : Fragment(R.layout.fragment_measurement) {
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        measurements_rv.layoutManager = LinearLayoutManager(context)
        measurements_rv.setHasFixedSize(true)

        val listMeasurement: MutableList<Measurement> = mutableListOf()
        val adapterMeasurement = AdapterMeasurement(requireContext(), listMeasurement)
        measurements_rv.adapter = adapterMeasurement

        val measurement1 = Measurement(
            "Medidas",
        "Primeiras medidas",
            "01/04/2022"
        )
        val measurement2 = Measurement(
            "Medidas",
            "Medidas depois de 3 meses",
            "01/07/2022"
        )
        listMeasurement.add(measurement1)
        listMeasurement.add(measurement2)
    }
}