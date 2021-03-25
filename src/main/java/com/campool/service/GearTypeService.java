package com.campool.service;

import com.campool.mapper.GearTypeMapper;
import com.campool.model.GearType;
import java.util.List;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class GearTypeService {

    @NonNull
    private final GearTypeMapper gearTypeMapper;

    public List<GearType> getGearTypes() {
        return gearTypeMapper.selectGearTypes();
    }

}
