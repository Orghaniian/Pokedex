package com.orghaniian.pokedex.ui.pokemondetails.basestats

import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.orghaniian.data.local.PokemonStat
import com.orghaniian.data.model.Stat
import com.orghaniian.pokedex.R
import com.orghaniian.pokedex.ui.theme.Dimensions
import com.orghaniian.pokedex.ui.utils.color
import com.orghaniian.pokedex.ui.utils.stringResourceId

@Composable
fun BaseStats(
    stats: List<PokemonStat>
) {
    Row {
        val modifier = Modifier.height(
            with(LocalDensity.current) {
                MaterialTheme.typography.bodyLarge.lineHeight.toDp()
            }
        )
        Column {
            stats.forEach {
                Text(
                    text = stringResource(it.stat.stringResourceId),
                    modifier = modifier,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }
            Text(
                text = stringResource(R.string.total),
                modifier = Modifier.padding(top = Dimensions.marginM).then(modifier),
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }
        Column(
            modifier = Modifier.padding(start = Dimensions.infoSpacing)
        ) {
            stats.forEach {
                Text(
                    text = it.value.toString(),
                    modifier = modifier
                )
            }
            Text(
                text = stats.fold(0) { acc, stat -> acc + stat.value }.toString(),
                modifier = Modifier.padding(top = Dimensions.marginM).then(modifier)
            )
        }
        Column(
            modifier = Modifier.padding(start = Dimensions.marginM),
        ) {
            stats.forEach {
                Box(
                    modifier = modifier,
                ) {
                    StatBar(
                        value = it.value,
                        color = it.stat.color,
                        modifier = Modifier.align(Alignment.Center)
                    )
                }
            }
            Box(
                modifier = Modifier.padding(top = Dimensions.marginM).then(modifier)
            ) {
                StatBar(
                    value = stats.fold(0) { acc, stat -> acc + stat.value },
                    max = stats.size * 100,
                    color = MaterialTheme.colorScheme.onSurface,
                    modifier = Modifier.align(Alignment.Center)
                )
            }
        }
    }
}

@Preview
@Composable
private fun PreviewBaseStats(){
    MaterialTheme {
        BaseStats(
            listOf(
                PokemonStat(Stat.HP, 45),
                PokemonStat(Stat.ATTACK, 70)
            )
        )
    }
}