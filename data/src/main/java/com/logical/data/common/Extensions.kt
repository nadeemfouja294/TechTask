package com.logical.data.common

import java.text.DecimalFormat

/**
 * This function is an extension function on Long? type.
 * It returns the value if it's not null, otherwise it returns 0.
 */
fun Long?.orZero() = this ?: 0

/**
 * This function is an extension function on Long type.
 * It formats the Long value to a string with commas as thousand separators.
 */
fun Long.format(): String = DecimalFormat("#,###.##").format(this)