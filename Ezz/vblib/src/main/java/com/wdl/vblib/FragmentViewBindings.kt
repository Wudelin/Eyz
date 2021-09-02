@file:JvmName("FragmentViewBindings")
@file:Suppress("RedundantVisibilityModifier", "unused", "UNCHECKED_CAST")

package com.wdl.vblib

import android.view.View
import androidx.annotation.IdRes
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.LifecycleOwner
import androidx.viewbinding.ViewBinding
import java.lang.Exception
import kotlin.reflect.KProperty

@JvmName("viewBindingFragment")
public fun <F : Fragment, V : ViewBinding> Fragment.viewBinding(viewBinder: (F) -> V): ViewBindingProperty<F, V> {
    return when (this) {
        is DialogFragment -> DialogFragmentViewBindingProperty(viewBinder) as ViewBindingProperty<F, V>
        else -> FragmentViewBindingProperty(viewBinder)
    }
}

@JvmName("viewBindingFragment")
public inline fun <F : Fragment, V : ViewBinding>
        Fragment.viewBinding(
    crossinline vbFac: (View) -> V,
    crossinline viewProvider: (F) -> View = Fragment::requireView
): ViewBindingProperty<F, V> =
    viewBinding { fragment: F -> vbFac(viewProvider(fragment)) }

@JvmName("viewBindingFragment")
public inline fun <F : Fragment, V : ViewBinding>
        Fragment.viewBinding(
    crossinline vbFac: (View) -> V,
    @IdRes viewBindingRootId: Int
): ViewBindingProperty<F, V> {
    return when (this) {
        is DialogFragment -> {
            viewBinding<DialogFragment, V>(vbFac) { frag ->
                frag.getRootView(viewBindingRootId)
            } as ViewBindingProperty<F, V>
        }
        else -> {
            viewBinding(vbFac) { fragment: F ->
                fragment.requireView().requireViewByIdCompat(viewBindingRootId)
            }
        }
    }
}


private class DialogFragmentViewBindingProperty<in F : DialogFragment, out V : ViewBinding>(
    viewBinder: (F) -> V
) : LifecycleViewBindingProperty<F, V>(viewBinder) {
    override fun getLifecycleOwner(thisRef: F): LifecycleOwner {
        return if (thisRef.showsDialog) {
            thisRef
        } else {
            try {
                thisRef.viewLifecycleOwner
            } catch (e: Exception) {
                error("Fragment doesn't have view or view has been destroy")
            }
        }
    }
}

private class FragmentViewBindingProperty<in F : Fragment, out V : ViewBinding>(viewBinder: (F) -> V) :
    LifecycleViewBindingProperty<F, V>(viewBinder) {

    private var fragmentLifecycleCallback: FragmentManager.FragmentLifecycleCallbacks? = null

    override fun getLifecycleOwner(thisRef: F): LifecycleOwner {
        try {
            return thisRef.viewLifecycleOwner
        } catch (e: Exception) {
            error("Fragment doesn't have view or view has been destroy")
        }
    }

    override fun getValue(thisRef: F, property: KProperty<*>): V {
        registerFragmentLifecycleCallback(thisRef)
        return super.getValue(thisRef, property)
    }

    override fun clear() {
        super.clear()
        fragmentLifecycleCallback = null
    }

    private fun registerFragmentLifecycleCallback(fragment: Fragment) {
        if (fragmentLifecycleCallback != null) {
            return
        }

        fragmentLifecycleCallback = ClearFragmentLifecycleCallback().also {
            fragment.parentFragmentManager.registerFragmentLifecycleCallbacks(it, false)
        }
    }

    private inner class ClearFragmentLifecycleCallback :
        FragmentManager.FragmentLifecycleCallbacks() {
        override fun onFragmentDestroyed(fm: FragmentManager, f: Fragment) {
            postClear()
        }
    }

}