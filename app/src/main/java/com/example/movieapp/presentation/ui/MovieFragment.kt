package com.example.movieapp.presentation.ui

import android.widget.Button
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.movieapp.MovieApp
import com.example.movieapp.core.base.fragment.BaseFragment
import com.example.movieapp.databinding.FragmentMovieBinding
import com.example.movieapp.presentation.adapter.MovieAdapter
import com.example.movieapp.presentation.viewmodel.MovieViewModel
import com.example.movieapp.presentation.viewmodel.MovieViewModelFactory


class MovieFragment : BaseFragment<FragmentMovieBinding>(FragmentMovieBinding::inflate) {

    private lateinit var movieViewModel: MovieViewModel
    private lateinit var adapter: MovieAdapter
    private var category: String = "popular"

    override fun onFragmentAttach() {
        val application = requireActivity().application as MovieApp
        val factory = MovieViewModelFactory(
            getPagedMoviesUseCase = application.getPagedMoviesUseCase,
            getFavoriteMoviesUseCase = application.getFavoriteMoviesUseCase,
            insertMovieUseCase = application.insertMovieUseCase,
            deleteMovieUseCase = application.deleteMovieUseCase
        )
        movieViewModel = ViewModelProvider(this, factory).get(MovieViewModel::class.java)
    }

    override fun setupViews() {
        setupAdapter()
        setupRecyclerView()
        setupObservers()
        setupButtonListeners()
        movieViewModel.fetchPagedMovies(category)
        highlightButton(binding.btnPopular)
    }

    private fun setupAdapter() {
        adapter = MovieAdapter(
            isFavoriteScreen = false,
            onIconClick = { movie ->
                movieViewModel.insertMovie(movie)
                showToast("Added to Favourites")
            }
        )
    }

    private fun setupRecyclerView() {
        binding.recyclerView.layoutManager = LinearLayoutManager(requireContext())
        binding.recyclerView.adapter = adapter
    }

    private fun setupObservers() {
        movieViewModel.pagedMovies.observe(viewLifecycleOwner) { pagingData ->
            adapter.submitData(lifecycle, pagingData)
        }
    }

    private fun setupButtonListeners() {
        binding.btnPopular.setOnClickListener {
            category = "popular"
            movieViewModel.fetchPagedMovies(category)
            highlightButton(binding.btnPopular)
        }
        binding.btnTopRated.setOnClickListener {
            category = "top_rated"
            movieViewModel.fetchPagedMovies(category)
            highlightButton(binding.btnTopRated)
        }
        binding.btnUpcoming.setOnClickListener {
            category = "upcoming"
            movieViewModel.fetchPagedMovies(category)
            highlightButton(binding.btnUpcoming)
        }
    }

    private fun highlightButton(selectedButton: Button) {
        listOf(binding.btnPopular, binding.btnTopRated, binding.btnUpcoming).forEach { button ->
            button.setBackgroundColor(ContextCompat.getColor(requireContext(), android.R.color.darker_gray))
        }
        selectedButton.setBackgroundColor(ContextCompat.getColor(requireContext(), android.R.color.holo_blue_light))
    }

    private fun showToast(message: String) {
        Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show()
    }
}

