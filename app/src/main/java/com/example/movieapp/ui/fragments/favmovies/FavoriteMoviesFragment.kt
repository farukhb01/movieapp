package com.example.movieapp.ui.fragments.favmovies


import android.widget.Toast
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.paging.PagingData
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.movieapp.MovieApp
import com.example.movieapp.core.base.fragment.BaseFragment
import com.example.movieapp.data.local.db.entity.MovieEntity
import com.example.movieapp.data.remote.api.MovieDto
import com.example.movieapp.databinding.FragmentFavoriteMoviesBinding
import com.example.movieapp.domain.mapper.DomainToDtoMapper
import com.example.movieapp.data.mapper.EntityToDomainMapper
import com.example.movieapp.ui.adapter.MovieAdapter
import com.example.movieapp.ui.viewmodel.MovieViewModel
import com.example.movieapp.ui.viewmodel.MovieViewModelFactory
import kotlinx.coroutines.launch

class FavoriteMoviesFragment : BaseFragment<FragmentFavoriteMoviesBinding>(
    FragmentFavoriteMoviesBinding::inflate
) {

    private lateinit var viewModel: MovieViewModel
    private lateinit var adapter: MovieAdapter

    override fun onFragmentAttach() {
        val application = requireActivity().application as MovieApp
        val container = application.appContainer

        viewModel = ViewModelProvider(
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
        viewModel.processFavoriteIntent(FavoriteMovieIntent.LoadFavorites)
    }

    private fun setupAdapter() {
        adapter = MovieAdapter(
            isFavoriteScreen = true,
            onIconClick = { movie ->
                viewModel.processFavoriteIntent(FavoriteMovieIntent.RemoveFavorite(movie.id))
                showToast("Removed from Favourites")
            }
        )
    }

    private fun setupRecyclerView() {
        binding.rvFavoriteMovies.layoutManager = LinearLayoutManager(requireContext())
        binding.rvFavoriteMovies.adapter = adapter
    }

    fun List<MovieEntity>.toPagingDataDto(): PagingData<MovieDto> {
        val entityToDomain = EntityToDomainMapper()
        val domainToDto = DomainToDtoMapper()
        val dtoList = this.map { entity ->
            domainToDto.map(entityToDomain.map(entity))
        }
        return PagingData.from(dtoList)
    }

    private fun setupObservers() {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.favoriteState.collect { state: FavoriteMovieState? ->
                    when (state) {
                        is FavoriteMovieState.FavoritesLoaded ->
                            adapter.submitData(state.movies.toPagingDataDto())

                        is FavoriteMovieState.FavoriteUpdated ->
                            showToast(state.message)

                        else -> {}
                    }
                }
            }
        }
    }

    private fun showToast(message: String) {
        Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show()
    }
}
