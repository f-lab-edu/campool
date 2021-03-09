package com.campool.controller;

import com.campool.annotation.LoginUserId;
import com.campool.annotation.LoginValidation;
import com.campool.model.CampingGear;
import com.campool.model.Rental;
import com.campool.model.RentalRegisterRequest;
import com.campool.model.RentalsRequestByLocation;
import com.campool.service.RentalService;
import java.util.List;
import javax.validation.Valid;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class RentalController {

    @NonNull
    private final RentalService rentalService;

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

}