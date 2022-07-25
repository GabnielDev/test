package com.example.myapplication.repository

import com.example.myapplication.network.MovieServiceInstance
import javax.inject.Inject

class MovieRepository @Inject constructor(private val services: MovieServiceInstance) {

    suspend fun getNowPlayingMovie(page: Int) = services.getNowPlayingMovie(page)

    suspend fun getTopRatedMovie(page: Int) = services.getTopRatedMovie(page)

    suspend fun getSegeraMovie(page: Int) = services.getSegeraMovie(page)

    suspend fun getSegeraTrailer(id: Int?) = services.getSegeraTrailer(id)

}