package com.smarttoolfactory.colorpicker.widget

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.sizeIn
import androidx.compose.material.icons.filled.Brush
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.unit.dp
import com.smarttoolfactory.colorpicker.ui.gradientColorScaleRGB


/**
 * Simple circle with stroke to show rainbow colors as [Brush.sweepGradient]
 */
@Composable
fun ColorWheel(modifier: Modifier = Modifier) {

    Canvas(modifier = modifier.sizeIn(minWidth = 24.dp, minHeight = 24.dp)) {
        val canvasWidth = size.width
        val canvasHeight = size.height

        require(canvasWidth == canvasHeight,
            lazyMessage = {
                print("Canvas dimensions should be equal to each other")
            }
        )
        val cX = canvasWidth / 2
        val cY = canvasHeight / 2
        val canvasRadius = canvasWidth.coerceAtMost(canvasHeight) / 2f
        val center = Offset(cX, cY)
        val strokeWidth = canvasRadius * .3f
        // Stroke is drawn out of the radius, so it's required to subtract stroke width from radius
        val radius = canvasRadius - strokeWidth

        drawCircle(
            brush = Brush.sweepGradient(colors = gradientColorScaleRGB, center = center),
            radius = radius,
            center = center,
            style = Stroke(
                width = strokeWidth
            )
        )
    }
}
