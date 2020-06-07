package com.sourceit.animalfacts.network

import com.sourceit.animalfacts.network.model.AnimalFacts
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

object ApiServiceCallBack {
    private const val API = "https://cat-fact.herokuapp.com/"
    private var animalFactsApi2: AnimalFactsApi2
    val data: Call<List<AnimalFacts>>
        get() = animalFactsApi2.getFactsAboutAnimal("cat")

    interface AnimalFactsApi2 {
        @GET("facts")
        fun getFactsAboutAnimal(@Query("animal_type") type: String?): Call<List<AnimalFacts>>
    }

    init {
        val interceptor =
            HttpLoggingInterceptor().apply { level = HttpLoggingInterceptor.Level.BODY }
        val client = OkHttpClient.Builder()
            .addInterceptor(interceptor).build()
        val retrofit = Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(API)
            .client(client)
            .build()
        animalFactsApi2 = retrofit.create(
            AnimalFactsApi2::class.java
        )
    }
}