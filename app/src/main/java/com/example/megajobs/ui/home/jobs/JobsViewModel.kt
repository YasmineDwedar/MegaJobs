package com.example.megajobs.ui.home.jobs

import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.megajobs.models.Job
import com.example.megajobs.network.ApiService
import com.haroldadmin.cnradapter.NetworkResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.launch

class JobsViewModel : ViewModel() {
    private var _jobsLiveData = MutableLiveData<List<Job>>()
    val jobsLiveData: LiveData<List<Job>>
        get() = _jobsLiveData
    private var _errorMutLiveData = MutableLiveData<String>()
    val errorLiveData: LiveData<String>
        get() = _errorMutLiveData

    init {
        getJobs()
    }

    fun getJobs() {
        viewModelScope.launch(Dispatchers.IO) {


            var list = ApiService.invoke().getUsers()

            when (list) {
                is NetworkResponse.Success -> {
                    flow {
                        emit(list)
                    }.flowOn(Dispatchers.IO)

                        .collect {

                            _jobsLiveData.postValue(list.body)
                        }
                }
                is NetworkResponse.ServerError -> {
                    showError()
                }
                is NetworkResponse.NetworkError -> {
                    showError()
                }
                is NetworkResponse.UnknownError -> {
                    showError()
                }
            }


        }
    }

    private fun showError() {
        _errorMutLiveData.postValue("Internet Connection Failed!")

    }


}