package com.example.recipesapp.ui.details

import androidx.compose.animation.core.animateDpAsState
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.max
import coil.compose.rememberAsyncImagePainter
import com.example.recipesapp.model.rest.response.RecipeResponse
import java.lang.Float.min

@Composable
fun RecipeDetailsScreen(recipe: RecipeResponse?) {
    val scrollState = rememberLazyListState()
    val offset = min(
        1f, 1 -
                (scrollState.firstVisibleItemScrollOffset / 600f
                        + scrollState.firstVisibleItemIndex)
    )
    val size by animateDpAsState(targetValue = max(60.dp, 140.dp * offset))

    Surface(color = MaterialTheme.colorScheme.background) {
        Column {
            Surface(shadowElevation = 4.dp) {
                Row(modifier = Modifier.fillMaxWidth()) {
                    Card(
                        modifier = Modifier.padding(16.dp),
                        shape = CircleShape,
                        border = BorderStroke(2.dp, Color.Black)
                    ) {
                        Image(
                            painter = rememberAsyncImagePainter(model = recipe?.imageUrl),
                            contentDescription = null,
                            modifier = Modifier.size(size),
                            contentScale = ContentScale.Crop
                        )
                    }
                    Text(
                        text = recipe?.name ?: "Default",
                        modifier = Modifier
                            .padding(16.dp)
                            .align(Alignment.CenterVertically),
                        style = MaterialTheme.typography.headlineMedium
                    )
                }
            }
            val repeatDescListForScroll = (0 .. 10).map { recipe?.description }
            LazyColumn(state = scrollState) {
                items(repeatDescListForScroll) { desc ->
                    Text(text = desc ?: "Default", modifier = Modifier.padding(8.dp))
                }
            }
        }
    }
}

@Preview
@Composable
fun DetailsScreenPreview() {
    Surface(Modifier.fillMaxSize()) {
        RecipeDetailsScreen(
            recipe = RecipeResponse(
                "1",
                "Goat",
                "Goat is a healthy meat option.",
                "https://picsum.photos/200"
            )
        )
    }
}