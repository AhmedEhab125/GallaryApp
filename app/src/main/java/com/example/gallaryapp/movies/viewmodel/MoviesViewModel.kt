package com.example.gallaryapp.movies.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.gallaryapp.repo.IRepo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject
@HiltViewModel
class MoviesViewModel@Inject constructor( private var repository: IRepo) : ViewModel() {
    private var _localVideosData = MutableStateFlow<List<String>>(listOf())
    var accessLocalVideosData: StateFlow<List<String>> = _localVideosData
    fun getVideos(){
        viewModelScope.launch {
            repository.getVideoPathsFromMediaStore().collect{
                _localVideosData.value=it
            }
        }

    }
}