package com.store.food.data.repository

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.store.food.data.local.db.ProductDao
import com.store.food.data.local.entity.ProductEntity
import com.store.food.data.remote.api.ProductResponse
import com.store.food.data.remote.api.ProductService
import com.store.food.data.remote.model.Product
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ProductRepository(
    private val productDao: ProductDao,
    private val productService: ProductService
) {
    private val _products = MutableLiveData<List<Product>>(emptyList())
    val products get() = _products

    private var _productsEntity = MutableLiveData<List<ProductEntity>>(emptyList())
    val productsEntity get() = _productsEntity

    fun fetchByName(name: String) {
        val fetchByNameCall = productService.fetchByName(name)
        fetchByNameCall.enqueue(object : Callback<ProductResponse> {
            override fun onResponse(
                call: Call<ProductResponse>,
                response: Response<ProductResponse>
            ) {
                if (response.isSuccessful) {
                    val productResponse = response.body()
                    if (productResponse?.products != null) {
                        _products.value = productResponse.products!!
                        println("Products: ${_products.value}")
                        for (product in _products.value!!) {
                            product.favorite = productDao.fetchById(product.id).isNotEmpty()
                        }
                    } else {
                        _products.value = emptyList()
                    }
                }
            }

            override fun onFailure(call: Call<ProductResponse>, t: Throwable) {
                Log.d("ProductRepository", t.message.toString())
            }
        })
    }


    fun insert(product: Product) {
        val productEntity =
            ProductEntity(product.id, product.title, product.image, product.imageType)
        productDao.insert(productEntity)
        product.favorite = true
    }

    fun delete(product: Product) {
        val productEntity =
            ProductEntity(product.id)
        productDao.delete(productEntity)
        product.favorite = false
    }

    fun fetchRandom() {
        val fetchRandomCall = productService.fetchByName("")
        fetchRandomCall.enqueue(object : Callback<ProductResponse> {
            override fun onResponse(
                call: Call<ProductResponse>,
                response: Response<ProductResponse>
            ) {
                if (response.isSuccessful) {
                    val productResponse = response.body()
                    if (productResponse?.products != null) {
                        _products.value = productResponse.products!!
                        println("Products: ${_products.value}")
                        for (product in _products.value!!) {
                            product.favorite = productDao.fetchById(product.id).isNotEmpty()
                        }
                    } else {
                        _products.value = emptyList()
                    }
                }
            }

            override fun onFailure(call: Call<ProductResponse>, t: Throwable) {
                Log.d("ProductRepository", t.message.toString())
            }
        })
    }
}