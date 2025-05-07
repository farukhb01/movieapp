package com.example.movieapp.data.mapper

interface Mapper<I, O> {
    fun map(input: I): O
}