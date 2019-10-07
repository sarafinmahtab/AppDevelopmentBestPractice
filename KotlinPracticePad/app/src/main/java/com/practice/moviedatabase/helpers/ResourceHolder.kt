package com.practice.moviedatabase.helpers

/**
 * Resource holder provided to the UI
 */
class ResourceHolder<T> private constructor(val status: Status, val data: T?, val error: Throwable?) {

    /**
     * Possible status types of a response provided to the UI
     */
    enum class Status {
        LOADING,
        SUCCESS,
        ERROR
    }

    companion object {

        fun <T> loading(): ResourceHolder<T> {
            return ResourceHolder(
                Status.LOADING,
                null,
                null
            )
        }

        fun <T> success(data: T): ResourceHolder<T> {
            return ResourceHolder(
                Status.SUCCESS,
                data,
                null
            )
        }

        fun <T> error(error: Throwable): ResourceHolder<T> {
            return ResourceHolder(
                Status.ERROR,
                null,
                error
            )
        }
    }
}
