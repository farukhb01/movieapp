package com.example.movieapp.di

import android.content.Context
import com.example.movieapp.core.constants.Constants.Companion.BASE_URL
import com.example.movieapp.data.local.datasource.LocalDataSource
import com.example.movieapp.data.local.datasource.LocalDataSourceImpl
import com.example.movieapp.data.local.db.database.MovieDatabase
import com.example.movieapp.data.remote.api.MovieApiService
import com.example.movieapp.data.remote.datasource.RemoteDataSource
import com.example.movieapp.data.remote.datasource.RemoteDataSourceImpl
import com.example.movieapp.data.repository.MovieRepositoryImpl
import com.example.movieapp.data.repository.IMovieRepository
import com.example.movieapp.domain.usecases.deletemovieusecase.DeleteMovieUseCase
import com.example.movieapp.domain.usecases.deletemovieusecase.IDeleteMovieUseCase
import com.example.movieapp.domain.usecases.getfavmoviesusecase.GetFavoriteMoviesUseCase
import com.example.movieapp.domain.usecases.getfavmoviesusecase.IGetFavoriteMoviesUseCase
import com.example.movieapp.domain.usecases.getmoviesusecase.GetPagedMoviesUseCase
import com.example.movieapp.domain.usecases.getmoviesusecase.IGetPagedMoviesUseCase
import com.example.movieapp.domain.usecases.insertmovieusecase.IInsertMovieUseCase
import com.example.movieapp.domain.usecases.insertmovieusecase.InsertMovieUseCase

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class AppContainer(context: Context) {

    private val retrofit: Retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    private val movieApiService: MovieApiService = retrofit.create(MovieApiService::class.java)

    private val localDataSource: LocalDataSource = LocalDataSourceImpl(
        MovieDatabase.getDatabase(context).movieDao()
    )
    private val remoteDataSource: RemoteDataSource = RemoteDataSourceImpl(movieApiService)

    private val movieRepository: IMovieRepository = MovieRepositoryImpl(
        localDataSource = localDataSource,
        remoteDataSource = remoteDataSource
    )

    val getPagedMoviesUseCase: IGetPagedMoviesUseCase = GetPagedMoviesUseCase(movieRepository)
    val getFavoriteMoviesUseCase: IGetFavoriteMoviesUseCase = GetFavoriteMoviesUseCase(movieRepository)
    val insertMovieUseCase: IInsertMovieUseCase = InsertMovieUseCase(movieRepository)
    val deleteMovieUseCase: IDeleteMovieUseCase = DeleteMovieUseCase(movieRepository)
}
