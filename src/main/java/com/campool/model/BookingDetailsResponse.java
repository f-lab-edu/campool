package com.campool.model;


import java.util.List;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

@Getter
@RequiredArgsConstructor
@ToString
public class BookingDetailsResponse {

    private final BookingInfo bookingInfo;

    private final List<CampingGear> gears;

}
