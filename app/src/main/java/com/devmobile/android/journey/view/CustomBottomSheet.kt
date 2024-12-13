package com.devmobile.android.journey.view

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.RecyclerView.VERTICAL
import com.devmobile.android.journey.R
import com.devmobile.android.journey.databinding.LayoutDriverItemHistoricBinding
import com.devmobile.android.journey.databinding.LayoutDriversBottomSheetBinding
import com.devmobile.android.journey.view.adapters.DriverHistoricItem
import com.devmobile.android.journey.view.adapters.DriverHistoricRecyclerAdapter
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class CustomBottomSheet : BottomSheetDialogFragment() {

    private val binding by lazy {
        LayoutDriversBottomSheetBinding.inflate(layoutInflater)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        setUpDrivers()
        return binding.root
    }

    fun setUpDrivers() {
        val driverTripItems = listOf(
            DriverHistoricItem("John Smith"),
            DriverHistoricItem("Alice Johnson"),
            DriverHistoricItem("Robert Brown"),
            DriverHistoricItem("Emily Davis"),
            DriverHistoricItem("Michael Wilson"),
            DriverHistoricItem("Sophia Martinez"),
            DriverHistoricItem("James Taylor"),
            DriverHistoricItem("Linda White"),
            DriverHistoricItem("William Harris"),
            DriverHistoricItem("Olivia Martin"),
            DriverHistoricItem("David Lee"),
            DriverHistoricItem("Emma Thompson"),
            DriverHistoricItem("Daniel Clark"),
            DriverHistoricItem("Mia Hernandez"),
            DriverHistoricItem("Benjamin Lewis"),
            DriverHistoricItem("Charlotte Robinson"),
            DriverHistoricItem("Noah Walker"),
            DriverHistoricItem("Amelia Hall"),
            DriverHistoricItem("Alexander Young"),
            DriverHistoricItem("Sophia Allen")
        )

        binding.recyclerDriverHistoric.addItemDecoration(
            DividerItemDecoration(
                requireContext(),
                VERTICAL
            )
        )
        binding.recyclerDriverHistoric.adapter =
            DriverHistoricRecyclerAdapter(driverTripItems) { binding ->

                if ((binding as LayoutDriverItemHistoricBinding).imageDone.background == null) {

                    Log.d("DEBUGGING", "CLICOUUUU")
                    binding.imageDone.setBackgroundResource(R.drawable.baseline_done_24)
                } else {
                    Log.d("DEBUGGING", "NULLLLLLLLL")
                    binding.imageDone.background = null
                }
            }
    }
}