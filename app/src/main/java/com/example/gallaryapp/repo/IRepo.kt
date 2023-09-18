package com.example.gallaryapp.repo

import android.net.Uri
import kotlinx.coroutines.flow.Flow

interface IRepo {
    suspend fun getImagePathsFromMediaStore(): Flow<List<Uri>>
    suspend fun getVideoPathsFromMediaStore(): Flow<List<String>>
}