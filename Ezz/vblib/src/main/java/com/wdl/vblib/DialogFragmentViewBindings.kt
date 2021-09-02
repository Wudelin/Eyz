@file:Suppress("RedundantVisibilityModifier", "unused")
@file:JvmName("DialogFragmentViewBindings")

package com.wdl.vblib

import android.view.View
import androidx.annotation.IdRes
import androidx.fragment.app.DialogFragment
import androidx.viewbinding.ViewBinding

@JvmName("viewBindingDialogFragment")
public fun <F : DialogFragment, V : ViewBinding> DialogFragment.dialogViewBinding(viewBinder: (F) -> V):
        ViewBindingProperty<F, V> = viewBinding(viewBinder)

@JvmName("viewBindingDialogFragment")
public inline fun <F : DialogFragment, V : ViewBinding> DialogFragment.dialogViewBinding(
    crossinline vbFac: (View) -> V,
    crossinline viewProvider: (F) -> View
): ViewBindingProperty<F, V> = viewBinding(vbFac, viewProvider)

@JvmName("viewBindingDialogFragment")
public inline fun <V : ViewBinding> DialogFragment.dialogViewBinding(
    crossinline vbFac: (View) -> V,
    @IdRes contentViewRootId: Int
): ViewBindingProperty<DialogFragment, V> = viewBinding(vbFac, contentViewRootId)