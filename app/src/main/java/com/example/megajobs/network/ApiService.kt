package com.example.megajobs.network

import com.example.megajobs.models.ErrorResponse
import com.example.megajobs.models.Job
import com.example.megajobs.utils.Constants
import com.haroldadmin.cnradapter.NetworkResponse
import com.haroldadmin.cnradapter.NetworkResponseAdapterFactory
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.*


interface ApiService {

    @GET(Constants.POSITIONS)
    suspend fun getUsers(): NetworkResponse<List<Job>, ErrorResponse>

    companion object {
        fun getMoshi(): Moshi {
            return Moshi.Builder().add(KotlinJsonAdapterFactory()).build()
        }

        operator fun invoke(): ApiService {
            return Retrofit.Builder()

                .addConverterFactory(MoshiConverterFactory.create(getMoshi()))
                .addCallAdapterFactory(NetworkResponseAdapterFactory())
                .baseUrl(Constants.BASE_URL)
                .build()
                .create(ApiService::class.java)
        }

    }
}