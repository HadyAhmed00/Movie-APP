package io.github.hadyahmed00.movieapp.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import io.github.hadyahmed00.movieapp.databinding.FragmentDetailsBinding

class DetailsFragment : Fragment() {

    private val viewModel:MainViewModel by activityViewModels()

    private lateinit var binding: FragmentDetailsBinding



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentDetailsBinding.inflate(layoutInflater)
        viewModel.clickedMovie.observe(viewLifecycleOwner, Observer {
            binding.movie = it
        })


        return binding.root
    }

}