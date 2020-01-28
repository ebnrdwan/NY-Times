package com.ebnrdwan.core.data.error.mapper

import org.junit.Assert.assertNotEquals
import org.junit.Before
import org.junit.Test
import java.io.IOException

class ErrorMapperTest {
    lateinit var mapper: ErrorMapper
    @Before
    fun setUp() {
        mapper = ErrorMapper()
    }

    @Test
    fun `when error`() {
        assertNotEquals(
            mapper.mapErrorToMessage(IOException()),
            com.ebnrdwan.core.data.error.Error()
        )
    }
}