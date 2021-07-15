package com.campool.service

import com.campool.mapper.UserMapper
import com.campool.encrypt.Encryptor
import com.campool.model.UserSignUpRequest
import com.campool.model.UserInfo
import com.campool.exception.NoSuchUserException
import com.campool.model.UserUpdateRequest
import org.springframework.dao.DuplicateKeyException
import org.springframework.stereotype.Service

@Service
class UserService(
    private val userMapper: UserMapper,
    private val encryptor: Encryptor
) {
    fun add(userSignUpRequest: UserSignUpRequest) {
        if (isDuplicate(userSignUpRequest.id)) {
            throw DuplicateKeyException("중복된 아이디가 존재합니다.")
        }

        userMapper.insertUser(userSignUpRequest.getEncryptedPasswordUserSignUp(encryptor))
    }

    fun isDuplicate(id: String): Boolean = userMapper.findById(id) != null

    fun getUserInfoById(id: String): UserInfo? = userMapper.findById(id)

    fun getByIdAndPw(id: String, password: String): UserInfo? {
        val userInfo = userMapper.findByIdAndPassword(id, encryptor.encrypt(password))
        return if (isValidUser(userInfo)) userInfo else throw NoSuchUserException("해당하는 사용자 정보가 없습니다.")
    }

    private fun isValidUser(userInfo: UserInfo?): Boolean = userInfo != null

    fun updateById(id: String, userUpdateRequest: UserUpdateRequest) {
        //현재 비밀번호의 사용자가 존재하는지 확인
        getByIdAndPw(id, userUpdateRequest.currentPassword)
        userMapper.updateById(
            id, encryptor.encrypt(userUpdateRequest.newPassword),
            userUpdateRequest.name, userUpdateRequest.email,
            userUpdateRequest.telephone
        )
    }

    fun deleteById(id: String) {
        if (!isValidUser(userMapper.findById(id))) {
            throw NoSuchUserException("해당하는 사용자 정보가 없습니다.")
        }

        userMapper.deleteById(id)
    }
}