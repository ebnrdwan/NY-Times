package com.ebnrdwan.corepresentation.utils

import java.net.SocketException

class NoInternetException(private val msg:String) : SocketException() {
    override val message: String?
        get() = msg
}