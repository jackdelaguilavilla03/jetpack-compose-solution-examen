package com.store.food.data.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Product")
class ProductEntity (
    @PrimaryKey
    val id: String,

    @ColumnInfo(name = "title")
    @JvmField
    val title: String,

    @ColumnInfo(name = "image")
    @JvmField
    val image: String,

    @ColumnInfo(name = "imageType")
    @JvmField
    val imageType: String,
) {
    constructor(id: String) : this(id, "", "", "")
}