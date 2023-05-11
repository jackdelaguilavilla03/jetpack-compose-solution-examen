package com.store.food.data.local.db

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.store.food.data.local.entity.ProductEntity

@Dao
interface ProductDao {
    @Query("select * from Product where id=:id")
    fun fetchById(id: String): List<ProductEntity>

    @Query("SELECT * FROM Product")
    fun fetchAll(): List<ProductEntity>

    @Insert
    fun insert(productEntity: ProductEntity)

    @Delete
    fun delete(productEntity: ProductEntity)
}