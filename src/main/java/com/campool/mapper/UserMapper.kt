package com.campool.mapper

import com.campool.model.UserSignUpRequest
import com.campool.model.UserInfo
import org.apache.ibatis.annotations.*

@Mapper
interface UserMapper {
    @Insert(
        """
        INSERT INTO USER(id, password, name, email, telephone) 
        VALUES(#{id}, #{password}, #{name}, #{email}, #{telephone})
        """
    )
    fun insertUser(userSignUpRequest: UserSignUpRequest)

    @Select(
        """
        SELECT id, name, email, telephone 
        FROM USER 
        WHERE id = #{id}
        """
    )
    fun findById(id: String): UserInfo?

    @Select(
        """
        SELECT id, name, email, telephone 
        FROM USER 
        WHERE id = #{id} AND password = #{password}
        """
    )
    fun findByIdAndPassword(id: String, password: String): UserInfo?

    @Update(
        """
        UPDATE USER 
        SET password = #{password}, name = #{name}, email = #{email}, telephone = #{telephone} 
        WHERE id = #{id}
        """
    )
    fun updateById(id: String, password: String, name: String, email: String, telephone: String)

    @Delete("DELETE FROM USER WHERE id = #{id}")
    fun deleteById(id: String)
}