package com.example.movieapp.ui.fragments.movies

import android.widget.Button
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.movieapp.MovieApp
import com.example.movieapp.core.base.fragment.BaseFragment
import com.example.movieapp.databinding.FragmentMovieBinding
import com.example.movieapp.ui.adapter.MovieAdapter
import com.example.movieapp.ui.adapter.MovieLoadStateAdapter
import com.example.movieapp.ui.fragments.favmovies.FavoriteMovieIntent
import com.example.movieapp.ui.viewmodel.MovieViewModel
import com.example.movieapp.ui.viewmodel.MovieViewModelFactory
import kotlinx.coroutines.launch


class MovieFragment : BaseFragment<FragmentMovieBinding>(FragmentMovieBinding::inflate) {

    private lateinit var movieViewModel: MovieViewModel
    private lateinit var adapter: MovieAdapter


    override fun onFragmentAttach() {
        val application = requireActivity().application as MovieApp
        val container = application.appContainer

        movieViewModel = ViewModelProvider(
            this,
            MovieViewModelFactory(
                getPagedMoviesUseCase = container.getPagedMoviesUseCase,
                getFavoriteMoviesUseCase = container.getFavoriteMoviesUseCase,
                insertMovieUseCase = container.insertMovieUseCase,
                deleteMovieUseCase = container.deleteMovieUseCase
            )
        ).get(MovieViewModel::class.java)
    }

    override fun setupViews() {
        setupAdapter()
        setupRecyclerView()
        setupObservers()
        setupButtonListeners()
        movieViewModel.processIntent(MovieIntent.LoadMovies("popular"))
        highlightButton(binding.btnPopular)
    }

    private fun setupAdapter() {
        adapter = MovieAdapter(
            isFavoriteScreen = false,
            onIconClick = { movie ->
                movieViewModel.processFavoriteIntent(FavoriteMovieIntent.AddFavorite(movie))
                showToast("Added to Favourites")
            }
        )

        binding.recyclerView.adapter = adapter.withLoadStateFooter(
            footer = MovieLoadStateAdapter()
        )
    }

    private fun setupRecyclerView() {
        binding.recyclerView.layoutManager = LinearLayoutManager(requireContext())
    }

    private fun setupObservers() {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                movieViewModel.movieState.collect { state ->
                    if (state is MovieState.MoviesLoaded) {
                        adapter.submitData(lifecycle, state.pagingData)
                    }
                }
            }
        }
    }

    private fun setupButtonListeners() {
        binding.btnPopular.setOnClickListener {
            movieViewModel.processIntent(MovieIntent.LoadMovies("popular"))
            highlightButton(binding.btnPopular)
        }
        binding.btnTopRated.setOnClickListener {
            movieViewModel.processIntent(MovieIntent.LoadMovies("top_rated"))
            highlightButton(binding.btnTopRated)
        }
        binding.btnUpcoming.setOnClickListener {
            movieViewModel.processIntent(MovieIntent.LoadMovies("upcoming"))
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

