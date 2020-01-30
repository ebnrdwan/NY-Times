package com.ebnrdwan.task.util

import okhttp3.mockwebserver.Dispatcher
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.RecordedRequest

private val SUCCESS = 200
private val ERRROR = 400
private val path = "all-sections/7.json"

class MockServerDispatcher {

    //  response dispatcher
    class ResponseDispatcher : Dispatcher() {


        private val mockResponse = MockResponse().setResponseCode(SUCCESS)

        override fun dispatch(request: RecordedRequest): MockResponse {
            if (request.path.equals(path, true))
                return MockResponse().setResponseCode(SUCCESS)
            return MockResponse().setResponseCode(ERRROR)
        }
    }

    //  error dispatcher
    class ErrorDispatcher : Dispatcher() {
        override fun dispatch(request: RecordedRequest): MockResponse =
            MockResponse().setResponseCode(ERRROR)
    }
}