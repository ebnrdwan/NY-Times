package com.ebnrdwan.core.data.error.manager

import com.ebnrdwan.core.data.error.Error

interface ErrorFactory {
    fun getError(error: Throwable): Error
}