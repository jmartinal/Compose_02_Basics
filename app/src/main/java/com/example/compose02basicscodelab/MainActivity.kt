package com.example.compose02basicscodelab

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.spring
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.compose02basicscodelab.ui.theme.Compose02BasicsCodelabTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Compose02BasicsCodelabTheme {
                MyApp()
            }
        }
    }
}

@Composable
private fun MyApp(names: List<String> = List(1000) { "$it" }) {
    var shouldShowOnboarding by rememberSaveable { mutableStateOf(true) }

    Surface(color = MaterialTheme.colors.background) {
        if (shouldShowOnboarding) {
            OnBoardingScreen(onContinueClicked = { shouldShowOnboarding = false })
        } else {
            Greetings(names)
        }
    }
}

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

@Composable
fun Greetings(names: List<String>) {
    LazyColumn(
        modifier = Modifier.padding(vertical = 4.dp)
    ) {
        items(names) { name ->
            Greeting(name = name)
        }
    }
}

@Composable
fun Greeting(name: String) {
    var isExpanded by rememberSaveable { mutableStateOf(false) }
    val extraPadding by animateDpAsState(
        targetValue = if (isExpanded) 48.dp else 0.dp,
        animationSpec = spring(
            dampingRatio = Spring.DampingRatioMediumBouncy,
            stiffness = Spring.StiffnessLow
        )
    )

    Surface(
        color = MaterialTheme.colors.primary,
        modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp)
    ) {
        Row(modifier = Modifier.padding(24.dp)) {
            Column(
                modifier = Modifier
                    .weight(1F)
                    .padding(bottom = extraPadding.coerceAtLeast(0.dp))
            ) {
                Text(text = "Hello,")
                Text(text = "$name!")
            }
            OutlinedButton(
                onClick = { isExpanded = !isExpanded }
            ) {
                Text(text = if (isExpanded) "Show less" else "Show more")
            }
        }
    }
}

@Preview(showBackground = true, widthDp = 320)
@Composable
fun DefaultPreview() {
    Compose02BasicsCodelabTheme {
        MyApp()
    }
}