@file:RestrictTo(RestrictTo.Scope.LIBRARY_GROUP)
@file:Suppress("NOTHING_TO_INLINE")

package com.wdl.vblib

import android.app.Activity
import android.view.View
import android.view.ViewGroup
import androidx.annotation.IdRes
import androidx.annotation.RestrictTo
import androidx.core.app.ActivityCompat
import androidx.core.view.ViewCompat
import androidx.fragment.app.DialogFragment


/**
 * 获取contentView
 */
@RestrictTo(RestrictTo.Scope.LIBRARY_GROUP)
fun findRootView(activity: Activity): View {
    val contentView = activity.findViewById<ViewGroup>(android.R.id.content)
    checkNotNull(contentView) {
        "Activity has no content view"
    }
    return when (contentView.childCount) {
        0 -> error("ContentView has no child")
        1 -> contentView.getChildAt(0)
        else -> error("ContentView more than one child")
    }
}

@RestrictTo(RestrictTo.Scope.LIBRARY_GROUP)
fun DialogFragment.getRootView(viewBindingRootId: Int): View {
    val dialog = checkNotNull(dialog) {
        "DialogFragment doesn't have dialog. Use viewBinding delegate after onCreateDialog"
    }

    val window = checkNotNull(dialog.window) {
        "Fragment Dialog has no window"
    }

    return with(window.decorView) {
        if (viewBindingRootId != 0)
            requireViewByIdCompat(viewBindingRootId)
        else this
    }
}

@RestrictTo(RestrictTo.Scope.LIBRARY)
inline fun <V : View> Activity.requireViewByIdCompat(@IdRes resId: Int): V =
    ActivityCompat.requireViewById(this, resId)

@RestrictTo(RestrictTo.Scope.LIBRARY)
inline fun <V : View> View.requireViewByIdCompat(@IdRes resId: Int): V =
    ViewCompat.requireViewById(this, resId)

