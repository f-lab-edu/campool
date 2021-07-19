package com.campool.mapper

import com.campool.model.AdminInfo
import com.campool.model.AdminSignUpRequest
import org.apache.ibatis.annotations.Insert
import org.apache.ibatis.annotations.Mapper
import org.apache.ibatis.annotations.Select

@Mapper
interface AdminMapper {
    @Insert(
        """
        INSERT INTO ADMIN(id, password, name, email, telephone) 
        VALUES(#{id}, #{password}, #{name}, #{email}, #{telephone})
        """
    )
    fun insertAdmin(adminSignUpRequest: AdminSignUpRequest)

    @Select(
        """
        SELECT id, name, email, telephone 
        FROM ADMIN 
        WHERE id = #{id}
        """
    )
    fun findById(id: String): AdminInfo?

    @Select(
        """
        SELECT id, name, email, telephone 
        FROM ADMIN 
        WHERE id = #{id} AND password = #{password}
        """
    )
    fun findByIdAndPassword(id: String, password: String): AdminInfo?
}