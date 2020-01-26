package com.ebnrdwan.task.data.error.manager

import com.ebnrdwan.task.data.error.Error
import com.ebnrdwan.task.data.error.mapper.ErrorMapper
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

class ErrorManagerTest {

    lateinit var mapper:ErrorMapper
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