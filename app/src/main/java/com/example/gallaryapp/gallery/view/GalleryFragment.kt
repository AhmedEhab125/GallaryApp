package com.example.gallaryapp.gallery.view

import android.Manifest
import android.content.pm.PackageManager
import android.database.Cursor
import android.os.Bundle
import android.provider.MediaStore
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
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
    private val STORAGE_PERMISSION_CODE = 1
    private lateinit var binding: FragmentGalleryBinding
    private lateinit var adapter: GalleryAdapter
    private val galleryViewModel: GalleryViewModel by viewModels()
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
        if (ContextCompat.checkSelfPermission(requireContext(), Manifest.permission.READ_EXTERNAL_STORAGE)
            == PackageManager.PERMISSION_GRANTED) {
            getGalleryImgs()
        } else {
            // Permission is not granted
            // Request the permission
            requestPermissions(
                arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE),
                STORAGE_PERMISSION_CODE
            )
        }

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
    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)

        if (requestCode == STORAGE_PERMISSION_CODE) {
            if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                // Permission granted, you can now access external storage
                getGalleryImgs()
                println("d5aaaaaaaaaaaaal")
            } else {
                // Permission denied, handle it accordingly (e.g., show a message to the user)
                Toast.makeText(requireContext(),"enable access storage",Toast.LENGTH_LONG).show()
                println("d5aaaaaaaaaaaaal")
                println("d5aaaaaaaaaaaaal")
            }
        }
    }

}