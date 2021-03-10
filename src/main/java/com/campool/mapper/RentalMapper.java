package com.campool.mapper;

import com.campool.model.CampingGear;
import com.campool.model.Rental;
import com.campool.model.RentalRegisterRequest;
import com.campool.model.RentalsRequestByLocation;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface RentalMapper {

    void insertRental(String userId, RentalRegisterRequest rental);

    void insertGears(List<CampingGear> gears);

    List<Rental> selectRentalsByLocation(RentalsRequestByLocation rentalsRequestByLocation);

}
