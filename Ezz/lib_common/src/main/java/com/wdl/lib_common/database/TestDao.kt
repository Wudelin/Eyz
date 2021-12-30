package com.wdl.lib_common.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.wdl.lib_common.data.TestData

@Dao
interface TestDao {

    @Query("SELECT * FROM testData ORDER BY id DESC")
    fun getAll(): List<TestData>


    @Insert
    fun insert(vararg datas:TestData)
}