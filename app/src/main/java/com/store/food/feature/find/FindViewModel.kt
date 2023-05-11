package com.store.food.feature.find

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.store.food.data.local.db.AppDatabase
import com.store.food.data.remote.api.ProductClient
import com.store.food.data.remote.api.ProductResponse
import com.store.food.data.remote.model.Product
import com.store.food.data.repository.ProductRepository
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class FindViewModel (application: Application) : AndroidViewModel(application) {
    private val productService = ProductClient.productService()
    private val productDao = AppDatabase.getInstance(application).productDao()
    private val productRepository = ProductRepository(productDao, productService)

    private var _products = productRepository.products
    val products get() = _products


    private var _name = MutableLiveData<String>()
    val name get() = _name

    fun update(name: String) {
        _name.value = name
    }

    fun fetchByName() {
        productRepository.fetchByName(name.value!!)
        _products.value = productRepository.products.value
    }

    fun fetchRandom(){
        productRepository.fetchRandom()
        _products.value = productRepository.products.value
    }

    fun insert(product: Product) {
        productRepository.insert(product)
    }

    fun delete(product: Product) {
        productRepository.delete(product)
    }

}