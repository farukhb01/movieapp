package com.example.movieapp.data.local.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.movieapp.data.local.dao.MovieDao
import com.example.movieapp.data.local.entity.MovieEntity

@Database(entities = [MovieEntity::class], version = 1)
abstract class MovieDatabase : RoomDatabase() {
    abstract fun movieDao(): MovieDao

    companion object {
        @Volatile
        private var instance: MovieDatabase? = null

        fun getDatabase(context: Context): MovieDatabase {
            if (instance == null) {
                synchronized(this) {
                    instance = Room.databaseBuilder(context.applicationContext,MovieDatabase::class.java,"movie_db").build()
                }
            }
            return instance !!
        }
    }
}
