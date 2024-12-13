package com.devmobile.android.journey.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.navArgs
import com.devmobile.android.journey.data.TripsAvailableRemoteRepository
import com.devmobile.android.journey.data.network.TripApiService
import com.devmobile.android.journey.databinding.FragmentTripOptionsBinding
import com.devmobile.android.journey.usecase.Driver
import com.devmobile.android.journey.view.adapters.DriversRecyclerAdapter
import com.devmobile.android.journey.viewmodel.TripOptionsViewModel
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch

class TripOptionsFragment : Fragment() {

    private val binding by lazy {
        FragmentTripOptionsBinding.inflate(layoutInflater)
    }
    private val repository by lazy {
        TripsAvailableRemoteRepository.getInstance(TripApiService.service)
    }
    private val viewModel: TripOptionsViewModel by viewModels {
        TripOptionsViewModel.provideFactory(repository)
    }
    private val safeArgs: TripOptionsFragmentArgs by navArgs()

    private val dispatcher = Dispatchers.Default

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding.fragment = this
        binding.mapView.onCreate(null)
        binding.mapView.onResume()
        setUpObservable()
        setUpMap(LatLng(0.0, 0.0))

        return binding.root
    }

    private fun setUpObservable() {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {

                launch {
                    viewModel.routes.collect {
                        setUpMap(LatLng(it.originLat.toDouble(), it.originLong.toDouble()))
                        setUpMap(LatLng(it.destinationLat.toDouble(), it.destinationLong.toDouble()))
                    }
                }

                launch {

                    viewModel.drivers.collect {
                        setUpDrivers(it)
                    }
                }
            }
        }
    }

    private fun setUpDrivers(drivers: List<Driver>) {
        lifecycleScope.launch(dispatcher) {
            binding.recyclerView.adapter = DriversRecyclerAdapter(drivers) {
                viewModel.confirmNewTrip(safeArgs.customerId)
            }
        }
    }

    private fun setUpMap(lat: LatLng) {
        lifecycleScope.launch {
            binding.mapView.getMapAsync {
                it.addMarker(
                    MarkerOptions().position(LatLng(0.0, 0.0))
                )
                it.addMarker(
                    MarkerOptions().position(LatLng(0.1, 1.0))
                )
            }
        }
    }

    override fun onResume() {
        super.onResume()
        binding.mapView.onResume()
    }

    override fun onPause() {
        super.onPause()
        binding.mapView.onPause()
    }

    override fun onDestroy() {
        super.onDestroy()
        binding.mapView.onDestroy()
    }

    override fun onLowMemory() {
        super.onLowMemory()
        binding.mapView.onLowMemory()
    }
}