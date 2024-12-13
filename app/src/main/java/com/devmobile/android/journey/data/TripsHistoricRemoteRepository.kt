package com.devmobile.android.journey.data

import com.devmobile.android.journey.data.network.ITripApiService
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.withContext

class TripsHistoricRemoteRepository(
    private val service: ITripApiService,
    private val ioDispatcher: CoroutineDispatcher = Dispatchers.IO
) {

    private val _trips = flow<Any> {

    }

    /**
     *  Fetch all rides already taken or only trips with a specify driver
     */
    suspend fun fetchTripsTaken(customerId: String, driverId: String? = null) {
        withContext(ioDispatcher) {

            service.getTripsTaken(customerId, driverId)
        }
    }
}