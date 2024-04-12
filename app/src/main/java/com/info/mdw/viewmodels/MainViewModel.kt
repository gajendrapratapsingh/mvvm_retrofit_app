package com.info.mdw.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.info.mdw.api.ApiResponse
import com.info.mdw.api.ApiState
import com.info.mdw.models.User
import com.info.mdw.repo.MainRepository
import com.info.mdw.repo.UserRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch
import javax.inject.Inject

//@HiltViewModel
//class MainViewModel @Inject constructor(private val userRepository: UserRepository) : ViewModel() {
//
//    private val _usersLiveData = MutableLiveData<ApiResponse<List<User>>>()
//    val usersLiveData: LiveData<ApiResponse<List<User>>> = _usersLiveData
//
//    fun fetchUsers() {
//        viewModelScope.launch(Dispatchers.IO) {
//            _usersLiveData.postValue(ApiResponse.Loading)
//            val response = userRepository.getUsers()
//            _usersLiveData.postValue(response)
//        }
//    }
//}

@HiltViewModel
class MainViewModel
@Inject
constructor(private val mainRepository: MainRepository) : ViewModel() {

    private val postStateFlow: MutableStateFlow<ApiState> = MutableStateFlow(ApiState.Empty)

    val _postStateFlow: StateFlow<ApiState> = postStateFlow

    fun getPost() = viewModelScope.launch {
        postStateFlow.value = ApiState.Loading
        mainRepository.getPost().catch { e->
                postStateFlow.value=ApiState.Failure(e)
            }.collect { data->
                postStateFlow.value=ApiState.Success(data)
        }
    }
}