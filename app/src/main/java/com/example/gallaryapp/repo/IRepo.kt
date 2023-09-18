package com.example.gallaryapp.repo

import kotlinx.coroutines.flow.Flow

interface IRepo {
    suspend fun getImagePathsFromMediaStore(): Flow<List<String>>
}