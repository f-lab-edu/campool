package com.campool.model

import java.time.LocalDate
import com.campool.enumeration.RentalStatus

data class BookingInfo(
    val bookingId: Long,
    val startDate: LocalDate,
    val endDate: LocalDate,
    val amount: Int,
    val rentalId: Long,
    val title: String,
    val description: String,
    val rentalStatus: RentalStatus,
    val longitude: Double,
    val latitude: Double,
    val userId: String,
    val name: String,
    val email: String,
    val telephone: String
)