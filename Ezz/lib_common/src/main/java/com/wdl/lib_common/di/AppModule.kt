package com.wdl.lib_common.di

import com.wdl.lib_common.BuildConfig
import com.wdl.lib_common.ext.logD
import com.wdl.lib_common.global.CONFIG_API_URL
import com.wdl.lib_common.global.Config
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

// module 表明提供依赖注入的模块
// InstallIn  SingletonComponent（限定域 - 跟app同一个生命周期） 全局注入
@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    private val TAG = "okhttp"

    // 全局配置
    @Singleton
    @Provides
    fun providerConfig(): Config {
        return Config()
    }

    // 提供日志拦截
    @Singleton
    @Provides
    fun providerHttpLoggingInterceptor(): HttpLoggingInterceptor {
        return HttpLoggingInterceptor { msg ->
            if (BuildConfig.DEBUG) {
                msg.logD(TAG)
            }
        }.also {
            it.level = HttpLoggingInterceptor.Level.BODY
        }
    }

    // 提供OkHttpClient
    @Singleton
    @Provides
    fun providerOkHttpClient(httpLoggingInterceptor: HttpLoggingInterceptor): OkHttpClient {
        return OkHttpClient.Builder().also {
            if (BuildConfig.DEBUG) {
                it.addInterceptor(httpLoggingInterceptor)
            }
        }.build()
    }

    // 提供Retrofit2
    @Singleton
    @Provides
    fun providerRetrofit2(okHttpClient: OkHttpClient, config: Config): Retrofit {
        return Retrofit.Builder()
            .baseUrl(config.getConfiguration<String>(CONFIG_API_URL))
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
}