package com.example.gallaryapp.gallery.view

import android.database.Cursor
import android.os.Bundle
import android.provider.MediaStore
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.example.gallaryapp.R
import com.example.gallaryapp.databinding.FragmentGalleryBinding
import com.example.gallaryapp.gallery.viewmodel.GalleryViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint

class GalleryFragment : Fragment() {

    private lateinit var binding: FragmentGalleryBinding
    private lateinit var adapter: GalleryAdapter
    val galleryViewModel: GalleryViewModel by viewModels()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding =
            DataBindingUtil.inflate(layoutInflater, R.layout.fragment_gallery, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUI()
        getGalleryImgs()
    }

    fun setUI() {
        adapter = GalleryAdapter(listOf())
        binding.adapter = adapter


    }
    fun getGalleryImgs(){
        galleryViewModel.getGalleryImgs()
        lifecycleScope.launch {
            galleryViewModel.accessLocalProductsData.collect{
                    imgsList->
                adapter.updateList(imgsList)
            }
        }

    }


}