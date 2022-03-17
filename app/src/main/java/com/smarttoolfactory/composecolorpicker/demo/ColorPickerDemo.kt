package com.smarttoolfactory.composecolorpicker.demo

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.smarttoolfactory.colorpicker.HslPicker
import com.smarttoolfactory.colorpicker.SaturationRhombus
import com.smarttoolfactory.colorpicker.slider.ColorBrush
import com.smarttoolfactory.colorpicker.slider.ColorfulSlider
import com.smarttoolfactory.colorpicker.slider.MaterialSliderDefaults
import com.smarttoolfactory.colorpicker.ui.Blue400
import com.smarttoolfactory.colorpicker.ui.gradientColorScaleHSL
import com.smarttoolfactory.colorpicker.ui.sliderSaturationHSLGradient

@Composable
fun ColorPickerDemo() {
    Column(
        modifier = Modifier
            .background(Color(0xff424242))
            .fillMaxSize()
            .padding(8.dp)
    ) {

        var hue by remember { mutableStateOf(0f) }
        var saturation by remember { mutableStateOf(1f) }
        var lightness by remember { mutableStateOf(0.5f) }
        var value by remember { mutableStateOf(1f) }

//        val color = Color.hsl(hue = hue, saturation = saturation, lightness = lightness)
        val colorHSL = Color.hsl(hue = hue, saturation = saturation, lightness = lightness)
        val colorHSV = Color.hsv(hue = hue, saturation = saturation, value = value)

        Column(horizontalAlignment = Alignment.CenterHorizontally) {

            Spacer(modifier = Modifier.height(10.dp))
            Text(
                text = "Color",
                color = Blue400,
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(top = 12.dp)
            )

            // Initial and Current Colors
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 50.dp, vertical = 20.dp)
            ) {

                Box(
                    modifier = Modifier
                        .weight(1f)
                        .height(40.dp)
                        .background(
                            colorHSL,
                            shape = RoundedCornerShape(topStart = 8.dp, bottomStart = 8.dp)
                        )
                )
                Box(
                    modifier = Modifier
                        .weight(1f)
                        .height(40.dp)
                        .background(
                            colorHSV,
                            shape = RoundedCornerShape(topEnd = 8.dp, bottomEnd = 8.dp)
                        )
                )
            }

            // ColorWheel for hue selection
            // SaturationRhombus for saturation and lightness selections
            Box(
                modifier = Modifier.padding(8.dp),
                contentAlignment = Alignment.Center
            ) {

//                ColorPickerWheel(
//                    modifier = Modifier
//                        .fillMaxWidth()
//                        .aspectRatio(1f),
//                    selectionRadius = 8.dp
//                ) { hueChange ->
//                    hue = hueChange.toFloat()
//                }

                Column {
                    SaturationRhombus(
                        modifier = Modifier.size(200.dp),
                        hue = hue,
                        saturation = saturation,
                        lightness = lightness,
                        selectionRadius = 8.dp
                    ) { s, l ->
                        println("CHANGING sat: $s, lightness: $l")
                        saturation = s
                        lightness = l
                    }


                    HslPicker(
                        modifier = Modifier.size(200.dp),
                        hue = hue,
                        saturation = saturation,
                        value = value,
                        selectionRadius = 8.dp
                    ) { s, v ->
                        println("CHANGING sat: $s, lightness: $v")
                        saturation = s
                        value = v
                    }
                }
            }

            // Sliders
//            ColorSlider(
//                modifier = Modifier
//                    .padding(start = 12.dp, end = 12.dp)
//                    .fillMaxWidth(),
//                title = "Hue",
//                titleColor = Color.Red,
//                rgb = hue,
//                onColorChanged = {
//                    hue = it
//                },
//                valueRange = 0f..360f
//            )

            val hueGradientHSL: List<Color> = gradientColorScaleHSL(
                saturation = saturation,
                lightness = lightness,
            )

            val saturationHSL = sliderSaturationHSLGradient(hue, lightness)

            ColorfulSlider(
                value = hue,
                modifier = Modifier.width(300.dp),
                thumbRadius = 14.dp,
                trackHeight = 14.dp,
                onValueChange = { value, _, _ ->
                    hue = value
                },
                valueRange = 0f..360f,
                coerceThumbInTrack = true,
                colors = MaterialSliderDefaults.materialColors(
                    activeTrackColor = ColorBrush(brush = Brush.linearGradient(hueGradientHSL)),
                ),
                drawInactiveTrack = false
            )

            Spacer(modifier = Modifier.height(4.dp))
            ColorfulSlider(
                value = saturation,
                modifier = Modifier.width(300.dp),
                thumbRadius = 14.dp,
                trackHeight = 14.dp,
                onValueChange = { value, _, _ ->
                    saturation = value
                },
                coerceThumbInTrack = true,
                colors = MaterialSliderDefaults.materialColors(
                    activeTrackColor = ColorBrush(brush = saturationHSL),
                ),
                drawInactiveTrack = false
            )
            Spacer(modifier = Modifier.height(4.dp))

            ColorSlider(
                modifier = Modifier
                    .padding(start = 12.dp, end = 12.dp)
                    .fillMaxWidth(),
                title = "Lightness",
                titleColor = Color.Blue,
                rgb = lightness * 100f,
                onColorChanged = {
                    lightness = it / 100f
                },
                valueRange = 0f..100f
            )

            Spacer(modifier = Modifier.height(4.dp))

            ColorSlider(
                modifier = Modifier
                    .padding(start = 12.dp, end = 12.dp)
                    .fillMaxWidth(),
                title = "Value",
                titleColor = Color.Blue,
                rgb = value * 100f,
                onColorChanged = {
                    value = it / 100f
                },
                valueRange = 0f..100f
            )
        }
    }
}
