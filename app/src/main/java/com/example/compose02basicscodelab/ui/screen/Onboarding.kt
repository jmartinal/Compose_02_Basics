package com.example.compose02basicscodelab.ui.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.compose02basicscodelab.ui.theme.Compose02BasicsCodelabTheme

@Composable
fun OnBoardingScreen(onContinueClicked: () -> Unit) {

    Surface {
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.fillMaxSize()
        ) {
            Text(
                text = "Welcome to the Jetpack Compose Basics Codelab",
                textAlign = TextAlign.Center,
                modifier = Modifier.padding(horizontal = 16.dp)
            )
            Button(
                modifier = Modifier.padding(vertical = 24.dp),
                onClick = { onContinueClicked() }
            ) {
                Text("Continue")
            }
        }
    }
}

@Preview(showBackground = true, widthDp = 320, heightDp = 320)
@Composable
fun OnBoardingScreenPreview() {
    Compose02BasicsCodelabTheme {
        OnBoardingScreen(onContinueClicked = { })
    }
}