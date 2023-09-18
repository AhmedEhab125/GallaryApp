package com.example.nwavetask.di

import android.content.Context
import com.example.gallaryapp.localData.ILocalDataSource
import com.example.gallaryapp.localData.LocalDataSource
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Provides
    @Singleton
    fun getLocalDataInstance(@ApplicationContext context: Context): ILocalDataSource {
        return LocalDataSource(context)
    }
}