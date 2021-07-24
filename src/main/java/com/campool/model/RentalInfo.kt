package com.campool.model

import com.campool.enumeration.RentalStatus
import java.time.LocalDate

data class RentalInfo(
    val rentalId: Long,
    val title: String,
    val description: String,
    val status: RentalStatus,
    val userId: String,
    val startDate: LocalDate,
    val endDate: LocalDate,
    val cost: Int,
    val longitude: Double,
    val latitude: Double
)