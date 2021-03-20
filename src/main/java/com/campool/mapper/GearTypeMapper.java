package com.campool.mapper;

import com.campool.model.GearType;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface GearTypeMapper {

    @Select("SELECT ID, NAME FROM TYPE")
    List<GearType> selectGearTypes();

}
