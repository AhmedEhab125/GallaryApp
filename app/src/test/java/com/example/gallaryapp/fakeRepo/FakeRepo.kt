package com.example.gallaryapp.fakeRepo

import android.net.Uri
import com.example.gallaryapp.localData.ILocalDataSource
import com.example.gallaryapp.localData.LocalDataSource
import com.example.gallaryapp.repo.IRepo
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf

class FakeRepo(val localDataSource: ILocalDataSource) :IRepo {
    override suspend fun getImagePathsFromMediaStore(): Flow<List<Uri>> {
        return localDataSource.getImagePathsFromMediaStore()
    }

    override suspend fun getVideoPathsFromMediaStore(): Flow<List<String>> {
        return localDataSource.getVideosPathsFromMediaStore()
    }
}