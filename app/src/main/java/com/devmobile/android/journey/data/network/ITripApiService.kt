package com.devmobile.android.journey.data.network

import com.devmobile.android.journey.usecase.ConfirmRideRequest
import com.devmobile.android.journey.usecase.ConfirmRideResponse
import com.devmobile.android.journey.usecase.RidesResponse
import com.devmobile.android.journey.usecase.RideEstimateRequest
import com.devmobile.android.journey.usecase.RideEstimateResponse
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.PATCH
import retrofit2.http.POST
import retrofit2.http.Path

interface ITripApiService {

    @POST("/ride/estimate")
    suspend fun requestRoute(@Body body: RideEstimateRequest): RideEstimateResponse

    @PATCH("/ride/confirm")
    suspend fun requestRideConfirmation(@Body body: ConfirmRideRequest): ConfirmRideResponse

    @GET("/ride/{customer_id}?driver_id={driver_id}")
    suspend fun getTripsTaken(
        @Path("customer_id") customerId: String,
        @Path("driver_id") driverId: String? = null
    ): RidesResponse
}