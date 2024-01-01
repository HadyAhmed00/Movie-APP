package io.github.hadyahmed00.movieapp.ui

import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.annotation.RequiresExtension
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import io.github.hadyahmed00.movieapp.Adapters.MovieAdapter
import io.github.hadyahmed00.movieapp.R
import io.github.hadyahmed00.movieapp.databinding.FragmentHomeBinding
import kotlinx.coroutines.launch

class HomeFragent : Fragment() {

    private val viewModel: MainViewModel by activityViewModels()

    private lateinit var binding:FragmentHomeBinding
    private lateinit var viewAdapter: MovieAdapter
    private val TAG = "Home Fragment"
    @RequiresExtension(extension = Build.VERSION_CODES.S, version = 7)
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHomeBinding.inflate(layoutInflater)
        binding.movieRc
        lifecycleScope.launch {
            binding.progressBar.isVisible = true
            viewModel.userIntent.send(MainIntent.FetchMovies)
            setUpRc()
            observe()
        }

        return binding.root
    }

    private fun setUpRc()  = binding.movieRc.apply {

        viewAdapter = MovieAdapter(MovieAdapter.OnClickListener{
            viewModel.setClickedMovie(it)
            findNavController().navigate(R.id.action_home2_to_detailsFragment)
            Toast.makeText(requireContext(), it.title, Toast.LENGTH_SHORT).show()
        })
        adapter = viewAdapter
//        layoutManager = StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
        layoutManager = LinearLayoutManager(requireContext())
    }
    private fun observe() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.state.collect {
                when (it) {
                    is MainState.Init -> Unit
                    is MainState.Loading -> {
                        binding.progressBar.isVisible = true
                        binding.movieRc.isVisible = false
                    }
                    is MainState.Error -> {
                        binding.progressBar.isVisible = false
                        Toast.makeText(requireContext(), "Check your connection", Toast.LENGTH_SHORT).show()
                        Log.i(TAG, it.error.toString())
                    }
                    is MainState.MoviesData -> {
                        binding.progressBar.isVisible = false
                        binding.movieRc.isVisible = true
                        viewAdapter.submitList(it.movies)

                    }
                }
            }
        }
    }
}