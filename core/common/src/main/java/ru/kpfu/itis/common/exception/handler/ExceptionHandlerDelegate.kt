package ru.kpfu.itis.common.exception.handler

import android.content.Context
import dagger.hilt.android.qualifiers.ApplicationContext
import retrofit2.HttpException
import ru.kpfu.itis.common.R
import ru.kpfu.itis.common.exception.BadRequestException
import ru.kpfu.itis.common.exception.NotFoundException
import ru.kpfu.itis.common.exception.ServerErrorException
import ru.kpfu.itis.common.exception.TooManyRequestsException
import ru.kpfu.itis.common.exception.UnauthorizedException
import javax.inject.Inject

class ExceptionHandlerDelegate @Inject constructor(
    @ApplicationContext private val context: Context,
) {

    fun handleException(ex: Throwable): Throwable {
        return when (ex) {
            is HttpException -> {
                when (ex.code()) {
                    400 -> BadRequestException(context.getString(R.string.bad_request_error))
                    401 -> UnauthorizedException(context.getString(R.string.unauthorized_error))
                    404 -> NotFoundException(context.getString(R.string.not_found_error))
                    429 -> TooManyRequestsException(context.getString(R.string.too_many_requests_error))
                    in 500..599 -> ServerErrorException(context.getString(R.string.server_error))
                    else -> ex
                }
            }
            else -> ex
        }
    }
}