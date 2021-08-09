package com.campool.controller

import com.campool.service.RentalService
import com.campool.service.BookingService
import com.campool.annotation.LoginValidation
import javax.validation.Valid
import com.campool.model.RentalRegisterRequest
import com.campool.model.CampingGear
import com.campool.annotation.LoginUserId
import com.campool.model.RentalsRequestByLocation
import com.campool.model.Rental
import com.campool.model.RentalDetailsResponse
import org.springframework.transaction.annotation.Transactional
import com.campool.model.RentalCompleteRequest
import org.springframework.web.bind.annotation.*

@RestController
class RentalController(
    private val rentalService: RentalService,
    private val bookingService: BookingService
) {
    @LoginValidation
    @PostMapping("/rentals")
    fun registerRental(
        @Valid request: RentalRegisterRequest,
        @RequestBody gears: List<CampingGear>, @LoginUserId id: String
    ) {
        rentalService.register(id, request, gears)
    }

    @LoginValidation
    @GetMapping("/rentals")
    fun getRentalsByLocation(@Valid request: RentalsRequestByLocation): List<Rental> =
        rentalService.getRentalsByLocation(request)

    @LoginValidation
    @GetMapping("/rentals/{id}")
    fun getRentalInfoById(@PathVariable id: Long): RentalDetailsResponse {
        val rentalInfo = rentalService.getRentalById(id)
        val gears = rentalService.getGearsByRentalId(id)
        return RentalDetailsResponse(rentalInfo, gears)
    }

    @LoginValidation
    @PatchMapping("/rentals/{rentalId}/rent")
    fun rentCampingGears(@PathVariable rentalId: Long, @LoginUserId userId: String) {
        rentalService.updateStatusToRented(rentalId, userId)
    }

    @LoginValidation
    @PostMapping("/rentals/complete")
    @Transactional
    fun completeRental(@Valid request: RentalCompleteRequest, @LoginUserId userId: String) {
        val bookingInfo = bookingService.getBookingInfoById(request.bookingId)
        rentalService.completeRental(request.rentalId, bookingInfo, userId)
    }

    @LoginValidation
    @DeleteMapping("/rentals/{rentalId}")
    fun deleteRental(@PathVariable rentalId: Long, @LoginUserId userId: String) {
        rentalService.deleteRental(rentalId, userId)
    }
}