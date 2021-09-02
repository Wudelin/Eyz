@file:Suppress("UNCHECKED_CAST", "unused", "RedundantVisibilityModifier")

package com.wdl.vblib

import android.os.Handler
import android.os.Looper
import android.util.Log
import androidx.annotation.CallSuper
import androidx.annotation.MainThread
import androidx.annotation.RestrictTo
import androidx.lifecycle.DefaultLifecycleObserver
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleOwner
import androidx.viewbinding.ViewBinding
import kotlin.properties.ReadOnlyProperty
import kotlin.reflect.KProperty

private const val TAG = "ViewBindingProperty"

interface ViewBindingProperty<in T : Any, out V : ViewBinding> : ReadOnlyProperty<T, V> {
    @MainThread
    fun clear()
}

@RestrictTo(RestrictTo.Scope.LIBRARY_GROUP)
public open class EagerViewBindingProperty<in T : Any, out V : ViewBinding>(private val viewBinding:V):
    ViewBindingProperty<T,V>{

    @MainThread
    override fun getValue(thisRef: T, property: KProperty<*>): V {
        return viewBinding
    }

    @MainThread
    override fun clear() {
    }

}

@RestrictTo(RestrictTo.Scope.LIBRARY_GROUP)
public open class LazyViewBindingProperty<in T : Any, out V : ViewBinding>(protected val viewBinder: (T) -> V) :
    ViewBindingProperty<T, V> {

    protected var viewBinding: Any? = null

    override fun getValue(thisRef: T, property: KProperty<*>): V {
        return viewBinding as? V ?: viewBinder(thisRef).also { viewBinding ->
            this.viewBinding = viewBinding
        }
    }

    @MainThread
    @CallSuper
    override fun clear() {
        this.viewBinding = null
    }

}

@RestrictTo(RestrictTo.Scope.LIBRARY_GROUP)
public abstract class LifecycleViewBindingProperty<in T : Any, out V : ViewBinding>(private val viewBinder: (T) -> V) :
    ViewBindingProperty<T, V> {
    private var viewBinding: V? = null

    protected abstract fun getLifecycleOwner(thisRef: T): LifecycleOwner

    override fun getValue(thisRef: T, property: KProperty<*>): V {
        viewBinding?.let {
            return it
        }

        val lifecycle = getLifecycleOwner(thisRef).lifecycle
        val viewBinding = viewBinder(thisRef)
        if (lifecycle.currentState == Lifecycle.State.DESTROYED) {
            Log.w(TAG, "lifecycle.currentState is DESTROYED")
        } else {
            lifecycle.addObserver(ClearOnDestroyLifecycleObserver(this))
            this.viewBinding = viewBinding
        }
        return viewBinding
    }

    internal fun postClear() {
        if (!mainHandler.post { clear() }) {
            clear()
        }
    }

    @MainThread
    @CallSuper
    override fun clear() {
        viewBinding = null
    }

    /**
     * fragment 防止内存泄漏
     */
    private class ClearOnDestroyLifecycleObserver(private val property: LifecycleViewBindingProperty<*, *>) :
        DefaultLifecycleObserver {

        override fun onDestroy(owner: LifecycleOwner) {
            super.onDestroy(owner)
            property.postClear()
        }
    }

    private companion object {
        val mainHandler = Handler(Looper.getMainLooper())
    }

}