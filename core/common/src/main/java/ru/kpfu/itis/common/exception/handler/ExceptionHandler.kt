package ru.kpfu.itis.common.exception.handler

inline fun <T, R> T.runCatching(
    exceptionHandlerDelegate: ExceptionHandlerDelegate,
    block: T.() -> R,
): Result<R> {
    return try {
        Result.success(block())
    } catch (ex: Throwable) {
        Result.failure(exceptionHandlerDelegate.handleException(ex))
    }
}