package com.example.gallaryapp.repo

import android.net.Uri
import com.example.gallaryapp.localData.ILocalDataSource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class Repository @Inject constructor(var localDataSource: ILocalDataSource):IRepo {
    override suspend fun getImagePathsFromMediaStore(): Flow<List<Uri>> {
        return localDataSource.getImagePathsFromMediaStore()
    }

    override suspend fun getVideoPathsFromMediaStore(): Flow<List<String>> {
        return localDataSource.getVideosPathsFromMediaStore()
    }

}