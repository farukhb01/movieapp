package com.example.movieapp

import android.app.Application
import com.example.movieapp.data.local.database.MovieDatabase
import com.example.movieapp.data.remote.api.MovieApiService
import com.example.movieapp.data.repository.MovieRepository
import com.example.movieapp.domain.usecases.DeleteMovieUseCase
import com.example.movieapp.domain.usecases.GetFavoriteMoviesUseCase
import com.example.movieapp.domain.usecases.GetPagedMoviesUseCase
import com.example.movieapp.domain.usecases.InsertMovieUseCase
import com.example.movieapp.core.constants.Constants.Companion.BASE_URL
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MovieApp: Application() {
    private lateinit var retrofit: Retrofit
    private lateinit var movieApiService: MovieApiService
    private lateinit var movieDatabase: MovieDatabase
    private lateinit var movieRepository: MovieRepository

    lateinit var getPagedMoviesUseCase: GetPagedMoviesUseCase
    lateinit var getFavoriteMoviesUseCase: GetFavoriteMoviesUseCase
    lateinit var insertMovieUseCase: InsertMovieUseCase
    lateinit var deleteMovieUseCase: DeleteMovieUseCase

    override fun onCreate() {
        super.onCreate()
        initializeDependencies()
    }

    private fun initializeDependencies() {
        retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        movieApiService = retrofit.create(MovieApiService::class.java)

        movieDatabase = MovieDatabase.getDatabase(this)

        movieRepository = MovieRepository(
            movieDatabase.movieDao(),
            movieApiService
        )

        getPagedMoviesUseCase = GetPagedMoviesUseCase(movieRepository)
        getFavoriteMoviesUseCase = GetFavoriteMoviesUseCase(movieRepository)
        insertMovieUseCase = InsertMovieUseCase(movieRepository)
        deleteMovieUseCase = DeleteMovieUseCase(movieRepository)
    }
}