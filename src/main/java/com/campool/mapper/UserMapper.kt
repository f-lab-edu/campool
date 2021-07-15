package com.campool.mapper;

import com.campool.model.UserInfo;
import com.campool.model.UserSignUpRequest;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

@Mapper
public interface UserMapper {

    @Insert("INSERT INTO USER(id, password, name, email, telephone) VALUES(#{id}, #{password}, #{name}, #{email}, #{telephone})")
    void insertUser(UserSignUpRequest userSignUpRequest);

    @Select("SELECT id, name, email, telephone FROM USER WHERE id = #{id}")
    UserInfo findById(String id);

    @Select("SELECT id, name, email, telephone FROM USER WHERE id = #{id} AND password = #{password}")
    UserInfo findByIdAndPassword(String id, String password);

    @Update("UPDATE USER SET password = #{password}, name = #{name}, email = #{email}, telephone = #{telephone} WHERE id = #{id} ")
    void updateById(String id, String password, String name, String email, String telephone);

    @Delete("DELETE FROM USER WHERE id = #{id}")
    void deleteById(String id);

}
