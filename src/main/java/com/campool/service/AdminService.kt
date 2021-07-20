package com.campool.service

import com.campool.mapper.AdminMapper
import com.campool.encrypt.Encryptor
import com.campool.model.AdminSignUpRequest
import com.campool.exception.NoSuchUserException
import com.campool.model.AdminInfo
import org.springframework.dao.DuplicateKeyException
import org.springframework.stereotype.Service

@Service
class AdminService(
    private val adminMapper: AdminMapper,
    private val encryptor: Encryptor
) {
    fun add(adminSignUpRequest: AdminSignUpRequest) {
        if (isDuplicate(adminSignUpRequest.id)) {
            throw DuplicateKeyException("중복된 아이디가 존재합니다.")
        }

        adminMapper.insertAdmin(adminSignUpRequest.getEncryptedPasswordUserSignUp(encryptor))
    }

    fun isDuplicate(id: String): Boolean = adminMapper.findById(id) != null

    fun getByIdAndPw(id: String, password: String): AdminInfo =
        adminMapper.findByIdAndPassword(id, encryptor.encrypt(password))
            ?: throw NoSuchUserException("해당하는 관리자 정보가 없습니다.")
}