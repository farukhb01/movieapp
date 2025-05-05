package com.example.movieapp.presentation.ui


import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.paging.PagingData
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.movieapp.MovieApp
import com.example.movieapp.core.base.fragment.BaseFragment
import com.example.movieapp.databinding.FragmentFavoriteMoviesBinding
import com.example.movieapp.presentation.adapter.MovieAdapter
import com.example.movieapp.presentation.viewmodel.MovieViewModel
import com.example.movieapp.presentation.viewmodel.MovieViewModelFactory
import kotlinx.coroutines.launch

class FavoriteMoviesFragment : BaseFragment<FragmentFavoriteMoviesBinding>(
    FragmentFavoriteMoviesBinding::inflate
) {

    private lateinit var viewModel: MovieViewModel
    private lateinit var adapter: MovieAdapter

    override fun onFragmentAttach() {
        val application = requireActivity().application as MovieApp
        viewModel = ViewModelProvider(
            this,
            MovieViewModelFactory(
                application.getPagedMoviesUseCase,
                application.getFavoriteMoviesUseCase,
                application.insertMovieUseCase,
                application.deleteMovieUseCase
            )
        ).get(MovieViewModel::class.java)
    }

    override fun setupViews() {
        setupAdapter()
        setupRecyclerView()
        setupObservers()
    }


    private fun <T : Any> List<T>.toPagingData(): PagingData<T> {
        return PagingData.from(this)
    }

    private fun setupAdapter() {
        adapter = MovieAdapter(isFavoriteScreen = true) { movie ->
            viewModel.deleteMovieById(movie.id)
            showToast("Removed from Favourite")
        }
    }

    private fun setupRecyclerView() {
        binding.rvFavoriteMovies.layoutManager = LinearLayoutManager(requireContext())
        binding.rvFavoriteMovies.adapter = adapter
    }

    private fun setupObservers() {
        viewModel.favoriteMovies.observe(viewLifecycleOwner) {
            val pagingData = it.toPagingData()
            lifecycleScope.launch {
                adapter.submitData(pagingData)
            }
        }
    }

    private fun showToast(message: String) {
        Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show()
    }
}

