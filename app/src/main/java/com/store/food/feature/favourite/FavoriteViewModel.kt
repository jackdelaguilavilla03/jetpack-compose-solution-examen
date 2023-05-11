package com.store.food.feature.favourite

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.store.food.data.local.db.AppDatabase
import com.store.food.data.local.entity.ProductEntity
import com.store.food.data.remote.api.ProductClient
import com.store.food.data.remote.model.Product
import com.store.food.data.repository.ProductRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class FavoriteViewModel(application: Application): AndroidViewModel(application) {
    private val productDao = AppDatabase.getInstance(application).productDao()

    private val _products = MutableStateFlow<List<ProductEntity>>(emptyList())
    val products: StateFlow<List<ProductEntity>> = _products

    fun fetchFavorites() {
        val favorites = productDao.fetchAll()
        _products.value = favorites
    }
}