package com.ebnrdwan.task.data.error.mapper

interface ErrorMapperInterface {

  fun mapErrorToMessage(throwable: Throwable):Int
}