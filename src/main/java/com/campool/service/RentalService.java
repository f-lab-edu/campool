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

@Transactional(readOnly = true)
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
        int meters = rentalsRequestByLocation.getDistanceInMeters();
        double longitude = rentalsRequestByLocation.getLongitude();
        double latitude = rentalsRequestByLocation.getLatitude();

        String polygon = getPolygonString(longitude, latitude, meters);

        return rentalMapper.selectRentalsByLocation(rentalsRequestByLocation, polygon);
    }

    private String getPolygonString(double longitude, double latitude, int meters) {
        double differenceX = 0.0000113182 * meters;
        double differenceY = 0.0000089426 * meters;

        double X1 = longitude - differenceX;
        double X2 = longitude + differenceX;
        double Y1 = latitude - differenceY;
        double Y2 = latitude + differenceY;

        return "POLYGON(("
                + X1 + " " + Y1 + ","
                + X2 + " " + Y1 + ","
                + X2 + " " + Y2 + ","
                + X1 + " " + Y2 + ","
                + X1 + " " + Y1 + "))";
    }

}
