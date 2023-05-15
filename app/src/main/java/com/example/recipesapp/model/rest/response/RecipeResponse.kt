package com.example.recipesapp.model.rest.response

import com.google.gson.annotations.SerializedName

data class RecipeResponse(
    @SerializedName("idCategory") val id: String = "",
    @SerializedName("strCategory") val name: String = "",
    @SerializedName("strCategoryDescription") val description: String = "",
    @SerializedName("strCategoryThumb") val imageUrl: String = ""
)