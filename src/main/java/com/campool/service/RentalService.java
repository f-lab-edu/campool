package com.campool.service;

import com.campool.mapper.RentalMapper;
import com.campool.model.CampingGear;
import com.campool.model.Rental;
import com.campool.model.RentalInfo;
import com.campool.model.RentalRegisterRequest;
import com.campool.model.RentalsRequestByLocation;
import java.util.List;
import java.util.NoSuchElementException;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class RentalService {

    @NonNull
    private final RentalMapper rentalMapper;

    @Transactional
    public void register(String userId, RentalRegisterRequest rentalRegisterRequest,
            List<CampingGear> gears) {
        rentalMapper.insertRental(userId, rentalRegisterRequest);
        rentalMapper.insertGears(gears);
    }

    public RentalInfo getRentalById(long id) {
        RentalInfo rentalInfo = rentalMapper.findRentalInfoById(id);
        if (rentalInfo == null) {
            throw new NoSuchElementException("해당하는 렌트 정보가 없습니다.");
        }
        return rentalInfo;
    }

    public List<CampingGear> getGearsByRentalId(long rentalId) {
        return rentalMapper.findGearsByRentalId(rentalId);
    }

    public List<Rental> getRentalsByLocation(RentalsRequestByLocation rentalsRequestByLocation) {
        return rentalMapper.selectRentalsByLocation(rentalsRequestByLocation);
    }

}
