package com.example.myapplication.view.fragment.beranda

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myapplication.model.ResponseMovie
import com.example.myapplication.repository.MovieRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import retrofit2.HttpException
import retrofit2.Response
import java.lang.Exception
import javax.inject.Inject

@HiltViewModel
class BerandaViewModel @Inject constructor(val repository: MovieRepository): ViewModel() {

    private val loading = MutableLiveData<Boolean>()
    private val status = MutableLiveData<Int>()
    private val message = MutableLiveData<String>()

    val playing = MutableLiveData<ResponseMovie?>()
    val toprated = MutableLiveData<ResponseMovie?>()

    fun getNowPlaying(page: Int): LiveData<ResponseMovie?> {
        loading.value = true
        viewModelScope.launch {
            repository.getNowPlayingMovie(page).let {
                try {
                    val data = it.body()
                    playing.value = data
                    loading.value = false
                } catch (t: Throwable) {
                    when(t) {
                        is Exception -> message.value = t.message.toString()
                        is HttpException -> message.value = t.message().toString()
                        else -> message.value = "Unknow Error"
                    }
                }
                loading.value = false
            }
        }
        return playing
    }

    fun getTopRated(page: Int): LiveData<ResponseMovie?> {
        loading.value = true
        viewModelScope.launch {
            repository.getTopRatedMovie(page).let {
                try {
                    val data = it.body()
                    toprated.value = data
                } catch (t: Throwable) {
                    when(t) {
                        is Exception -> message.value = t.message.toString()
                        is HttpException -> message.value = t.message().toString()
                        else -> message.value = "Unknow Error"
                    }
                }
            }
        }
        return toprated
    }

}