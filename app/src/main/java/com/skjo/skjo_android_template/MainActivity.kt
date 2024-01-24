package com.skjo.skjo_android_template

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import com.skjo.skjo_android_template.ui.theme.Skjo_android_templateTheme

class MainActivity : ComponentActivity() {
    private var isShow = true
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(1f),
                    color = Color.Red
                ) {
                    Greeting("Android")
                }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Column {
        Surface {
            Button(
                onClick = {  }
            ) {
                Text(text = "Button Example")
            }

        }

        Text(
            text = "Hello $name!",
            modifier = modifier
        )

        Text(
            text = "SKJO",
            modifier = modifier
        )
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    Skjo_android_templateTheme {
        Greeting("Android")
    }
}