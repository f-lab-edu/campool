package com.campool.model;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.ToString;
import org.springframework.lang.Nullable;

@Getter
@RequiredArgsConstructor
@ToString
public class RentalsRequestByLocation {

    @NonNull
    @Max(100000)
    @Min(100)
    private final int distanceInMeters;

    @NonNull
    private final double longitude;

    @NonNull
    private final double latitude;

    @Nullable
    private final Integer typeId;

}
