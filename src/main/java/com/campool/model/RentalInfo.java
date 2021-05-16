package com.campool.model;

import com.campool.enumeration.RentalStatus;
import java.time.LocalDate;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

@Getter
@RequiredArgsConstructor
@ToString
public class RentalInfo {

    private final int rentalId;

    private final String title;

    private final String description;

    private final RentalStatus status;

    private final String userId;

    private final LocalDate startDate;

    private final LocalDate endDate;

    private final int cost;

    private final double longitude;

    private final double latitude;

}
