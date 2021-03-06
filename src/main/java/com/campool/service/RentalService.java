package com.campool.service;

import com.campool.mapper.RentalMapper;
import com.campool.model.CampingGear;
import com.campool.model.RentalRegisterRequest;
import java.util.List;
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
    public void register(String userId, RentalRegisterRequest rentalRegisterRequest, List<CampingGear> gears) {
        rentalMapper.insertRental(userId, rentalRegisterRequest);
        rentalMapper.insertGears(gears);
    }

}
