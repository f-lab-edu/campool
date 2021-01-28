package com.campool.mapper;

import com.campool.model.UserSignUp;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface UserMapper {

    @Insert("INSERT INTO USER(id, password, name, email, telephone) VALUES(#{id}, #{password}, #{name}, #{email}, #{telephone})")
    void insert(UserSignUp userSignUp);

    @Select("SELECT * FROM USER WHERE id = #{id}")
    UserSignUp findById(String id);

    @Select("SELECT * FROM USER WHERE id = #{id} AND password = #{password}")
    UserSignUp findByIdAndPassword(String id, String password);

}
