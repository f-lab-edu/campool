package com.campool.model;

import java.time.LocalDate;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

@Getter
@RequiredArgsConstructor
@ToString
public class BookingInfo {

    private final long bookingId;

    private final LocalDate startDate;

    private final LocalDate endDate;

    private final int amount;

    private final long rentalId;

    private final String title;

    private final String description;

    private final String rentalStatus;

    private final double longitude;

    private final double latitude;

    private final String userId;

    private final String name;

    private final String email;

    private final String telephone;

}
