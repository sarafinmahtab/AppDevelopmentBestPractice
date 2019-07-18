package com.practice.moviedatabase.dal.repositories

import android.accounts.NetworkErrorException
import com.practice.moviedatabase.bll.GenreMovieUseCase
import com.practice.moviedatabase.bll.TopRatedMovieUseCase
import com.practice.moviedatabase.dal.networks.ServerConstants
import com.practice.moviedatabase.models.Genre
import com.practice.moviedatabase.models.Genres
import com.practice.moviedatabase.models.Movie
import com.practice.moviedatabase.models.Result
import com.practice.moviedatabase.models.params.GenreParams
import com.practice.moviedatabase.models.params.TopRatedMovieParams
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import org.hamcrest.CoreMatchers.`is`
import org.hamcrest.CoreMatchers.instanceOf
import org.junit.Assert.assertThat
import org.junit.Before
import org.junit.Test

class TopRatedMovieRepositoryTest {

    private val topRatedMovieUseCase = mockk<TopRatedMovieUseCase>()
    private val genreUseCase = mockk<GenreMovieUseCase>()
    private val SUT: TopRatedMovieRepository = TopRatedMovieRepository(topRatedMovieUseCase, genreUseCase)

    private val topRatedMovieParams =
        TopRatedMovieParams(ServerConstants.apiKey, ServerConstants.language, "1", ServerConstants.sortedBy)
    private val genreParams =
        GenreParams(ServerConstants.apiKey, ServerConstants.language)


    @Before
    fun setUp() {
        MockKAnnotations.init(this, relaxUnitFun = true)
        successStubbing()
    }


    @Test
    fun fetchTopRatedMovie_correctParameterPassed() {

        runBlocking {
            SUT.fetchTopRatedMovies(topRatedMovieParams)
        }

        coVerify(exactly = 1) {
            topRatedMovieUseCase.invoke(topRatedMovieParams)
        }
    }

    @Test
    fun fetchTopRatedMovie_success_successReturned() {

        val result = runBlocking {
            SUT.fetchTopRatedMovies(topRatedMovieParams)
        }

        assertThat(result.status, `is`(Result.Status.SUCCESS))
        assertThat(result.data, `is`(instanceOf(List::class.java)))
    }

    @Test
    fun fetchTopRatedMovie_generalError_errorReturned() {

        generalError()

        val result = runBlocking {
            SUT.fetchTopRatedMovies(topRatedMovieParams)
        }

        assertThat(result.status, `is`(Result.Status.ERROR))
    }

    @Test
    fun fetchTopRatedMovie_networkError_errorReturned() {

        networkError()

        val result = runBlocking {
            SUT.fetchTopRatedMovies(topRatedMovieParams)
        }

        assertThat(result.status, `is`(Result.Status.ERROR))
        assertThat(result.error, `is`(instanceOf(NetworkErrorException::class.java)))
    }


    @Test
    fun fetchGenres_correctParameterPassed() {

        runBlocking {
            SUT.fetchGenres(genreParams)
        }

        coVerify(exactly = 1) {
            genreUseCase.invoke(genreParams)
        }
    }

    @Test
    fun fetchGenres_success_successReturned() {

        val result = runBlocking {
            SUT.fetchGenres(genreParams)
        }

        assertThat(result.status, `is`(Result.Status.SUCCESS))
        assertThat(result.data, `is`(instanceOf(Genres::class.java)))
    }

    @Test
    fun fetchGenres_generalError_errorReturned() {

        generalError()

        val result = runBlocking {
            SUT.fetchGenres(genreParams)
        }

        assertThat(result.status, `is`(Result.Status.ERROR))
    }

    @Test
    fun fetchGenres_networkError_errorReturned() {

        networkError()

        val result = runBlocking {
            SUT.fetchGenres(genreParams)
        }

        assertThat(result.status, `is`(Result.Status.ERROR))
        assertThat(result.error, `is`(instanceOf(NetworkErrorException::class.java)))
    }


    private fun networkError() {
        coEvery {
            topRatedMovieUseCase(topRatedMovieParams)
        } returns Result.error(NetworkErrorException("Network Error Exception caught"))

        coEvery {
            genreUseCase(genreParams)
        } returns Result.error(NetworkErrorException("Network Error Exception caught"))
    }

    private fun generalError() {
        coEvery {
            topRatedMovieUseCase(topRatedMovieParams)
        } returns Result.error(Exception("Exception caught"))

        coEvery {
            genreUseCase(genreParams)
        } returns Result.error(Exception("Exception caught"))
    }

    private fun successStubbing() {

        coEvery {
            topRatedMovieUseCase(topRatedMovieParams)
        } returns Result.success(
            listOf(
                Movie(
                    0,
                    0,
                    false,
                    0.0,
                    "",
                    0.0,
                    null,
                    null,
                    null,
                    null,
                    "",
                    false,
                    "",
                    ""
                )
            )
        )

        coEvery {
            genreUseCase(genreParams)
        } returns Result.success(
            Genres(
                listOf(
                    Genre(1, "Adventure")
                )
            )
        )
    }
}
