package com.ebnrdwan.core.data.error

import com.ebnrdwan.core.R


class Error(
    val message: Int = R.string.error,
    serverErrorMsg: String = ""
)  // compose errors in one object to be expandable and reusable