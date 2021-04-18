package com.campool.service;

import static org.junit.jupiter.api.Assertions.*;

import com.campool.mapper.GearTypeMapper;
import com.campool.model.GearType;
import com.campool.model.GearTypeRegisterRequest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.dao.DuplicateKeyException;

import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
class GearTypeServiceTest {

    @InjectMocks
    GearTypeService gearTypeService;

    @Mock
    GearTypeMapper gearTypeMapper;

    GearType gearType;

    @BeforeEach
    void setUp() {
        gearType = new GearType(1, "gearType");
    }

    @Test
    void addDuplicateGearTypeNameThrowsException() {
        String duplicateGearTypeName = "gearType";
        given(gearTypeMapper.findGearTypeByName(gearType.getName())).willReturn(gearType);

        Exception exception = assertThrows(DuplicateKeyException.class,
                () -> gearTypeService.addGearType(duplicateGearTypeName));

        assertEquals("이미 등록되어 있는 타입입니다.", exception.getMessage());
    }

}