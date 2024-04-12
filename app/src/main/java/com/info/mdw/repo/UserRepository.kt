package com.info.mdw.repo

import com.info.mdw.api.ApiResponse
import com.info.mdw.api.ApiService
import com.info.mdw.models.User
import javax.inject.Inject

class UserRepository @Inject constructor(private val apiService: ApiService) {
    suspend fun getUsers(): ApiResponse<List<User>> {
        return try {
            val users = apiService.getUsers()
            ApiResponse.Success(users)
        } catch (e: Exception) {
            ApiResponse.Error(e.message)
        }
    }
}


