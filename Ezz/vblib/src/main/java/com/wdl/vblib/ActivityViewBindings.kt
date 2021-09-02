@file:JvmName("ActivityViewBindings")
@file:Suppress("RedundantVisibilityModifier", "unused")

package com.wdl.vblib

import android.view.View
import androidx.activity.ComponentActivity
import androidx.annotation.IdRes
import androidx.annotation.RestrictTo
import androidx.lifecycle.LifecycleOwner
import androidx.viewbinding.ViewBinding

@RestrictTo(RestrictTo.Scope.LIBRARY)
private class ActivityViewBindingProperty<in AC : ComponentActivity, out V : ViewBinding>(
    viewBinder: (AC) -> V
) : LifecycleViewBindingProperty<AC, V>(viewBinder) {
    override fun getLifecycleOwner(thisRef: AC): LifecycleOwner = thisRef
}

@JvmName("viewBindingActivity")
public fun <AC : ComponentActivity, V : ViewBinding>
        ComponentActivity.viewBinding(viewBinder: (AC) -> V): ViewBindingProperty<AC, V> {
    return ActivityViewBindingProperty(viewBinder)
}

@JvmName("viewBindingActivity")
public inline fun <AC : ComponentActivity, V : ViewBinding>
        ComponentActivity.viewBinding(
    crossinline vbFac: (View) -> V,
    crossinline vbProvider: (AC) -> View = ::findRootView
): ViewBindingProperty<AC, V> =
    viewBinding { activity ->
        vbFac(vbProvider(activity))
    }

@JvmName("viewBindingActivity")
public inline fun <AC : ComponentActivity,V:ViewBinding> ComponentActivity.viewBinding(
    crossinline vbFac: (View) -> V,
    @IdRes viewBindingRootId:Int
):ViewBindingProperty<AC,V> = viewBinding { activity ->
    vbFac(activity.requireViewByIdCompat(viewBindingRootId))
}