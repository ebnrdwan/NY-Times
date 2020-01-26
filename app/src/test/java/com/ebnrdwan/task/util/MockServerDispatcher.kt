package com.ebnrdwan.task.util

import okhttp3.mockwebserver.Dispatcher
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.RecordedRequest

class MockServerDispatcher {

    //  response dispatcher
    class ResponseDispatcher : Dispatcher() {
        override fun dispatch(request: RecordedRequest): MockResponse {
            if (request.path.equals("all-sections/7.json", true))
                return MockResponse().setResponseCode(200)
            return MockResponse().setResponseCode(400)
        }
    }

    //  error dispatcher
    class ErrorDispatcher : Dispatcher() {
        override fun dispatch(request: RecordedRequest): MockResponse =
            MockResponse().setResponseCode(400)
    }
}