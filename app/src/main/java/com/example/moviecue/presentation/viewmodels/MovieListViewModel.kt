package com.example.moviecue.presentation.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.moviecue.models.MovieList
import com.example.moviecue.myutils.DataState
import com.example.moviecue.repositories.MovieRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MovieListViewModel @Inject constructor(private val repository: MovieRepository) :
    ViewModel() {
    private val _dataState: MutableLiveData<DataState<MovieList?>> = MutableLiveData()
    val dataState: MutableLiveData<DataState<MovieList?>>
        get() = _dataState

    init {
        getMovieList("marvel")
    }

    fun getMovieList(title: String) {
        viewModelScope.launch {
            dataState.value = repository.getMovie(title, "movie")
        }

    }
}
