package com.example.recipesapp.ui.details

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.example.recipesapp.model.RecipesRepository
import com.example.recipesapp.model.rest.response.RecipeResponse

class RecipeDetailsViewModel(savedStateHandle: SavedStateHandle) : ViewModel() {
    private val repository: RecipesRepository = RecipesRepository.getInstance()

    val recipeState = mutableStateOf<RecipeResponse?>(null)

    init {
        val recipeId = savedStateHandle.get<String>("recipe_id") ?: ""
        recipeState.value = repository.getRecipe(recipeId)
    }
}