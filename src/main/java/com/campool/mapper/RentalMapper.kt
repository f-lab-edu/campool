package com.campool.mapper;

import com.campool.enumeration.RentalStatus;
import com.campool.model.CampingGear;
import com.campool.model.Rental;
import com.campool.model.RentalInfo;
import com.campool.model.RentalRegisterRequest;
import com.campool.model.RentalsRequestByLocation;
import java.time.LocalDate;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface RentalMapper {

    void insertRental(String userId, RentalRegisterRequest rental, RentalStatus status);

    void insertGears(List<CampingGear> gears);

    RentalInfo findRentalInfoById(long id);

    List<CampingGear> findGearsByRentalId(long rentalId);

    List<Rental> findRentalsByLocation(RentalsRequestByLocation rental, RentalStatus status,
            String polygon);

    Integer findCostByIdAndDate(long id, LocalDate startDate, LocalDate endDate);

    void updateStatusById(long id, RentalStatus status);

    void deleteById(long id);

}
