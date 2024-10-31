package com.example.tutgetandroid.network

import retrofit2.Retrofit
import retrofit2.converter.scalars.ScalarsConverterFactory
import retrofit2.http.GET
import retrofit2.http.POST

private const val BASE_URL =
    "https://localhost:8069"
//https://localhost:8069/api/qna/getAllQuestions

private val retrofit = Retrofit.Builder()
    .addConverterFactory(ScalarsConverterFactory.create())
    .baseUrl(BASE_URL)
    .build()

interface TutgetApiService {
    @GET("/api/qna/getAllQuestions")
    fun getQnA()

//    @POST("/api/login")
//    fun login(@Body request: LoginRequest): Response<LoginResponse>



}

object TutgetApi {
    val retrofitService : TutgetApiService by lazy {
        retrofit.create(TutgetApiService::class.java)
    }
}
