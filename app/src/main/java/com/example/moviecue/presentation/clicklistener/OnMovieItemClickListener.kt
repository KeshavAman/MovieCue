package com.example.moviecue.presentation.clicklistener

import com.example.moviecue.models.SearchItem

/**
 * interface @OnMovieItemClickListener to handle click on movie items
 */
interface OnMovieItemClickListener {
    fun onMovieItemClick(movie:SearchItem)
}