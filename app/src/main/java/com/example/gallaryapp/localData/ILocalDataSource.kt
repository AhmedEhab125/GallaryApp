package com.example.gallaryapp.localData

import kotlinx.coroutines.flow.Flow

interface ILocalDataSource {
    suspend fun getImagePathsFromMediaStore(): Flow<List<String>>
    suspend fun getVideosPathsFromMediaStore(): Flow<List<String>>
}