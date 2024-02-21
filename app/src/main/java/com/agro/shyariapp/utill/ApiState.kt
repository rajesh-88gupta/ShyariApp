package com.agro.shyariapp.utill

import com.agro.shyariapp.Post

sealed class ApiState {
    class Success(val data:List<Post> ) : ApiState()
    class Failure(val msg:Throwable) : ApiState()
    object Loading : ApiState()
    object Empty : ApiState()
}