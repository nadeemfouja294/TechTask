package com.logical.techtask.utils

import android.content.Context
import android.content.Intent
import android.net.Uri
import java.text.DecimalFormat

/**
 * A collection of utility functions for handling common operations.
 *
 * 1. `orZero`: Extension function for `Long?` that returns the value of the `Long` if it's not null,
 *    otherwise, it returns zero.
 *
 * 2. `format`: Extension function for `Long` that formats the number with thousand separators.
 *
 * 3. `openBrowser`: Function to open a web URL in the device's default web browser.
 */
fun Long?.orZero() = this ?: 0

fun Long.format(): String = DecimalFormat("#,###.##").format(this)

fun Context.openBrowser(url: String) {
    startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(url)))
}