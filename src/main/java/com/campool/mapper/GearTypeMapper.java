package com.campool.mapper;

import com.campool.model.GearType;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface GearTypeMapper {

    /**
     * 렌트정보 조회 시 참조하는 캠핑용품 전체 유형을 가져오는 쿼리
     */
    @Select("SELECT ID, NAME FROM TYPE")
    List<GearType> selectGearTypes();

}
