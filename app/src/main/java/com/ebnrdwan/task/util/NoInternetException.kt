package com.ebnrdwan.task.util

import java.net.SocketException

class NoInternetException(private val msg:String) : SocketException() {
    override val message: String?
        get() = msg
}