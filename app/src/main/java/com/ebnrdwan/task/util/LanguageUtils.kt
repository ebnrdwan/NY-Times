package com.ebnrdwan.task.util

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.widget.Toast
import java.util.*


fun changeAppLanguage(
    context: Activity,
    language: String
) {
    Constants.Language.CURRENT = language
    context.startActivity(Intent(context, context::class.java).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP))
}

fun toggleLanguage(): String {
    return when (Locale.getDefault().language) {
        Constants.Language.ENGLISH -> Constants.Language.ARABIC
        else -> Constants.Language.ENGLISH
    }
}

fun Context.showToast(msg: String) =
    Toast.makeText(this, msg, Toast.LENGTH_SHORT).show()



