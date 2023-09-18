package com.example.gallaryapp.gallery.viewmodel

import android.net.Uri
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.gallaryapp.repo.IRepo
import com.example.gallaryapp.repo.Repository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject
@HiltViewModel

class GalleryViewModel @Inject constructor(private var repository: IRepo) : ViewModel() {
    private var _localImagesData = MutableStateFlow<List<Uri>>(listOf())
    var accessLocalImagesData: StateFlow<List<Uri>> = _localImagesData
    fun getGalleryImgs(){
        viewModelScope.launch {
            repository.getImagePathsFromMediaStore().collect{
                _localImagesData.value=it
            }
        }

    }
}