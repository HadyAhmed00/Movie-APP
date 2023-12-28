package io.github.hadyahmed00.movieapp.ui

import android.os.Build
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.annotation.RequiresExtension
import androidx.core.view.isVisible
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import io.github.hadyahmed00.movieapp.Adapters.MovieAdapter
import io.github.hadyahmed00.movieapp.databinding.FragmentHomeBinding
import kotlinx.coroutines.launch

class Home : Fragment() {



    private val viewModel: MainViewModel by lazy {
        ViewModelProvider(this)[MainViewModel::class.java]
    }

    private lateinit var binding:FragmentHomeBinding
    private lateinit var viewAdapter: MovieAdapter
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

            Toast.makeText(requireContext(), it.title, Toast.LENGTH_SHORT).show()
        })
        adapter = viewAdapter
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
                        Toast.makeText(requireContext(), it.error, Toast.LENGTH_SHORT).show()
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