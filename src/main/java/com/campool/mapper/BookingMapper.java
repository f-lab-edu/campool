package com.campool.mapper;

import com.campool.model.Booking;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface BookingMapper {

    void insertBooking(Booking booking);

}
