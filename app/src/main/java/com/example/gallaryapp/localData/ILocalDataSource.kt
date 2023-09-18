package com.example.gallaryapp.localData

import android.net.Uri
import kotlinx.coroutines.flow.Flow

interface ILocalDataSource {
    suspend fun getImagePathsFromMediaStore(): Flow<List<Uri>>
    suspend fun getVideosPathsFromMediaStore(): Flow<List<String>>
}