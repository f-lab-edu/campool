package com.campool.service;

import com.campool.mapper.GearTypeMapper;
import com.campool.model.GearType;
import java.util.List;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.dao.DuplicateKeyException;
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

    public void addGearType(String name) {
        if (isDuplicateByName(name)) {
            throw new DuplicateKeyException("이미 등록되어 있는 타입입니다.");
        }
        gearTypeMapper.insertGearType(name);
    }

    public boolean isDuplicateByName(String name) {
        return gearTypeMapper.findGearTypeByName(name) != null;
    }

}
