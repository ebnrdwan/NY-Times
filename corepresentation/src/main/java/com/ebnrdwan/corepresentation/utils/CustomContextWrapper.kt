package com.ebnrdwan.corepresentation.utils

import android.content.Context
import android.content.ContextWrapper
import android.os.Build
import android.os.LocaleList
import java.util.*

class CustomContextWrapper(base: Context) : ContextWrapper(base) {
    companion object {
        fun wrap(context: Context, language: String): ContextWrapper {
            var context = context
            val res = context.resources
            val configuration = res.configuration
            val newLocale = Locale(language)

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                configuration.setLocale(newLocale)
                val localeList = LocaleList(newLocale)
                LocaleList.setDefault(localeList)
                configuration.setLocales(localeList)
                context = context.createConfigurationContext(configuration)
            } else {
                configuration.setLocale(newLocale)
                context = context.createConfigurationContext(configuration)
            }

            return CustomContextWrapper(context)
        }
    }
}