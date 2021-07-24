package com.campool.model

class RentalsRequestByLocation(
    val distanceInMeters: Int,
    val longitude: Double,
    val latitude: Double,
    val typeId: Int
)