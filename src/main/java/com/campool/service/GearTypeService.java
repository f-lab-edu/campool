package com.campool.service;

import com.campool.mapper.GearTypeMapper;
import com.campool.model.GearType;
import java.util.List;
import java.util.NoSuchElementException;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class GearTypeService {

    private static final String types = "gearTypes";

    @NonNull
    private final GearTypeMapper gearTypeMapper;

    @Cacheable(value = types)
    public List<GearType> getGearTypes() {
        return gearTypeMapper.findAllGearTypes();
    }

    @CacheEvict(value = types, allEntries = true)
    public void addGearType(String name) {
        if (isDuplicateByName(name)) {
            throw new DuplicateKeyException("이미 등록되어 있는 타입입니다.");
        }
        gearTypeMapper.insertGearType(name);
    }

    public boolean isDuplicateByName(String name) {
        return gearTypeMapper.findGearTypeByName(name) != null;
    }

    @CacheEvict(value = types, allEntries = true)
    public void updateByName(String currentName, String newName) {
        if (isNotValidGearType(gearTypeMapper.findGearTypeByName(currentName))) {
            throw new NoSuchElementException("존재하지 않는 타입 명입니다.");
        }
        gearTypeMapper.updateByName(currentName, newName);
    }

    @CacheEvict(value = types, allEntries = true)
    public void deleteById(long id) {
        if (isNotValidGearType(gearTypeMapper.findGearTypeById(id))) {
            throw new NoSuchElementException("존재하지 않는 타입입니다.");
        }
        gearTypeMapper.deleteById(id);
    }

    private boolean isNotValidGearType(GearType gearType) {
        return gearType == null;
    }

}
