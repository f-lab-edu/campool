package com.campool.controller;

import com.campool.annotation.LoginUserId;
import com.campool.annotation.LoginValidation;
import com.campool.model.BookingState;
import com.campool.model.CreateBookingRequest;
import com.campool.model.CreateBookingResponse;
import com.campool.service.BookingService;
import java.util.List;
import javax.validation.Valid;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class BookingController {

    @NonNull
    private final BookingService bookingService;

    @LoginValidation
    @PostMapping("/bookings")
    public CreateBookingResponse createBooking(@Valid CreateBookingRequest createBookingRequest,
            @LoginUserId String userId) {
        return bookingService.createBooking(createBookingRequest, userId);
    }

    @LoginValidation
    @GetMapping("/bookings")
    public List<BookingState> getBookingStates(@LoginUserId String id) {
        return bookingService.getStatesList(id);
    }

}
