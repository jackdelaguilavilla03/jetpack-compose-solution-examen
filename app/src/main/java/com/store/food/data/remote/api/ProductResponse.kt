package com.store.food.data.remote.api

import com.store.food.data.remote.model.Product

data class ProductResponse(
    val products: List<Product>?
)
