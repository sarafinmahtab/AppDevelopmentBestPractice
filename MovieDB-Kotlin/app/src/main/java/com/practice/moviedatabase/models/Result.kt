package com.practice.moviedatabase.models

/**
 * Resource holder provided to the UI
 */
class Result<T> private constructor(val status: Status, val data: T?, val error: Throwable?) {

    /**
     * Possible status types of a response provided to the UI
     */
    enum class Status {
        LOADING,
        SUCCESS,
        ERROR
    }

    companion object {

        fun <T> loading(): Result<T> {
            return Result(Status.LOADING, null, null)
        }

        fun <T> success(data: T): Result<T> {
            return Result(Status.SUCCESS, data, null)
        }

        fun <T> error(error: Throwable): Result<T> {
            return Result(Status.ERROR, null, error)
        }
    }
}
