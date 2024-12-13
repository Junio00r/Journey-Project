package com.devmobile.android.journey.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.widget.doAfterTextChanged
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import com.devmobile.android.journey.R
import com.devmobile.android.journey.data.network.TripApiService
import com.devmobile.android.journey.data.TripsAvailableRemoteRepository
import com.devmobile.android.journey.databinding.FragmentTripSolicitationBinding
import com.devmobile.android.journey.usecase.OperationStateResult
import com.devmobile.android.journey.viewmodel.TripSolicitationViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class TripSolicitationFragment : Fragment() {

    private val binding by lazy {
        FragmentTripSolicitationBinding.inflate(layoutInflater)
    }
    private val viewModel: TripSolicitationViewModel by viewModels {
        TripSolicitationViewModel.provideFactory(repository = repository, this)
    }
    private val repository by lazy {
        TripsAvailableRemoteRepository.getInstance(TripApiService.service)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding.viewModel = viewModel
        setUpObservables()

        return binding.root
    }

    private fun setUpObservables() {

        lifecycleScope.launch(Dispatchers.Default) {
            repeatOnLifecycle(Lifecycle.State.STARTED) {

                binding.editTextUserId.doAfterTextChanged {
                    viewModel.updateUIState(newUserID = it.toString())
                }
                binding.editTextOriginAddress.doAfterTextChanged {
                    viewModel.updateUIState(newOriginAddress = it.toString())
                }
                binding.editTextDestinyAddress.doAfterTextChanged {
                    viewModel.updateUIState(newDestinyAddress = it.toString())
                }
                binding.materialButton.setOnClickListener {
                    viewModel.fetchRidesAvailable()
                }

                lifecycleScope.launch {

                    viewModel.userIDErrorPropagator.collect {
                        binding.editTextUserId.error = it
                    }
                }

                lifecycleScope.launch {
                    viewModel.originAddressErrorPropagator.collect {
                        binding.editTextOriginAddress.error = it
                    }
                }

                lifecycleScope.launch {
                    viewModel.destinyAddressErrorPropagator.collect {
                        binding.editTextDestinyAddress.error = it
                    }
                }
            }
        }

        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.onSendData.collect {

                    if (it is OperationStateResult.Success) {

                        val action = TripSolicitationFragmentDirections.actionFromNavTripSolicitationTo(binding.editTextUserId.text.toString())
                        findNavController().navigate(action)

                    } else if (it is OperationStateResult.Error) {

                        Toast.makeText(requireContext(), "DEU ERRADO BERGUE", Toast.LENGTH_LONG).show()
                    }
                }
            }
        }
    }
}