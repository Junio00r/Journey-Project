package com.devmobile.android.journey.data

import com.devmobile.android.journey.data.network.ITripApiService
import com.devmobile.android.journey.usecase.RideEstimateRequest
import com.devmobile.android.journey.usecase.RideEstimateResponse
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.withContext

class TripsAvailableRemoteRepository private constructor(
    private val tripService: ITripApiService,
    private val ioDispatcher: CoroutineDispatcher = Dispatchers.IO
) {

    private val _ridesAvailable = MutableSharedFlow<RideEstimateResponse>(1)
    val ridesAvailable = _ridesAvailable.asSharedFlow()

    suspend fun requestTripsAvailable(route: RideEstimateRequest) {

        withContext(ioDispatcher) {

            _ridesAvailable.emit(tripService.requestRoute(route))
        }
    }

    companion object {
        // Make sure of be synchrony access, because kotlin uses set and get implicitly
        @Volatile
        @JvmStatic
        private var instance: TripsAvailableRemoteRepository? = null

        @JvmStatic
        fun getInstance(
            tripService: ITripApiService,
            ioDispatcher: CoroutineDispatcher = Dispatchers.IO
        ): TripsAvailableRemoteRepository {

            return instance ?: synchronized(this) {
                instance ?: TripsAvailableRemoteRepository(tripService, ioDispatcher).also {
                    instance = it
                }
            }
        }
    }
}