package com.example.gallaryapp.movies.view

import android.Manifest
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.example.gallaryapp.databinding.FragmentMoviesBinding
import com.example.gallaryapp.movies.viewmodel.MoviesViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MoviesFragment : Fragment() {
    private val STORAGE_PERMISSION_CODE = 2
    lateinit var binding: FragmentMoviesBinding
    lateinit var adapter: MoviesAdapter
    private val moviesViewModel: MoviesViewModel by viewModels()
    private val readVideoPermission = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q)
        Manifest.permission.READ_MEDIA_VIDEO
    else
        Manifest.permission.READ_EXTERNAL_STORAGE
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
        init()
        if (ContextCompat.checkSelfPermission(
                requireContext(),
                readVideoPermission
            )
            == PackageManager.PERMISSION_GRANTED
        ) {
            getVideos()
        } else {
            // Permission is not granted
            // Request the permission
            requestPermissions(
                arrayOf(readVideoPermission),
                STORAGE_PERMISSION_CODE
            )
        }


    }

    fun init() {
        adapter = MoviesAdapter(listOf())
        binding.moviesAdapter = adapter
    }

    fun getVideos() {

        lifecycleScope.launch {
            moviesViewModel.accessLocalVideosData.collect { videos ->
                if (!videos.isEmpty()) {
                    adapter.updateList(videos)
                    binding.noItems2.visibility = View.GONE

                } else {
                    binding.noItems2.visibility = View.VISIBLE
                }
            }
        }
        moviesViewModel.getVideos()
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)

        if (requestCode == STORAGE_PERMISSION_CODE) {
            if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                // Permission granted
                getVideos()
            } else {
                Toast.makeText(requireContext(), "Enable access storage", Toast.LENGTH_LONG).show()


            }
        }
    }


}