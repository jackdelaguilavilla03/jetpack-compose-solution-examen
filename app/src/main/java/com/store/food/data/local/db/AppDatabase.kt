package com.store.food.data.local.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.store.food.data.local.entity.ProductEntity

@Database(entities = [ProductEntity::class], version = 3)
abstract class AppDatabase : RoomDatabase() {
    abstract fun productDao(): ProductDao

    companion object {
        private var INSTANCE: AppDatabase? = null

        fun getInstance(context: Context): AppDatabase {
            INSTANCE = Room.databaseBuilder(
                context,
                AppDatabase::class.java,
                "products.db"
            )
//                .fallbackToDestructiveMigration()
                .allowMainThreadQueries()
                .build()
            return INSTANCE as AppDatabase
        }
    }
}