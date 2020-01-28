package com.ebnrdwan.core.data.error.manager

import com.ebnrdwan.core.data.error.Error
import com.ebnrdwan.core.data.error.mapper.ErrorMapper
import javax.inject.Inject


class ErrorManager @Inject constructor(private val errorMapper: ErrorMapper) :
    ErrorFactory {
    override fun getError(error: Throwable): Error {
        return Error(message = errorMapper.mapErrorToMessage(error))
    }

}