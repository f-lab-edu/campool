package com.campool.model;

import com.campool.enumeration.BookingStatus;
import java.time.LocalDate;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Getter
@Builder
@ToString
public class Booking {

    private final long id;

    private final long rentalId;

    private final String userId;

    private final BookingStatus status;

    private final LocalDate startDate;

    private final LocalDate endDate;

    private final int rentalPeriod;

    private final int cost;

    private final int amount;

}
