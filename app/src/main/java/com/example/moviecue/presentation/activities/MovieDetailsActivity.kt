package com.example.moviecue.presentation.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.databinding.DataBindingUtil
import com.example.moviecue.R
import com.example.moviecue.databinding.ActivityMovieDetailsBinding
import com.example.moviecue.models.MovieDetails
import com.example.moviecue.myutils.DataState
import com.example.moviecue.myutils.MyConstant.Companion.IMDB_ID
import com.example.moviecue.presentation.viewmodels.MovieDetailsViewModel
import com.example.moviecue.presentation.viewmodels.MovieListViewModel
import dagger.hilt.android.AndroidEntryPoint
import timber.log.Timber

/**
 * @MovieDetailsActivity class responsible for a movie details
 */
@AndroidEntryPoint
class MovieDetailsActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMovieDetailsBinding
    private val viewModel: MovieDetailsViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_movie_details)
        actionBar?.setHomeButtonEnabled(true)
        actionBar?.setDisplayHomeAsUpEnabled(true)
        binding.lifecycleOwner = this
        binding.viewModel = viewModel
        val imdbId = intent?.getStringExtra(IMDB_ID)
        Timber.d("imdb id = $imdbId")
        imdbId?.let { viewModel.getMovieDetailsFlow(it) }
        subscribeLivedata()
    }

    /**
     * observe api response
     */
    private fun subscribeLivedata() {

        viewModel.dataState.observe(this) { data ->
            when (data) {
                is DataState.Loading -> {
                    viewModel.setLoading(true)
                }
                is DataState.Success -> {
                    viewModel.setLoading(false)
                    data.data?.let {
                        viewModel.setMovieDetails(it)
                        title = it.title
                    }

                }
                is DataState.Error -> {
                    viewModel.setLoading(false)
                }
            }
        }

    }
}