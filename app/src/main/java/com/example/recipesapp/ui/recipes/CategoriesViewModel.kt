package com.example.recipesapp.ui.recipes

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.recipesapp.model.RecipesRepository
import com.example.recipesapp.model.rest.response.RecipeResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class CategoriesViewModel(private val repository: RecipesRepository = RecipesRepository.getInstance()) : ViewModel() {
    val recipesState: MutableState<List<RecipeResponse>> = mutableStateOf(emptyList())

    init {
        viewModelScope.launch(Dispatchers.IO) {
            val recipes = getRecipes()
            recipesState.value = recipes
        }
    }

    private suspend fun getRecipes() = repository.getRecipes().categories
}