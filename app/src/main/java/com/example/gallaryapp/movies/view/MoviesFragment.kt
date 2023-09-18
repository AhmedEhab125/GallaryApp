package com.example.gallaryapp.movies.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.example.gallaryapp.databinding.FragmentMoviesBinding
import com.example.gallaryapp.gallery.viewmodel.GalleryViewModel
import com.example.gallaryapp.movies.viewmodel.MoviesViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint

class MoviesFragment : Fragment() {
    lateinit var binding: FragmentMoviesBinding
    lateinit var adapter: MoviesAdapter
    private val moviesViewModel: MoviesViewModel by viewModels()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMoviesBinding.inflate(layoutInflater)
        // Inflate the layout for this fragment
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        adapter = MoviesAdapter(listOf())
        binding.moviesAdapter = adapter
        moviesViewModel.getVideos()
        lifecycleScope.launch {
            moviesViewModel.accessLocalVideosData.collect {
                adapter.updateList(it)
            }
        }


    }


}