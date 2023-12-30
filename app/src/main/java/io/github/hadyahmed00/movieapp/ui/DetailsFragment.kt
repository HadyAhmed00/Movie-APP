package io.github.hadyahmed00.movieapp.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import io.github.hadyahmed00.movieapp.databinding.FragmentDetailsBinding

class DetailsFragment : Fragment() {

    private val viewModel:MainViewModel by lazy {
        ViewModelProvider(this).get(MainViewModel::class.java)
    }

    private lateinit var binding: FragmentDetailsBinding



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentDetailsBinding.inflate(layoutInflater)
        binding.movie = arguments?.getParcelable("movie_item")



        return binding.root
    }

}