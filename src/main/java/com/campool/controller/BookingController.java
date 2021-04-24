package com.campool.controller;

import com.campool.annotation.LoginUserId;
import com.campool.model.CreateBookingRequest;
import com.campool.model.CreateBookingResponse;
import com.campool.service.BookingService;
import javax.validation.Valid;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class BookingController {

    @NonNull
    private final BookingService bookingService;

    @PostMapping("/bookings")
    public CreateBookingResponse createBooking(@Valid CreateBookingRequest createBookingRequest,
            @LoginUserId String userId) {
        return bookingService.createBooking(createBookingRequest, userId);
    }

}
