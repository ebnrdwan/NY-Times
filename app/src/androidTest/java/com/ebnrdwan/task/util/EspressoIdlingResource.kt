package com.ebnrdwan.task.util

import androidx.test.espresso.IdlingResource

class EspressoIdlingResource {
    companion object INSTANCE {
        private const val RESOURCE = "GLOBAL"

        private val mCountingIdlingResource = SimpleCountingIdlingResource(RESOURCE)

        val idlingResource: IdlingResource
            get() = mCountingIdlingResource

        fun increment() {
            mCountingIdlingResource.increment()
        }

        fun decrement() {
            mCountingIdlingResource.decrement()
        }
    }
}