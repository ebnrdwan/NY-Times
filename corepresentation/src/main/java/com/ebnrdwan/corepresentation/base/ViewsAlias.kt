package com.ebnrdwan.corepresentation.base

import android.app.Activity
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main.handle__nodata_nointernet_layout.*
import kotlinx.android.synthetic.main.loading_layout.*
import kotlinx.android.synthetic.main.toolbar.*

inline val Fragment.export_loading get() = loading_view
inline val Fragment.exportErrorView get() = error_view
inline val Fragment.exportTvError get() = tv_error
inline val Fragment.exportToolbar get() = toolbar


inline val Activity.export_loading get() = loading_view
inline val Activity.exportErrorView get() = error_view
inline val Activity.exportTvError get() = tv_error
inline val Activity.exportToolbar get() = toolbar
