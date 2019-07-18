package com.practice.moviedatabase.dal.repositories

import com.practice.moviedatabase.bll.TopRatedMovieUseCase
import com.practice.moviedatabase.dal.networks.ServerConstants
import com.practice.moviedatabase.models.Movie
import com.practice.moviedatabase.models.Result
import com.practice.moviedatabase.models.params.TopRatedMovieParams
import io.mockk.MockKAnnotations
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import org.hamcrest.CoreMatchers.`is`
import org.junit.Assert.assertThat
import org.junit.Before
import org.junit.Test

class TopRatedMovieRepositoryTest {

    private val useCaseMock = mockk<TopRatedMovieUseCase>()
    private val SUT: TopRatedMovieRepository = TopRatedMovieRepository(useCaseMock)

    @Before
    fun setUp() {
        MockKAnnotations.init(this, relaxUnitFun = true)
        successStubbing()
    }

    @Test
    fun fetchTopRatedMovie_success_successReturned() {

        val result = runBlocking {
            SUT.fetchTopRatedMovies(
                TopRatedMovieParams(ServerConstants.apiKey, ServerConstants.language, "1", ServerConstants.sortedBy)
            )
        }

        assertThat(result.status, `is`(Result.Status.SUCCESS))
    }

    private fun successStubbing() {

        every {

            runBlocking {
                useCaseMock(
                    TopRatedMovieParams(
                        ServerConstants.apiKey,
                        ServerConstants.language,
                        "1",
                        ServerConstants.sortedBy
                    )
                )
            }
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
