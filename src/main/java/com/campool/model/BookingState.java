package com.campool.model;

import java.time.LocalDate;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

@Getter
@RequiredArgsConstructor
@ToString
public class BookingState {

    private final long bookingId;

    private final long rentalId;

    private final String title;

    private final LocalDate startDate;

    private final LocalDate endDate;

}
