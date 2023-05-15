package com.example.recipesapp.model.api

import com.example.recipesapp.model.rest.response.CategoryResponse
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

class RecipesWebService {

    private lateinit var api: RecipesApi

    init {
        val retrofit = Retrofit.Builder()
            .baseUrl("https://www.themealdb.com/api/json/v1/1/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        api = retrofit.create(RecipesApi::class.java)
    }

    suspend fun getRecipes(): CategoryResponse {
        return api.getRecipes()
    }

    interface RecipesApi {
        @GET("categories.php")
        suspend fun getRecipes(): CategoryResponse
    }

}