package com.example.movieapp.domain.mapper

interface Mapper<I, O> {
    fun map(input: I): O
}