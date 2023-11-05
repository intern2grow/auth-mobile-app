package dev.awd.auth.utils

import android.view.View
import android.widget.Button
import com.google.android.material.textfield.TextInputLayout

val TextInputLayout.text: String
    get() = editText?.text.toString()

fun View.hide() = kotlin.run { visibility = View.GONE }
fun View.show() = kotlin.run { visibility = View.VISIBLE }
fun View.invisible() = kotlin.run { visibility = View.INVISIBLE }

infix fun View.visibleIf(condition: Boolean) =
    run { if (condition) show() else hide() }

infix fun View.invisibleIf(condition: Boolean) =
    run { if (condition) invisible() else show() }

infix fun View.goneIf(condition: Boolean) =
    run { if (condition) hide() else show() }

infix fun Button.clickableIf(condition: Boolean) =
    run { isClickable = condition }