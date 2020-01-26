package com.ebnrdwan.task.data.error

import com.ebnrdwan.task.R

class Error(
    val message: Int = R.string.error,
    serverErrorMsg: String = ""
)  // compose errors in one object to be expandable and reusable