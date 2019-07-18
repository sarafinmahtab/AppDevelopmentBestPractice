package com.practice.moviedatabase.dal.repositories

import android.accounts.NetworkErrorException
import com.practice.moviedatabase.bll.TopRatedMovieUseCase
import com.practice.moviedatabase.dal.networks.ServerConstants
import com.practice.moviedatabase.models.Movie
import com.practice.moviedatabase.models.Result
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

    private val useCaseMock = mockk<TopRatedMovieUseCase>()
    private val SUT: TopRatedMovieRepository = TopRatedMovieRepository(useCaseMock)

    private val params =
        TopRatedMovieParams(ServerConstants.apiKey, ServerConstants.language, "1", ServerConstants.sortedBy)

    @Before
    fun setUp() {
        MockKAnnotations.init(this, relaxUnitFun = true)
        successStubbing()
    }

    @Test
    fun fetchTopRatedMovie_correctParameterPassed() {

        runBlocking {
            SUT.fetchTopRatedMovies(params)
        }

        coVerify (exactly = 1) {
            useCaseMock.invoke(params)
        }
    }

    @Test
    fun fetchTopRatedMovie_success_successReturned() {

        val result = runBlocking {
            SUT.fetchTopRatedMovies(params)
        }

        assertThat(result.status, `is`(Result.Status.SUCCESS))
        assertThat(result.data, `is`(instanceOf(List::class.java)))
    }

    @Test
    fun fetchTopRatedMovie_generalError_errorReturned() {

        generalError()

        val result = runBlocking {
            SUT.fetchTopRatedMovies(params)
        }

        assertThat(result.status, `is`(Result.Status.ERROR))
    }

    @Test
    fun fetchTopRatedMovie_networkError_errorReturned() {

        networkError()

        val result = runBlocking {
            SUT.fetchTopRatedMovies(params)
        }

        assertThat(result.status, `is`(Result.Status.ERROR))
        assertThat(result.error, `is`(instanceOf(NetworkErrorException::class.java)))
    }


    private fun networkError() {
        coEvery {
            useCaseMock(params)
        } returns Result.error(NetworkErrorException("Network Error Exception caught"))
    }

    private fun generalError() {
        coEvery {
            useCaseMock(params)
        } returns Result.error(Exception("Exception caught"))
    }

    private fun successStubbing() {

        coEvery {
            useCaseMock(params)
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
    }
}
