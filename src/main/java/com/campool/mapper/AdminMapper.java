package com.campool.mapper;

import com.campool.model.AdminSignUp;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface AdminMapper {

    @Insert("INSERT INTO ADMIN(id, password, name, email, telephone) VALUES(#{id}, #{password}, #{name}, #{email}, #{telephone})")
    void insert(AdminSignUp adminSignUp);

    @Select("SELECT ID, PASSWORD, NAME, EMAIL, TELEPHONE FROM ADMIN WHERE id = #{id}")
    AdminSignUp findById(String id);

}
