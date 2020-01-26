package com.ebnrdwan.task.data.error.manager

import com.ebnrdwan.task.data.error.Error

interface ErrorFactory {
    fun getError(error: Throwable): Error
}