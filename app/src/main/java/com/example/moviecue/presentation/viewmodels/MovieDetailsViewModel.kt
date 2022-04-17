package com.example.moviecue.presentation.viewmodels

import androidx.lifecycle.*
import com.example.moviecue.models.MovieDetails
import com.example.moviecue.models.MovieList
import com.example.moviecue.myutils.DataState
import com.example.moviecue.repositories.MovieRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

/**
 * @MovieDetailsViewModel handle logic for details of movie
 */
@HiltViewModel
class MovieDetailsViewModel @Inject constructor(private val repository: MovieRepository) :
    ViewModel() {
    private val _dataState: MutableLiveData<DataState<MovieDetails?>> = MutableLiveData()
    val dataState: LiveData<DataState<MovieDetails?>>
        get() = _dataState
    private val _movieDetails: MutableLiveData<MovieDetails> = MutableLiveData()
    val movieDetails: LiveData<MovieDetails>
        get() = _movieDetails
    private val _isLoading: MutableLiveData<Boolean> = MutableLiveData()
    val isLoading: LiveData<Boolean>
        get() = _isLoading

    init {
        Timber.d("MovieDetailsViewModel initiated")
    }

    /**
     * observe the api call as flow and set the
     * response as datastate live data
     */
    fun getMovieDetailsFlow(id: String) = viewModelScope.launch {
        repository.getMovieDetailsFlow(id).collect {
            _dataState.value = it
        }
    }

    fun setMovieDetails(movieDetails: MovieDetails){
        _movieDetails.value = movieDetails
    }
    fun setLoading(loading:Boolean){
        _isLoading.value=loading
    }

}