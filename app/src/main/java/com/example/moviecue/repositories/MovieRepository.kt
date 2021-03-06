package com.example.moviecue.repositories

import com.example.moviecue.data.ApiService
import com.example.moviecue.models.MovieDetails
import com.example.moviecue.models.MovieList
import com.example.moviecue.myutils.BaseApiResponse
import com.example.moviecue.myutils.DataState
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityRetainedComponent
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.withContext
import java.lang.Exception
import javax.inject.Inject

@Module
@InstallIn(ActivityRetainedComponent::class)
class MovieRepository @Inject constructor(private val apiService: ApiService) : BaseApiResponse() {
    /**
     * @param s is title of movie
     * @param type
     * @return @see DataState
     */
    suspend fun getMovie(s: String, type: String) = withContext(Dispatchers.IO) {
        DataState.Loading

        try {
            val result = apiService.getMovieList(s, type)
            if (result.isSuccessful) {
                DataState.Success(result.body())
            } else {
                DataState.Error(Exception(result.message()))
            }
        } catch (e: Exception) {
            DataState.Error(Exception(e.message))
        }

    }

    /**
     * @return flow Flow<DataState<MovieDetails>>
     *     @param id which is imdb id
     */
    suspend fun getMovieDetailsFlow(id: String): Flow<DataState<MovieDetails>> {
        return flow {
            emit(DataState.Loading)
            emit(safeApiCall { apiService.getMovieDetails(id) })
        }.catch { emit(DataState.Error(Exception("Network error"))) }
            .flowOn(Dispatchers.IO)

    }
}