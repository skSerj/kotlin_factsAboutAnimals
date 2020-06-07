package com.sourceit.animalfacts.network

import com.sourceit.animalfacts.network.model.AnimalFacts
import io.reactivex.Observable
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

object ApiService {
    private const val END_POINT = "https://cat-fact.herokuapp.com/"
    private val countryApi: CountryApi

    val data: Observable<List<AnimalFacts>>
        get() = countryApi.allCountries

    interface CountryApi {
        @get:GET("facts")
        val allCountries: Observable<List<AnimalFacts>>
    }

    init {
        val interceptor = HttpLoggingInterceptor().apply { level = HttpLoggingInterceptor.Level.BODY }
        val client = OkHttpClient.Builder().addInterceptor(interceptor).build()
        val retrofit = Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .baseUrl(END_POINT)
            .client(client)
            .build()
        countryApi = retrofit.create(CountryApi::class.java)
    }
}