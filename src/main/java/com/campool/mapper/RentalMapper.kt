package com.campool.mapper

import com.campool.model.RentalRegisterRequest
import com.campool.enumeration.RentalStatus
import com.campool.model.CampingGear
import com.campool.model.RentalInfo
import com.campool.model.RentalsRequestByLocation
import com.campool.model.Rental
import org.apache.ibatis.annotations.Mapper
import java.time.LocalDate

@Mapper
interface RentalMapper {
    fun insertRental(userId: String, rental: RentalRegisterRequest, status: RentalStatus)

    fun insertGears(gears: List<CampingGear>)

    fun findRentalInfoById(id: Long): RentalInfo?

    fun findGearsByRentalId(rentalId: Long): List<CampingGear>

    fun findRentalsByLocation(rental: RentalsRequestByLocation, status: RentalStatus, polygon: String): List<Rental>

    fun findCostByIdAndDate(id: Long, startDate: LocalDate, endDate: LocalDate): Int?

    fun updateStatusById(id: Long, status: RentalStatus)

    fun deleteById(id: Long)
}