package com.store.food.data.remote.api

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ProductService {
    @GET("food/products/search?")
    fun fetchByName(
        @Query("query") name: String,
        @Query("number") number: Int = 50,
        @Query("apiKey") apiKey: String = "4b3dec9082734382ac3b45343804e625"
    ): Call<ProductResponse>
}