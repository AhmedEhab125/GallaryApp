package com.example.nwavetask.di

import com.example.gallaryapp.localData.ILocalDataSource
import com.example.gallaryapp.localData.LocalDataSource
import com.example.gallaryapp.repo.IRepo
import com.example.gallaryapp.repo.Repository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class InterFacesModule {
    @Binds
    @Singleton
    abstract fun bindsRepository(repository : Repository) : IRepo


}