package com.devmobile.android.journey.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.devmobile.android.journey.data.TripsAvailableRemoteRepository
import com.devmobile.android.journey.usecase.Driver
import com.devmobile.android.journey.usecase.Route
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.launch


class TripOptionsViewModel(
    private val repository: TripsAvailableRemoteRepository,
    private val coroutineScope: CoroutineScope = CoroutineScope(SupervisorJob() + Dispatchers.Default)
) : ViewModel() {

    init {
        coroutineScope.launch {
            repository.ridesAvailable.collect {
                _drivers.emit(it.drivers)
            }
        }
    }

    private val _drivers = MutableStateFlow<List<Driver>?>(null)
    val drivers = _drivers.asStateFlow().filterNotNull()

    private val _routes = MutableSharedFlow<Route>()
    val routes = _routes.asSharedFlow()

    fun confirmNewTrip(customerId: String) {
        coroutineScope.launch {
        }
    }

    companion object {

        fun provideFactory(
            repository: TripsAvailableRemoteRepository,
        ): ViewModelProvider.Factory = object : ViewModelProvider.Factory {

            override fun <T : ViewModel> create(modelClass: Class<T>): T {
                return TripOptionsViewModel(repository = repository) as T
            }
        }
    }
}