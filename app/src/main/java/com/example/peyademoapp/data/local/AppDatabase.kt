package com.example.peyademoapp.data.local

import androidx.room.RoomDatabase

//@Database(
//    entities = [],
//    version = 1,
//)
abstract class AppDatabase : RoomDatabase() {
    abstract fun productDao(): com.example.peyademoapp.data.local.dao.ProductDao

}