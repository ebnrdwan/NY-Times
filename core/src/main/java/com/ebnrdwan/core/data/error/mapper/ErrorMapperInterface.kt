package com.ebnrdwan.core.data.error.mapper

interface ErrorMapperInterface {

  fun mapErrorToMessage(throwable: Throwable):Int
}