package com.ebnrdwan.core.util

class Constants {
    object Networking {

        const val RETROFIT_TIMEOUT_SECONDS: Long = 60
        const val API_REQUEST_TAG = "API_REQUEST:->"
        const val API_RESPONSE_TAG = "API_RESPONSE:->"
        const val APP_VERSION_TAG = "APP_FLAVOR:->"
    }

    object Logging {
        const val RX_TAG_ERROR = "RX_ERROR"
        const val RX_TAG_SUCCESS = "RX_SUCCESS"
        const val RX_TAG_NEXT = "RX_NEXT"
        const val RX_TAG_COMPLETE = "RX_COMPLETE"
        const val RX_TAG_SUPSCRIBE = "RX_COMPLETE"
    }

}
