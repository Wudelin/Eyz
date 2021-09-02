@file:Suppress("unused")
@file:JvmName("ViewHolderBindings")

package com.wdl.vblib

import android.view.View
import androidx.annotation.IdRes
import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding

fun <VH : RecyclerView.ViewHolder, V : ViewBinding> VH.viewBinding(viewBinder: (VH) -> V): ViewBindingProperty<VH, V> =
    LazyViewBindingProperty(viewBinder)

inline fun <VH : RecyclerView.ViewHolder, V : ViewBinding> VH.viewBinding(
    crossinline vbFac: (View) -> V,
    crossinline viewProvider: (VH) -> View = RecyclerView.ViewHolder::itemView
): ViewBindingProperty<VH, V> = LazyViewBindingProperty { viewHolder: VH ->
    viewProvider(viewHolder).let(vbFac)
}

inline fun <VH : RecyclerView.ViewHolder, V : ViewBinding> VH.viewBinding(
    crossinline vbFac: (View) -> V,
    @IdRes contentViewRootId: Int
): ViewBindingProperty<VH, V> = LazyViewBindingProperty { viewHolder: VH ->
    vbFac(viewHolder.itemView.requireViewByIdCompat(contentViewRootId))
}