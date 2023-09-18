package com.example.gallaryapp.movies

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.gallaryapp.R
import com.example.gallaryapp.databinding.FragmentMoviesBinding


class MoviesFragment : Fragment() {
lateinit var binding: FragmentMoviesBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding= FragmentMoviesBinding.inflate(layoutInflater)
        // Inflate the layout for this fragment
        return binding.root
    }


}