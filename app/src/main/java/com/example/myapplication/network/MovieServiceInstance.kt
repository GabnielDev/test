package com.example.myapplication.network

import com.example.myapplication.model.MovieTrailer
import com.example.myapplication.model.ResponseMovie
import com.example.myapplication.utils.Constants.URL_NOWPLAYING_MOVIE
import com.example.myapplication.utils.Constants.URL_TOPRATED_MOVIE
import com.example.myapplication.utils.Constants.URL_TRAILER_MOVIE
import com.example.myapplication.utils.Constants.URL_UPCOMING_MOVIE
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MovieServiceInstance {

    @GET(URL_NOWPLAYING_MOVIE)
    suspend fun getNowPlayingMovie(
        @Query("page") page: Int
    ): Response<ResponseMovie>

    @GET(URL_TOPRATED_MOVIE)
    suspend fun getTopRatedMovie(
        @Query("page") page: Int
    ): Response<ResponseMovie>

    @GET(URL_UPCOMING_MOVIE)
    suspend fun getSegeraMovie(
        @Query("page") page: Int
    ): Response<ResponseMovie>

    @GET(URL_TRAILER_MOVIE)
    suspend fun getSegeraTrailer(
        @Path("movie_id") id: Int?
    ): Response<MovieTrailer>
}