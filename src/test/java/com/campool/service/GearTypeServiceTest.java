package com.campool.service;

import static org.junit.jupiter.api.Assertions.*;

import com.campool.mapper.GearTypeMapper;
import com.campool.model.GearType;
import java.util.NoSuchElementException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
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

    @DisplayName("중복된 타입의 추가 요청은 중복 예외를 발생")
    @Test
    void addDuplicateGearTypeNameThrowsException() {
        given(gearTypeMapper.findGearTypeByName(gearType.getName())).willReturn(gearType);
        String duplicateGearTypeName = "gearType";

        Exception exception = assertThrows(DuplicateKeyException.class,
                () -> gearTypeService.addGearType(duplicateGearTypeName));

        assertEquals("이미 등록되어 있는 타입입니다.", exception.getMessage());
    }

    @DisplayName("존재하지 않는 현재 타입 명으로 요청 시 예외 발생")
    @Test
    void updateNonExistentCurrentNameThrowsException() {
        String nonExistentName = "currentName";
        given(gearTypeMapper.findGearTypeByName(nonExistentName)).willReturn(null);

        Exception exception = assertThrows(NoSuchElementException.class,
                () -> gearTypeService.updateByName("currentName", "newName"));

        assertEquals("존재하지 않는 타입 명입니다.", exception.getMessage());

    }

    @DisplayName("존재하지 않는 ID로 삭제 요청 시 예외 발생")
    @Test
    void deleteNonExistentIdThrowsException() {
        int nonExistentId = 123;
        given(gearTypeMapper.findGearTypeById(nonExistentId)).willReturn(null);

        Exception exception = assertThrows(NoSuchElementException.class,
                () -> gearTypeService.deleteById(123));

        assertEquals("존재하지 않는 타입입니다.", exception.getMessage());
    }

}