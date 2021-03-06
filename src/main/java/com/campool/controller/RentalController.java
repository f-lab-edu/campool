package com.campool.controller;

import com.campool.annotation.LoginUserId;
import com.campool.annotation.LoginValidation;
import com.campool.model.CampingGear;
import com.campool.model.RentalRegisterRequest;
import com.campool.service.RentalService;
import java.util.List;
import javax.validation.Valid;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class RentalController {

    @NonNull
    private final RentalService rentalService;

    @LoginValidation
    @PostMapping("/rental")
    public void registerRental(@Valid RentalRegisterRequest rentalRegisterRequest,
            @RequestBody List<CampingGear> gears, @LoginUserId String id) {
        rentalService.register(id, rentalRegisterRequest, gears);
    }

}
