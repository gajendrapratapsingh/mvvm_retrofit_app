package com.info.mdw.api

import com.info.mdw.models.Post

sealed class ApiState{
    object Loading : ApiState()
    class Failure(val msg:Throwable) : ApiState()
    class Success(val data:List<Post>) : ApiState()
    object Empty : ApiState()
}