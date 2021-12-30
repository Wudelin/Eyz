package com.wdl.lib_common.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "testData")
data class TestData(val msg: String, val timestamp: Long) {
    @PrimaryKey(autoGenerate = true)
    var id: Long = 0
}
