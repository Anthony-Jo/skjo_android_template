package com.skjo.skjo_android_template.ui.component

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color

object Surface {
    @Composable
    fun WhiteSurface() {
        Surface(
            color = Color.White,
            modifier = Modifier.fillMaxSize(1f),
        ) {

        }
    }
}