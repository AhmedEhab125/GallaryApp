package com.example.gallaryapp.movies.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.gallaryapp.fakeRepo.FakeRepo
import com.example.gallaryapp.localData.ILocalDataSource
import com.example.gallaryapp.localData.LocalDataSourceTest
import com.example.gallaryapp.repo.IRepo
import com.example.wetharapplication.MainDispatcherRule
import com.example.wetharapplication.getOrAwaitValue
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule

import org.junit.Test

class MoviesViewModelTest {
lateinit var moviesViewModel :MoviesViewModel
lateinit var  localDataSourceTest: ILocalDataSource
    @get:Rule
    var instanceExecuteRule = InstantTaskExecutorRule()

    @get:Rule
    var mainDispatcherRule = MainDispatcherRule()
    @Before
    fun setUp(){
        localDataSourceTest = LocalDataSourceTest()
        moviesViewModel = MoviesViewModel(FakeRepo(localDataSourceTest))
    }
    @Test
    fun getVideos()= mainDispatcherRule.runBlockingTest {
        moviesViewModel.getVideos()
        val result = moviesViewModel.accessLocalVideosData.getOrAwaitValue {  }
        assertEquals(result.size,4)
        assertNotNull(result)
    }
}