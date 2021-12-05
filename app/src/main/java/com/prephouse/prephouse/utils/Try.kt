package com.prephouse.prephouse.utils

sealed class Try<out T> {
    abstract fun get(): T

    abstract fun <R> flatMap(tryBlock: (T) -> Try<R>): Try<R>

    data class Success<T>(private val result: T) : Try<T>() {
        override fun get(): T = result

        override fun <R> flatMap(tryBlock: (T) -> Try<R>): Try<R> {
            return try {
                tryBlock(result)
            } catch (e: Throwable) {
                Failure(e)
            }
        }
    }

    data class Failure<T>(private val throwable: Throwable) : Try<T>() {
        override fun get(): T = throw throwable

        override fun <R> flatMap(tryBlock: (T) -> Try<R>): Try<R> {
            return this as? Failure<R> ?: throw IllegalStateException()
        }
    }

    companion object {
        inline fun <T> of(crossinline block: () -> T) = try {
            Success(block())
        } catch (e: Throwable) {
            Failure(e)
        }
    }
}
