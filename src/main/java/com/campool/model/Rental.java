package com.campool.model;

import java.time.LocalDate;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.format.annotation.DateTimeFormat;

@Getter
@Setter
@ToString
public class Rental {

    private int rentalId;

    private String title;

    private String userId;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate startDate;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate endDate;

    private int cost;

    private double longitude;

    private double latitude;

}
