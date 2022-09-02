package com.example.compose02basicscodelab.ui.screen

import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.spring
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedButton
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

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

@Preview
@Composable
fun GreetingPreview() {
    Greeting(name = "Jorge")
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

@Preview
@Composable
fun GreetingsPreview() {
    Greetings(names = listOf("John", "Mary", "Anna"))
}