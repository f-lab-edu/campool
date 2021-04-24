package com.campool.model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

@Getter
@RequiredArgsConstructor
@ToString
public class CreateBookingResponse {

    private final long id;

    private final int amount;

}
