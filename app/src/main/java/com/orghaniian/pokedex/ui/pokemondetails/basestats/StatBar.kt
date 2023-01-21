package com.orghaniian.pokedex.ui.pokemondetails.basestats

import androidx.compose.animation.core.Animatable
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.tooling.preview.Preview
import com.orghaniian.pokedex.ui.theme.PokedexTheme

@Composable
fun StatBar(
    modifier: Modifier = Modifier,
    value: Int,
    max: Int = 100,
    color: Color,
    animate: Boolean = true
) {
    val display = remember { Animatable(0f) }
    LaunchedEffect(Unit) {
        if(animate) display.animateTo(1f)
    }
    LinearProgressIndicator(
        progress = if(animate) (value / max.toFloat()) * display.value else value / max.toFloat(),
        color = color,
        trackColor = MaterialTheme.colorScheme.outline.copy(alpha = .5f),
        strokeCap = StrokeCap.Round,
        modifier = modifier
    )
}

@Preview
@Composable
fun PreviewStatBar() {
    PokedexTheme {
        Surface {
            StatBar(
                value = 45,
                color = Color.Red
            )
        }
    }
}