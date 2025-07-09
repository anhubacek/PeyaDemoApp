package com.example.data.local

import androidx.room.RoomDatabase
import com.example.data.local.dao.ProductDao

//@Database(
//    entities = [],
//    version = 1,
//)
abstract class AppDatabase : RoomDatabase() {
    abstract fun productDao(): ProductDao

}