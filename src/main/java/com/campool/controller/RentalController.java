package com.campool.controller;

import com.campool.annotation.LoginUserId;
import com.campool.annotation.LoginValidation;
import com.campool.model.BookingInfo;
import com.campool.model.CampingGear;
import com.campool.model.Rental;
import com.campool.model.RentalCompleteRequest;
import com.campool.model.RentalDetailsResponse;
import com.campool.model.RentalInfo;
import com.campool.model.RentalRegisterRequest;
import com.campool.model.RentalsRequestByLocation;
import com.campool.service.BookingService;
import com.campool.service.RentalService;
import java.util.List;
import javax.validation.Valid;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class RentalController {

    @NonNull
    private final RentalService rentalService;

    @NonNull
    private final BookingService bookingService;

    @LoginValidation
    @PostMapping("/rentals")
    public void registerRental(@Valid RentalRegisterRequest rentalRegisterRequest,
            @RequestBody List<CampingGear> gears, @LoginUserId String id) {
        rentalService.register(id, rentalRegisterRequest, gears);
    }

    @LoginValidation
    @GetMapping("/rentals")
    public List<Rental> getRentalsByLocation(
            @Valid RentalsRequestByLocation rentalsRequestByLocation) {
        return rentalService.getRentalsByLocation(rentalsRequestByLocation);
    }

    @LoginValidation
    @GetMapping("/rentals/{id}")
    public RentalDetailsResponse getRentalInfoById(@PathVariable long id) {
        RentalInfo rentalInfo = rentalService.getRentalById(id);
        List<CampingGear> gears = rentalService.getGearsByRentalId(id);
        return new RentalDetailsResponse(rentalInfo, gears);
    }

    @LoginValidation
    @PatchMapping("/rentals/rent/{rentalId}")
    public void rentCampingGears(@PathVariable long rentalId, @LoginUserId String userId) {
        rentalService.updateStatusToRented(rentalId, userId);
    }

    @LoginValidation
    @PostMapping("/rentals/complete")
    @Transactional
    public void completeRental(@Valid RentalCompleteRequest request, @LoginUserId String userId) {
        BookingInfo bookingInfo = bookingService.getBookingInfoById(request.getBookingId());
        rentalService.completeRental(request.getRentalId(), bookingInfo, userId);
    }

}
