package com.campool.model

import java.time.LocalDate

data class Rental(
    val rentalId: Int,
    val title: String,
    val userId: String,
    val startDate: LocalDate,
    val endDate: LocalDate,
    val cost: Int,
    val longitude: Double,
    val latitude: Double
)