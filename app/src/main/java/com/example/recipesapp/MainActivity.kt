package com.example.recipesapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.recipesapp.ui.details.RecipeDetailsScreen
import com.example.recipesapp.ui.details.RecipeDetailsViewModel
import com.example.recipesapp.ui.recipes.CategoriesScreen
import com.example.recipesapp.ui.theme.RecipesAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            RecipesAppTheme {
                RecipesApp()
            }
        }
    }
}

@Composable
private fun RecipesApp() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = "destination_categories_list") {
        composable(route = "destination_categories_list") {
            CategoriesScreen { recipeId ->
                navController.navigate("destination_recipe_details/$recipeId")
            }
        }
        composable(
            route = "destination_recipe_details/{recipe_id}",
            arguments = listOf(navArgument("recipe_id") {
                type = NavType.StringType
            })
        ) {
            val viewModel: RecipeDetailsViewModel = viewModel()
            RecipeDetailsScreen(recipe = viewModel.recipeState.value)
        }
    }
}