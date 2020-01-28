package com.ebnrdwan.core.data.error.mapper

import com.ebnrdwan.core.data.error.Error
import org.junit.Assert.assertNotEquals
import org.junit.Before
import org.junit.Test
import java.io.IOException

class ErrorMapperTest {
    lateinit var mapper: com.ebnrdwan.core.data.error.mapper.ErrorMapper
    @Before
    fun setUp() {
        mapper = com.ebnrdwan.core.data.error.mapper.ErrorMapper()
    }

    @Test
    fun mapErrorToMessage() {
        assertNotEquals( mapper.mapErrorToMessage(IOException()),
            com.ebnrdwan.core.data.error.Error()
        )
    }
}