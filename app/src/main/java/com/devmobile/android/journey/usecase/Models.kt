package com.devmobile.android.journey.usecase

import java.time.format.DateTimeFormatter


data class Coordinates(val latitude: Number, val longitude: Number)
data class Review(val rating: Number, val comment: String)

// Another 1
data class RideEstimateRequest(
    val customer_id: String,
    val origin: String,
    val destination: String
)

data class RideEstimateResponse(
    val origin: Coordinates,
    val destination: Coordinates,
    val distance: Number,
    val duration: String,
    val drivers: List<Driver>,
    val routeResponse: Any,
)

data class Driver(
    val id: Number,
    val name: String,
    val description: String,
    val vehicle: String,
    val review: Review,
    val value: Number,
)

// Another 2
data class ConfirmRideRequest(
    val customer_id: String,
    val origin: String,
    val destination: String,
    val distance: Number,
    val duration: String,
    val driver: Driver,
    val value: Number
)

data class ConfirmRideResponse(val success: Boolean)

// Another 3
data class RidesResponse(val customer_id: String, val rides: Array<Ride>)
data class Ride(
    val id: Number,
    val data: DateTimeFormatter,
    val origin: String,
    val destination: String,
    val distance: Number,
    val duration: String,
    val driver: Driver,
    val value: Number,
)


data class Route(
    val originLat: Number,
    val originLong: Number,
    val destinationLat: Number,
    val destinationLong: Number
)