package com.ebnrdwan.core.data.models

import com.ebnrdwan.core.R


sealed class UiState {
    abstract var message: Int

    object LOADING : UiState() {
        override var message: Int = R.string.loading
    }

    object RELOADING : UiState() {
        override var message: Int = R.string.reoading
    }

    object SUCCESS : UiState() {
        override var message: Int = R.string.success

    }

    object ERROR : UiState() {
        override var message: Int = R.string.no_internet

    }

    object NODATA : UiState() {
        override var message: Int = R.string.no_data

    }

    object NOINTERNET : UiState() {
        override var message: Int = R.string.no_internet

    }

}