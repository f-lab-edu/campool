package com.campool.mapper;

import com.campool.model.GearType;
import java.util.List;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface GearTypeMapper {

    /**
     * 캠핑용품 유형 데이터는 많지 않기 때문에 테이블 전체를 불러와 캐싱하기 위한 용도의 쿼리
     */
    @Select("SELECT ID, NAME FROM TYPE")
    List<GearType> selectGearTypes();

    @Select("SELECT ID, NAME FROM TYPE WHERE NAME = #{name}")
    GearType findGearTypeByName(String name);

    @Insert("INSERT INTO TYPE(name) VALUES(#{name})")
    void insertGearType(String name);

}
