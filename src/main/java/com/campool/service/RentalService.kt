package com.campool.service

import org.springframework.transaction.annotation.Transactional
import com.campool.mapper.RentalMapper
import com.campool.model.RentalRegisterRequest
import com.campool.model.CampingGear
import com.campool.enumeration.RentalStatus
import com.campool.model.RentalInfo
import java.util.NoSuchElementException
import com.campool.model.RentalsRequestByLocation
import com.campool.model.Rental
import java.math.BigDecimal
import com.campool.model.BookingInfo
import org.springframework.stereotype.Service

@Transactional(readOnly = true)
@Service
class RentalService(
    private val rentalMapper: RentalMapper
) {
    companion object {
        private val LONGITUDE_PER_METER = BigDecimal("0.0000113182")
        private val LATITUDE_PER_METER = BigDecimal("0.0000089426")
    }

    @Transactional
    fun register(userId: String, rentalRegisterRequest: RentalRegisterRequest, gears: List<CampingGear>) {
        rentalMapper.insertRental(userId, rentalRegisterRequest, RentalStatus.TRADEABLE)
        rentalMapper.insertGears(gears)
    }

    fun getRentalById(id: Long): RentalInfo =
        rentalMapper.findRentalInfoById(id) ?: throw NoSuchElementException("해당하는 렌트 정보가 없습니다.")

    fun getGearsByRentalId(rentalId: Long): List<CampingGear> = rentalMapper.findGearsByRentalId(rentalId)

    fun getRentalsByLocation(rentalsRequestByLocation: RentalsRequestByLocation): List<Rental> {
        val meters = rentalsRequestByLocation.distanceInMeters
        val longitude = rentalsRequestByLocation.longitude
        val latitude = rentalsRequestByLocation.latitude
        val polygon = getPolygonString(BigDecimal(longitude), BigDecimal(latitude), BigDecimal(meters))
        return rentalMapper
            .findRentalsByLocation(rentalsRequestByLocation, RentalStatus.TRADEABLE, polygon)
    }

    private fun getPolygonString(longitude: BigDecimal, latitude: BigDecimal, meters: BigDecimal): String {
        val differenceX = LONGITUDE_PER_METER.multiply(meters)
        val differenceY = LATITUDE_PER_METER.multiply(meters)
        val x1 = longitude.subtract(differenceX)
        val x2 = longitude.add(differenceX)
        val y1 = latitude.subtract(differenceY)
        val y2 = latitude.add(differenceY)
        return ("POLYGON(($x1 $y1, $x2 $y1, $x2 $y2, $x1 $y2, $x1 $y1))")
    }

    fun updateStatusToRented(rentalId: Long, userId: String) {
        updateNewStatus(RentalStatus.TRADING, RentalStatus.RENTED, rentalId, userId)
    }

    fun completeRental(rentalId: Long, bookingInfo: BookingInfo, userId: String) {
        require(!(rentalId != bookingInfo.rentalId || userId != bookingInfo.userId)) { "유효하지 않는 요청입니다." }
        updateNewStatus(RentalStatus.RENTED, RentalStatus.TRADE_COMPLETED, rentalId, userId)
    }

    @Transactional
    fun updateNewStatus(currentStatus: RentalStatus, newStatus: RentalStatus, rentalId: Long, userId: String) {
        check(isValidRentalStatus(getRentalById(rentalId), currentStatus, userId))
        { currentStatus.message + "인 상태가 아닙니다." }
        rentalMapper.updateStatusById(rentalId, newStatus)
    }

    private fun isValidRentalStatus(rentalInfo: RentalInfo, status: RentalStatus, userId: String): Boolean =
        rentalInfo.status == status && rentalInfo.userId == userId

    @Transactional
    fun deleteRental(rentalId: Long, userId: String) {
        val rentalInfo = getRentalById(rentalId)
        check(!(rentalInfo.status != RentalStatus.TRADEABLE || rentalInfo.userId != userId))
        { "본인이 등록하고 거래 가능 상태일 때만 삭제할 수 있습니다." }
        rentalMapper.deleteById(rentalId)
    }
}