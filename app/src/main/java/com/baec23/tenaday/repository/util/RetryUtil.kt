package com.baec23.tenaday.repository.util

import kotlinx.coroutines.delay

private const val NUM_RETRIES = 3
private const val INITIAL_RETRY_DELAY_MS = 500L
private const val RETRY_BACKOFF_MULTIPLIER = 2.0

object RetryUtil {
    suspend fun <T> withRetry(
        numRetries: Int = NUM_RETRIES,
        initialRetryDelay: Long = INITIAL_RETRY_DELAY_MS,
        retryBackoffMultiplier: Double = RETRY_BACKOFF_MULTIPLIER,
        operation: suspend () -> Result<T>
    ): Result<T> {
        var currRetryCount = 0
        var currDelay = initialRetryDelay
        var lastResult: Result<T> = Result.failure(Exception("Request Failure"))

        while (currRetryCount < numRetries) {
            lastResult = try {
                operation()
            } catch (e: Exception) {
                Result.failure(e)
            }

            if (lastResult.isSuccess) {
                return lastResult
            }

            currRetryCount++
            delay(currDelay)
            currDelay = (currDelay * retryBackoffMultiplier).toLong()
        }
        return lastResult
    }
}
