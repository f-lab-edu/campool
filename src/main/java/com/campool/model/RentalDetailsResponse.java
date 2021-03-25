package com.campool.model;


import java.util.List;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

@Getter
@RequiredArgsConstructor
@ToString
public class RentalDetailsResponse {

    private final RentalInfo rentalInfo;

    private final List<CampingGear> gears;

}
