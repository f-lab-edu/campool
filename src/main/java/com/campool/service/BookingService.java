package com.campool.service;

import com.campool.enumeration.BookingStatus;
import com.campool.mapper.BookingMapper;
import com.campool.mapper.RentalMapper;
import com.campool.model.Booking;
import com.campool.model.BookingInfo;
import com.campool.model.BookingState;
import com.campool.model.CreateBookingRequest;
import com.campool.model.CreateBookingResponse;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.NoSuchElementException;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class BookingService {

    @NonNull
    private final BookingMapper bookingMapper;

    @NonNull
    private final RentalMapper rentalMapper;

    public CreateBookingResponse createBooking(CreateBookingRequest request, String userId) {
        int cost = getCostByIdAndDate(request.getRentalId(), request.getStartDate(),
                request.getEndDate());
        int rentalPeriod = getCountByDate(request.getStartDate(), request.getEndDate());
        int amount = cost * rentalPeriod;

        Booking booking = Booking.builder()
                .rentalId(request.getRentalId())
                .userId(userId)
                .status(BookingStatus.PAYMENT_PENDING)
                .startDate(request.getStartDate())
                .endDate(request.getEndDate())
                .rentalPeriod(rentalPeriod)
                .cost(cost)
                .amount(amount)
                .build();

        bookingMapper.insertBooking(booking);

        return new CreateBookingResponse(booking.getId(), amount);
    }

    public BookingInfo getBookingInfoById(long id) {
        BookingInfo bookingInfo = bookingMapper.findInfoById(id);
        if (isNotValidBookingInfo(bookingInfo)) {
            throw new NoSuchElementException("해당하는 예약 정보가 없습니다.");
        }
        return bookingInfo;
    }

    private boolean isNotValidBookingInfo(BookingInfo bookingInfo) {
        return bookingInfo == null;
    }

    public int getCostByIdAndDate(long rentalId, LocalDate startDate, LocalDate endDate) {
        Integer cost = rentalMapper.findCostByIdAndDate(rentalId, startDate, endDate);
        if (cost == null) {
            throw new NoSuchElementException("유효하지 않은 날짜이거나 물품입니다.");
        }
        return cost;
    }

    public int getCountByDate(LocalDate startDate, LocalDate endDate) {
        int count = (int) ChronoUnit.DAYS.between(startDate, endDate);
        if (count < 0) {
            throw new IllegalArgumentException("시작 날은 종료 날보다 늦을 수 없습니다.");
        }
        return count + 1;
    }

    public List<BookingState> getStatesList(String id) {
        return bookingMapper.findStatesByIdAndStatus(id, BookingStatus.PAYMENT_COMPLETED);
    }

}
