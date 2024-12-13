package com.devmobile.android.journey.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.RecyclerView.VERTICAL
import com.devmobile.android.journey.databinding.FragmentTripHistoryBinding
import com.devmobile.android.journey.view.adapters.RecyclerTripsAdapter
import com.devmobile.android.journey.view.adapters.Trip

class TripHistoryFragment : Fragment() {

    private val binding by lazy {
        FragmentTripHistoryBinding.inflate(layoutInflater)
    }
    private val bottomSheet = CustomBottomSheet()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        setUpTrips()
        binding.fragment = this

        return binding.root
    }

    private fun setUpTrips() {
        val trips: List<Trip> = listOf(
            Trip("New York", "Boston", "John Smith", "2024-12-01", "215 miles", "4h", "$85"),
            Trip(
                "Los Angeles",
                "San Diego",
                "Alice Johnson",
                "2024-12-02",
                "120 miles",
                "2h 30m",
                "$60"
            ),
            Trip("Chicago", "Milwaukee", "Robert Brown", "2024-12-03", "92 miles", "1h 40m", "$45"),
            Trip(
                "San Francisco",
                "Sacramento",
                "Emily Davis",
                "2024-12-04",
                "88 miles",
                "1h 30m",
                "$50"
            ),
            Trip("Houston", "Austin", "Michael Wilson", "2024-12-05", "165 miles", "2h 45m", "$70"),
            Trip("Dallas", "Fort Worth", "Sophia Martinez", "2024-12-06", "30 miles", "40m", "$25"),
            Trip("Miami", "Orlando", "James Taylor", "2024-12-07", "235 miles", "4h", "$90"),
            Trip("Seattle", "Portland", "Linda White", "2024-12-08", "175 miles", "3h", "$80"),
            Trip("Denver", "Boulder", "William Harris", "2024-12-09", "29 miles", "40m", "$20"),
            Trip(
                "Atlanta",
                "Savannah",
                "Olivia Martin",
                "2024-12-10",
                "250 miles",
                "4h 30m",
                "$95"
            ),
            Trip("Phoenix", "Tucson", "David Lee", "2024-12-11", "113 miles", "2h", "$55"),
            Trip(
                "Las Vegas",
                "Grand Canyon",
                "Emma Thompson",
                "2024-12-12",
                "130 miles",
                "2h 30m",
                "$65"
            ),
            Trip(
                "Nashville",
                "Memphis",
                "Daniel Clark",
                "2024-12-13",
                "210 miles",
                "3h 30m",
                "$85"
            ),
            Trip(
                "Philadelphia",
                "Washington D.C.",
                "Mia Hernandez",
                "2024-12-14",
                "140 miles",
                "2h 40m",
                "$75"
            ),
            Trip(
                "San Antonio",
                "Corpus Christi",
                "Benjamin Lewis",
                "2024-12-15",
                "144 miles",
                "2h 15m",
                "$65"
            ),
            Trip(
                "Charlotte",
                "Raleigh",
                "Charlotte Robinson",
                "2024-12-16",
                "170 miles",
                "3h",
                "$80"
            ),
            Trip("Salt Lake City", "Provo", "Noah Walker", "2024-12-17", "45 miles", "50m", "$30"),
            Trip("Portland", "Eugene", "Amelia Hall", "2024-12-18", "110 miles", "2h", "$55"),
            Trip("Detroit", "Ann Arbor", "Alexander Young", "2024-12-19", "43 miles", "50m", "$35"),
            Trip(
                "Cleveland",
                "Pittsburgh",
                "Sophia Allen",
                "2024-12-20",
                "135 miles",
                "2h 20m",
                "$70"
            )

        )

        binding.recyclerTrips.addItemDecoration(DividerItemDecoration(requireContext(), VERTICAL))
        binding.recyclerTrips.adapter = RecyclerTripsAdapter(trips)
    }

    fun onSelectorClick() {
        bottomSheet.show(parentFragmentManager, null)
    }
}