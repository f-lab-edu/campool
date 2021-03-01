package com.campool.mapper;

import com.campool.model.UserSignUp;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

@Mapper
public interface UserMapper {

    @Insert("INSERT INTO USER(id, password, name, email, telephone) VALUES(#{id}, #{password}, #{name}, #{email}, #{telephone})")
    void insert(UserSignUp userSignUp);

    @Select("SELECT ID, PASSWORD, NAME, EMAIL, TELEPHONE FROM USER WHERE id = #{id}")
    UserSignUp findById(String id);

    @Select("SELECT ID, PASSWORD, NAME, EMAIL, TELEPHONE FROM USER WHERE id = #{id} AND password = #{password}")
    UserSignUp findByIdAndPassword(String id, String password);

    @Update("UPDATE USER SET PASSWORD = #{password}, NAME = #{name}, EMAIL = #{email}, TELEPHONE = #{telephone} WHERE ID = #{id} ")
    void updateById(String id, String password, String name, String email, String telephone);

    @Delete("DELETE FROM USER WHERE ID = #{id}")
    void deleteById(String id);

}
