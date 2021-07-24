package com.campool.model;

import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

@Getter
@RequiredArgsConstructor
@ToString
public class RentalCompleteRequest {

    @NonNull
    private final long rentalId;

    @NonNull
    private final long bookingId;

}
