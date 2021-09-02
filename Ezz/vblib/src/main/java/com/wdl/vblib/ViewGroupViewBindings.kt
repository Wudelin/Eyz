package com.wdl.vblib

import android.view.View
import android.view.ViewGroup
import androidx.annotation.IdRes
import androidx.annotation.RestrictTo
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.ViewTreeLifecycleOwner
import androidx.viewbinding.ViewBinding

@PublishedApi
@RestrictTo(RestrictTo.Scope.LIBRARY)
internal class ViewGroupViewBindingProperty<in VG : ViewGroup, V : ViewBinding>(viewBinder: (VG) -> V) :
    LifecycleViewBindingProperty<VG, V>(viewBinder) {
    override fun getLifecycleOwner(thisRef: VG): LifecycleOwner {
        return checkNotNull(ViewTreeLifecycleOwner.get(thisRef)) {
            "Fragment don't have view or the view has been destroyed"
        }
    }
}

inline fun <V : ViewBinding> ViewGroup.viewBinding(
    lifecycleAware: Boolean,
    crossinline vbFac: (ViewGroup) -> V
): ViewBindingProperty<ViewGroup, V> {
    return when {
        isInEditMode -> EagerViewBindingProperty(vbFac(this))
        lifecycleAware -> ViewGroupViewBindingProperty { vg -> vbFac(vg) }
        else -> LazyViewBindingProperty { vg -> vbFac(vg) }
    }
}

inline fun <V : ViewBinding> ViewGroup.viewBinding(
    crossinline vbFac: (ViewGroup) -> V
): ViewBindingProperty<ViewGroup, V> = viewBinding(lifecycleAware = false, vbFac)

inline fun <V : ViewBinding> ViewGroup.viewBinding(
    @IdRes viewBindingRootId: Int,
    lifecycleAware: Boolean,
    crossinline vbFac: (View) -> V
): ViewBindingProperty<ViewGroup, V> {
    return when {
        isInEditMode -> EagerViewBindingProperty(vbFac(this))
        lifecycleAware -> ViewGroupViewBindingProperty { viewGroup -> vbFac(viewGroup) }
        else -> LazyViewBindingProperty { viewGroup: ViewGroup ->
            vbFac(viewGroup.requireViewByIdCompat(viewBindingRootId))
        }
    }
}

inline fun <V : ViewBinding> ViewGroup.viewBinding(
    crossinline vbFac: (View) -> V,
    @IdRes viewBindingRootId: Int,
): ViewBindingProperty<ViewGroup, V> {
    return viewBinding(viewBindingRootId, vbFac)
}

inline fun <V : ViewBinding> ViewGroup.viewBinding(
    @IdRes viewBindingRootId: Int,
    crossinline vbFac: (View) -> V,
): ViewBindingProperty<ViewGroup, V> {
    return viewBinding(viewBindingRootId, lifecycleAware = false, vbFac)
}
