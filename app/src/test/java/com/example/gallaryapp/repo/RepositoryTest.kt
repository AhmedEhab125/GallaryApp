package com.example.gallaryapp.repo

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.gallaryapp.fakeRepo.FakeRepo
import com.example.gallaryapp.localData.ILocalDataSource
import com.example.gallaryapp.localData.LocalDataSourceTest
import com.example.wetharapplication.MainDispatcherRule
import com.example.wetharapplication.getOrAwaitValue
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class RepositoryTest {
    @get:Rule
    var instanceExecuteRule = InstantTaskExecutorRule()

    @get:Rule
    var mainDispatcherRule = MainDispatcherRule()
    lateinit var localDataSource: ILocalDataSource
    lateinit var repo:IRepo
    @Before
    fun setUp(){
        localDataSource = LocalDataSourceTest()
        repo = FakeRepo(localDataSource)
    }
    @Test
    fun getImagePathsFromMediaStore()= runBlockingTest {
        val  response =repo.getImagePathsFromMediaStore().getOrAwaitValue{}
        assertEquals(response.size,3)
    }

    @Test
    fun getVideoPathsFromMediaStore()=runBlockingTest {
        val  response =repo.getVideoPathsFromMediaStore().getOrAwaitValue{}
        assertEquals(response.size,4)
    }
}