package com.campool.mapper;

import com.campool.enumeration.BookingStatus;
import com.campool.model.Booking;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface BookingMapper {

    void insertBooking(Booking booking);

    void updateStatusById(long id, BookingStatus status);

    Integer selectAmountByIdAndStatus(long id, BookingStatus status);

}
