package com.example.movieapp.data.local.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.movieapp.data.local.entity.MovieEntity

@Dao
interface MovieDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMovie(movie: MovieEntity)

    @Query("SELECT * FROM movies")
    fun getAllMovies(): LiveData<List<MovieEntity>>

    @Query("DELETE FROM movies WHERE id = :movieId")
    suspend fun deleteMovieById(movieId: Int)
}
