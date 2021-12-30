package com.wdl.lib_common.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.wdl.lib_common.data.TestData

@Database(exportSchema = true, version = 1, entities = [TestData::class])
abstract class AppDatabase : RoomDatabase() {
    abstract fun testDao(): TestDao
}