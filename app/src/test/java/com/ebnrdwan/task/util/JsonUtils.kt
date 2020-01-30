package com.ebnrdwan.task.util

import com.ebnrdwan.task.domain.ArticlesUseCaseTest
import java.io.IOException
import java.nio.charset.Charset

fun loadJsonFromResources(fileName: String): String? {
    var json: String? = null
    try {
        val stream =
            ArticlesUseCaseTest::class.java.getResourceAsStream("/api_responses/$fileName")

        val size = stream.available()

        val buffer = ByteArray(size)

        stream.read(buffer)

        stream.close()

        json = String(buffer, Charset.defaultCharset())


    } catch (ex: IOException) {
        ex.printStackTrace()
        return null
    }

    return json

}