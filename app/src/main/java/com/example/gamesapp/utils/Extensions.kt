package com.example.gamesapp.utils

import android.text.Html
import android.view.View

fun View.show() {
    visibility = View.VISIBLE
}

fun View.hide() {
    visibility = View.INVISIBLE
}

fun View.gone() {
    visibility = View.GONE
}

fun String.convertFromHtml():String{
    return Html.fromHtml(this).toString()
}