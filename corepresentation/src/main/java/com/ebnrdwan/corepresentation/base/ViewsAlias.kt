package com.ebnrdwan.corepresentation.base

import android.app.Activity
import android.widget.TextView
import androidx.appcompat.widget.Toolbar
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.fragment.app.Fragment
import com.airbnb.lottie.LottieAnimationView
import kotlinx.android.synthetic.main.handle__nodata_nointernet_layout.*
import kotlinx.android.synthetic.main.loading_layout.*
import kotlinx.android.synthetic.main.toolbar.*

inline val Fragment.export_loading: LottieAnimationView get() = loading_view
inline val Fragment.exportErrorView: ConstraintLayout get() = error_view
inline val Fragment.exportTvError: TextView get() = tv_error
inline val Fragment.exportToolbar: Toolbar get() = toolbar


inline val Activity.export_loading: LottieAnimationView get() = loading_view
inline val Activity.exportErrorView: ConstraintLayout get() = error_view
inline val Activity.exportTvError: TextView get() = tv_error
inline val Activity.exportToolbar: Toolbar get() = toolbar
