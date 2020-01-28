package com.ebnrdwan.corepresentation.utils

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.widget.Toast
import com.ebnrdwan.corepresentation.utils.Constants.*
import java.util.*


fun changeAppLanguage(
    context: Activity,
    language: String
) {
    Language.CURRENT = language
    context.startActivity(Intent(context, context::class.java).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP))
}

fun toggleLanguage(): String {
    return when (Locale.getDefault().language) {
        Language.ENGLISH ->  Language.ARABIC
        else ->  Language.ENGLISH
    }
}

fun Context.showToast(msg: String) =
    Toast.makeText(this, msg, Toast.LENGTH_SHORT).show()



