package com.ebnrdwan.task.data.error.mapper

import com.ebnrdwan.task.data.error.Error
import org.junit.Assert.assertNotEquals
import org.junit.Before
import org.junit.Test
import java.io.IOException

class ErrorMapperTest {
    lateinit var mapper:ErrorMapper
    @Before
    fun setUp() {
        mapper = ErrorMapper()
    }

    @Test
    fun mapErrorToMessage() {
        assertNotEquals( mapper.mapErrorToMessage(IOException()),Error())
    }
}