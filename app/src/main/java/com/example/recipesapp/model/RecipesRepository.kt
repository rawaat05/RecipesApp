package com.example.recipesapp.model

import com.example.recipesapp.model.api.RecipesWebService
import com.example.recipesapp.model.rest.response.CategoryResponse
import com.example.recipesapp.model.rest.response.RecipeResponse

class RecipesRepository(private val webService: RecipesWebService = RecipesWebService()) {

    private var cachedRecipes = listOf<RecipeResponse>()

    suspend fun getRecipes(): CategoryResponse {
        val response = webService.getRecipes()
        cachedRecipes = response.categories
        return response
    }

    fun getRecipe(id: String): RecipeResponse? {
        return cachedRecipes.firstOrNull {
            it.id == id
        }
    }

    companion object {
        @Volatile
        private var instance: RecipesRepository? = null

        fun getInstance() = instance?: synchronized(this) {
            instance ?: RecipesRepository().also { instance = it }
        }
    }
}