package com.store.food.data.remote.model

data class Product (
    val id: String,
    val title: String,
    val image: String,
    var favorite: Boolean,
    val imageType: String,
)