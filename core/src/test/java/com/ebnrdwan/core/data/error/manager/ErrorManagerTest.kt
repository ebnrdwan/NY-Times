package com.ebnrdwan.core.data.error.manager

import com.ebnrdwan.core.data.error.Error
import com.ebnrdwan.core.data.error.mapper.ErrorMapper
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

class ErrorManagerTest {

    lateinit var mapper: ErrorMapper
    lateinit var manager: ErrorManager
    @Before
    fun setUp() {
        mapper = ErrorMapper()
        manager = ErrorManager(mapper)
    }
    @Test
    fun getError() {
     assertEquals(manager.getError(TypeCastException()).message, Error().message)
    }

}