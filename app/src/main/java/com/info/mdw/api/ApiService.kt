package com.info.mdw.api

import com.info.mdw.helper.Constants
import com.info.mdw.models.Post
import com.info.mdw.models.User
import retrofit2.http.GET

interface ApiService {
    companion object {
        const val BASE_URL = Constants.BASE_URL
    }

    @GET("users")
    suspend fun getUsers(): List<User>

    @GET("posts")
    suspend fun getPost():List<Post>
}
