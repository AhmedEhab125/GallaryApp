package com.example.gallaryapp.gallery.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.gallaryapp.fakeRepo.FakeRepo
import com.example.gallaryapp.localData.ILocalDataSource
import com.example.gallaryapp.localData.LocalDataSourceTest
import com.example.gallaryapp.movies.viewmodel.MoviesViewModel
import com.example.wetharapplication.MainDispatcherRule
import com.example.wetharapplication.getOrAwaitValue
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule

import org.junit.Test

class GalleryViewModelTest {
    lateinit var galleryViewModel : GalleryViewModel
    lateinit var  localDataSourceTest: ILocalDataSource
    @get:Rule
    var instanceExecuteRule = InstantTaskExecutorRule()

    @get:Rule
    var mainDispatcherRule = MainDispatcherRule()
    @Before
    fun setUp(){
        localDataSourceTest = LocalDataSourceTest()
        galleryViewModel = GalleryViewModel(FakeRepo(localDataSourceTest))
    }
    @Test
    fun getGalleryImgs() {
        galleryViewModel.getGalleryImgs()
        val result =galleryViewModel.accessLocalImagesData.getOrAwaitValue {  }
        assertEquals(result.size,3)
        assertNotNull(result)
    }
}