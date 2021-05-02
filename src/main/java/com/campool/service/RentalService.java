package com.campool.service;

import com.campool.mapper.RentalMapper;
import com.campool.model.CampingGear;
import com.campool.model.Rental;
import com.campool.model.RentalInfo;
import com.campool.model.RentalRegisterRequest;
import com.campool.model.RentalsRequestByLocation;
import java.math.BigDecimal;
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

    private static final BigDecimal LONGITUDE_PER_METER = new BigDecimal("0.0000113182");
    private static final BigDecimal LATITUDE_PER_METER = new BigDecimal("0.0000089426");

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

        String polygon = getPolygonString(new BigDecimal(longitude), new BigDecimal(latitude),
                new BigDecimal(meters));

        return rentalMapper.selectRentalsByLocation(rentalsRequestByLocation, polygon);
    }

    private String getPolygonString(BigDecimal longitude, BigDecimal latitude, BigDecimal meters) {
        BigDecimal differenceX = LONGITUDE_PER_METER.multiply(meters);
        BigDecimal differenceY = LATITUDE_PER_METER.multiply(meters);

        BigDecimal X1 = longitude.subtract(differenceX);
        BigDecimal X2 = longitude.add(differenceX);
        BigDecimal Y1 = latitude.subtract(differenceY);
        BigDecimal Y2 = latitude.add(differenceY);

        return "POLYGON(("
                + X1 + " " + Y1 + ","
                + X2 + " " + Y1 + ","
                + X2 + " " + Y2 + ","
                + X1 + " " + Y2 + ","
                + X1 + " " + Y1 + "))";
    }

}
