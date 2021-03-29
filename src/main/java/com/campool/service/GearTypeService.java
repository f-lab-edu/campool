package com.campool.service;

import com.campool.mapper.GearTypeMapper;
import com.campool.model.GearType;
import java.util.List;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class GearTypeService {

    @NonNull
    private final GearTypeMapper gearTypeMapper;

    @Cacheable(value = "gearTypes")
    public List<GearType> getGearTypes() {
        return gearTypeMapper.selectGearTypes();
    }

}
