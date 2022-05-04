package com.rerere.iwara4a.ui.states

import android.app.Activity
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.toComposeRect
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.dp
import androidx.window.layout.WindowMetricsCalculator

@Composable
fun rememberWindowSizeClass(): WindowSize {
    val activity = LocalContext.current as Activity
    // val configuration = LocalConfiguration.current
    val windowMetrics = //remember(configuration) {
        WindowMetricsCalculator.getOrCreate()
            .computeCurrentWindowMetrics(activity)
    //}
    val windowDpSize = with(LocalDensity.current) {
        windowMetrics.bounds.toComposeRect().size.toDpSize()
    }
    return when {
        windowDpSize.width < 600.dp -> WindowSize.Compact
        windowDpSize.width < 840.dp -> WindowSize.Medium
        else -> WindowSize.Expanded
    }
}

@Composable
fun rememberWindowDpSize(): DpSize {
    val activity = LocalContext.current as Activity
    // val configuration = LocalConfiguration.current
    val windowMetrics = WindowMetricsCalculator.getOrCreate()
        .computeCurrentWindowMetrics(activity)
    return with(LocalDensity.current) {
        windowMetrics.bounds.toComposeRect().size.toDpSize()
    }
}

enum class WindowSize { Compact, Medium, Expanded }