package com.jbc7ag.cryptso.util

import androidx.core.graphics.ColorUtils

fun isDark(color: Int): Boolean = ColorUtils.calculateLuminance(color) < 0.1
fun isLight(color: Int): Boolean = ColorUtils.calculateLuminance(color) > 0.8
