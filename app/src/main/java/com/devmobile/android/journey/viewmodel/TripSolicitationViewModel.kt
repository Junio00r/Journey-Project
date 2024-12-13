package com.devmobile.android.journey.viewmodel

import android.os.Bundle
import androidx.lifecycle.AbstractSavedStateViewModelFactory
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.savedstate.SavedStateRegistryOwner
import com.devmobile.android.journey.data.TripsAvailableRemoteRepository
import com.devmobile.android.journey.usecase.OperationStateResult
import com.devmobile.android.journey.usecase.RideEstimateRequest
import com.devmobile.android.journey.usecase.Validator
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.cancel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import java.util.regex.Pattern

class TripSolicitationViewModel(
    private val repository: TripsAvailableRemoteRepository,
    private val savedStateHandle: SavedStateHandle,
    private val coroutineScope: CoroutineScope = CoroutineScope(SupervisorJob() + Dispatchers.Default)
) : ViewModel() {

    val userID: StateFlow<String> get() = savedStateHandle.getStateFlow("USER_ID", "")
    val originAddress: StateFlow<String> get() = savedStateHandle.getStateFlow("ORIGIN_ADDRESS", "")
    val destinyAddress: StateFlow<String> get() = savedStateHandle.getStateFlow("DESTINY_ADDRESS", "")

    private val _userIDErrorPropagator = MutableSharedFlow<String?>(0)
    val userIDErrorPropagator = _userIDErrorPropagator.asSharedFlow()

    private val _originAddressErrorPropagator = MutableSharedFlow<String?>(0)
    val originAddressErrorPropagator = _userIDErrorPropagator.asSharedFlow()

    private val _destinyAddressErrorPropagator = MutableSharedFlow<String?>(0)
    val destinyAddressErrorPropagator = _userIDErrorPropagator.asSharedFlow()

    private val _onSendData = MutableSharedFlow<OperationStateResult?>()
    val onSendData = _onSendData.asSharedFlow()

    private fun isValidInput(value: String, pattern: Pattern): Boolean {

        return Validator.isMatch(value, pattern)
    }

    @JvmOverloads
    fun updateUIState(
        newUserID: String? = null,
        newOriginAddress: String? = null,
        newDestinyAddress: String? = null,
    ) {

        coroutineScope.launch {

            newUserID?.let {

                if (isValidInput(it, Validator.NUMBER)) {
                    savedStateHandle["USER_ID"] = newUserID
                    _userIDErrorPropagator.emit(null)
                } else {
                    _userIDErrorPropagator.emit("ID must be a number!")
                }
            }
            newOriginAddress?.let {

                if (isValidInput(it, Validator.TEXT)) {
                    savedStateHandle["ORIGIN_ADDRESS"] = newOriginAddress
                    _originAddressErrorPropagator.emit(null)
                } else {
                    _originAddressErrorPropagator.emit("Invalid Address!")
                }
            }
            newDestinyAddress?.let {

                if (isValidInput(it, Validator.TEXT)) {
                    savedStateHandle["DESTINY_ADDRESS"] = newDestinyAddress
                    _originAddressErrorPropagator.emit(null)
                } else {
                    _destinyAddressErrorPropagator.emit("Invalid Address!")
                }
            }
        }
    }

    fun fetchRidesAvailable() {

        coroutineScope.launch {

            try {

                repository.requestTripsAvailable(RideEstimateRequest(userID.value, originAddress.value, destinyAddress.value))
                _onSendData.emit(OperationStateResult.Success)

            } catch (e: Exception) {
                _onSendData.emit(OperationStateResult.Error(e))
            }
        }
    }

    override fun onCleared() {
        coroutineScope.cancel()

        super.onCleared()
    }

    companion object {

        fun provideFactory(
            repository: TripsAvailableRemoteRepository,
            owner: SavedStateRegistryOwner,
            defaultArgs: Bundle? = null,
        ): AbstractSavedStateViewModelFactory {

            return object : AbstractSavedStateViewModelFactory(owner, defaultArgs = null) {
                override fun <T : ViewModel> create(
                    key: String,
                    modelClass: Class<T>,
                    handle: SavedStateHandle
                ): T {

                    @Suppress("UNCHECKED_CAST")
                    return TripSolicitationViewModel(repository, handle) as T
                }
            }
        }
    }
}