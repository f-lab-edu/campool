package com.campool.model;

import java.time.LocalDate;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;
import org.springframework.format.annotation.DateTimeFormat;

@Getter
@RequiredArgsConstructor
@ToString
public class BookingState {

    private final long bookingId;

    private final long rentalId;

    private final String title;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private final LocalDate startDate;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private final LocalDate endDate;

}
