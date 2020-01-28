package com.ebnrdwan.core.data.error.mapper

import com.ebnrdwan.core.R
import retrofit2.HttpException

import java.io.IOException
import java.net.SocketException
import javax.inject.Inject

class ErrorMapper @Inject constructor() :
    ErrorMapperInterface {

    override fun mapErrorToMessage(throwable: Throwable): Int {
        return when (val error = throwable) {
            is HttpException -> when (error.code()) {
                404 -> R.string.not_found
                400 -> R.string.bad_request
                401 -> R.string.unauthotized
                500 -> R.string.server_error
                else -> R.string.error
            }
            is SocketException,
            is IOException -> R.string.no_internet
            else -> R.string.error
        }
    }
}



