package com.campool.mapper;

import com.campool.enumeration.BookingStatus;
import com.campool.model.Booking;
import com.campool.model.BookingInfo;
import com.campool.model.BookingState;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface BookingMapper {

    void insertBooking(Booking booking);

    void updateStatusById(long id, BookingStatus status);

    Integer selectAmountByIdAndStatus(long id, BookingStatus status);

    List<BookingState> findStatesByIdAndStatus(String id, BookingStatus status);

    BookingInfo findInfoById(long id);

}
