package com.example.gallaryapp.localData

import android.net.Uri
import androidx.test.ext.junit.runners.AndroidJUnit4
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import org.junit.Assert.*
import org.junit.runner.RunWith


class LocalDataSourceTest:ILocalDataSource{
    override suspend fun getImagePathsFromMediaStore(): Flow<List<Uri>> {
       return flowOf(listOf(Uri.EMPTY, Uri.EMPTY, Uri.EMPTY))
    }

    override suspend fun getVideosPathsFromMediaStore(): Flow<List<String>> {
        return flowOf(listOf("", "", "", ""))
    }

}