package com.example.recipesapp.ui.recipes

import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.rememberAsyncImagePainter
import com.example.recipesapp.model.rest.response.RecipeResponse
import com.example.recipesapp.ui.theme.RecipesAppTheme

@Composable
fun CategoriesScreen(navigationCallback: (String) -> Unit) {
    val viewModel: CategoriesViewModel = viewModel()
    val recipes = viewModel.recipesState.value
    LazyColumn(contentPadding = PaddingValues(16.dp)) {
        items(recipes) { recipe ->
            Category(recipe, navigationCallback)
        }
    }
}

@Composable
fun Category(recipe: RecipeResponse, navigationCallback: (String) -> Unit) {
    var isExpanded by remember { mutableStateOf(false) }

    ElevatedCard(
        shape = RoundedCornerShape(8.dp),
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 16.dp)
            .clickable { navigationCallback(recipe.id) }
    ) {
        Row(modifier = Modifier.animateContentSize()) {
            Image(
                painter = rememberAsyncImagePainter(model = recipe.imageUrl),
                contentDescription = null,
                modifier = Modifier
                    .size(80.dp)
                    .padding(4.dp)
                    .align(Alignment.CenterVertically)
            )
            Column(
                modifier = Modifier
                    .align(Alignment.CenterVertically)
                    .fillMaxWidth(0.8f)
                    .padding(16.dp)
            ) {
                Text(text = recipe.name, style = MaterialTheme.typography.titleMedium)
                Text(
                    text = recipe.description,
                    textAlign = TextAlign.Start,
                    color = LocalContentColor.current.copy(alpha = 0.75f),
                    style = MaterialTheme.typography.labelSmall,
                    overflow = TextOverflow.Ellipsis,
                    maxLines = if (isExpanded) 10 else 4
                )
            }
            Icon(
                if (isExpanded)
                    Icons.Filled.KeyboardArrowUp
                else
                    Icons.Filled.KeyboardArrowDown,
                contentDescription = null,
                modifier = Modifier
                    .padding(16.dp)
                    .align(
                        if (isExpanded)
                            Alignment.Bottom
                        else
                            Alignment.CenterVertically
                    )
                    .clickable { isExpanded = !isExpanded }
            )
        }
    }
}


@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    RecipesAppTheme {
        CategoriesScreen { }
    }
}