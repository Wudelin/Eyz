package com.wdl.lib_common.di

import android.content.Context
import androidx.room.Room
import com.wdl.lib_common.database.AppDatabase
import com.wdl.lib_common.database.TestDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.android.scopes.ActivityScoped
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun providerDatabase(@ApplicationContext context: Context): AppDatabase {
        return Room.databaseBuilder(context, AppDatabase::class.java, "app.db").build()
    }

    @Provides
    fun providerDao(database: AppDatabase): TestDao {
        return database.testDao()
    }
}