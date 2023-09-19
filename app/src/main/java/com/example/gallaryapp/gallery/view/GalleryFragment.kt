package com.example.gallaryapp.gallery.view

import android.Manifest
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
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
    private val STORAGE_PERMISSION_CODE = 1952
    private lateinit var binding: FragmentGalleryBinding
    private lateinit var adapter: GalleryAdapter
    private val galleryViewModel: GalleryViewModel by viewModels()
    private val readImagePermission = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q)
        Manifest.permission.READ_MEDIA_IMAGES
    else
        Manifest.permission.READ_EXTERNAL_STORAGE
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

        if (ContextCompat.checkSelfPermission(
                requireContext(),
                readImagePermission
            )
            == PackageManager.PERMISSION_GRANTED
        ) {
            getGalleryImgs()
        } else {
            val permission = readImagePermission
            Log.i("TAG", "onViewCreated: ")
            requestPermissions(arrayOf(permission), STORAGE_PERMISSION_CODE)
        }

    }

    fun setUI() {
        adapter = GalleryAdapter(listOf())
        binding.galleryAdapter = adapter


    }

    fun getGalleryImgs() {
        lifecycleScope.launch {
            galleryViewModel.accessLocalImagesData.collect { imgsList ->
                if (!imgsList.isEmpty()) {
                    adapter.updateList(imgsList)
                    binding.noItems.visibility = View.GONE
                } else
                    binding.noItems.visibility = View.VISIBLE
            }
        }
        galleryViewModel.getGalleryImgs()

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
                getGalleryImgs()
            } else {
                Toast.makeText(requireContext(), "Enable access storage", Toast.LENGTH_LONG).show()

            }
        }
    }

}