package com.example.myapplication.view.fragment.segera

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myapplication.model.ResponseMovie
import com.example.myapplication.model.ResultTrailer
import com.example.myapplication.repository.MovieRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import retrofit2.HttpException
import java.lang.Exception
import javax.inject.Inject

@HiltViewModel
class SegeraViewModel @Inject constructor(val repository: MovieRepository) : ViewModel() {

    private val message = MutableLiveData<String>()

    val segera = MutableLiveData<ResponseMovie?>()


    fun getSegera(page: Int): LiveData<ResponseMovie?> {
        viewModelScope.launch {
            repository.getSegeraMovie(page).let {
                try {
                    val data = it.body()
                    segera.value = data
                } catch (t: Throwable) {
                    when (t) {
                        is Exception -> message.value = t.message.toString()
                        is HttpException -> message.value = t.message().toString()
                        else -> message.value = "Unknow Error"
                    }
                }
            }
        }
        return segera
    }

    fun getTrailer(id: Int?): LiveData<ArrayList<ResultTrailer?>?> {
        val trailerMovie = MutableLiveData<ArrayList<ResultTrailer?>?>()
        viewModelScope.launch {
            repository.getSegeraTrailer(id).let {
                try {
                    val data = it.body()?.results
                    trailerMovie.value = data
                } catch (t: Throwable) {

                }
            }
        }
        return trailerMovie
    }


}