package com.app.nexttogo.app.di

import android.content.Context
import androidx.room.Room
import com.app.nexttogo.app.db.NextToGoDatabase
import com.app.nexttogo.app.network.ApiService
import com.app.nexttogo.data.local.LocalDataSourceImpl
import com.app.nexttogo.data.remote.RemoteDataSourceImpl
import com.app.nexttogo.data.repository.RaceRepositoryImpl
import com.app.nexttogo.data.source.LocalDataSource
import com.app.nexttogo.data.source.RemoteDataSource
import com.app.nexttogo.domain.repository.RaceRepository
import com.google.gson.Gson
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

/**
 * App Module to provide it to the Dagger Hilt.
 */
@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun providesNextToGoDatabase(@ApplicationContext context: Context): NextToGoDatabase {
        return Room.databaseBuilder(context, NextToGoDatabase::class.java, "next_to_go.db").build()
    }

    @Provides
    @Singleton
    fun provideOkHttpClient(): OkHttpClient {
        val loggingInterceptor = HttpLoggingInterceptor()
        loggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
        return OkHttpClient.Builder()
            .addInterceptor(loggingInterceptor)
            .build()
    }

    @Provides
    @Singleton
    fun provideApiService(okHttpClient: OkHttpClient): ApiService {
        return Retrofit.Builder()
            .baseUrl("https://api.neds.com.au/rest/")
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create(Gson()))
            .build()
            .create(ApiService::class.java)
    }


    @Provides
    @Singleton
    fun provideRemoteDataSource(apiService: ApiService): RemoteDataSource {
        return RemoteDataSourceImpl(apiService)
    }

    @Provides
    @Singleton
    fun provideLocalSource(nextToGoDatabase: NextToGoDatabase): LocalDataSource {
        return LocalDataSourceImpl(nextToGoDatabase)
    }

    @Provides
    @Singleton
    fun provideTransactionRepository(
        remoteDataSource: RemoteDataSource,
        localDataSource: LocalDataSource,
    ): RaceRepository {
        return RaceRepositoryImpl(
            remoteDataSource = remoteDataSource,
            localDataSource = localDataSource
        )
    }
}