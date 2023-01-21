package com.orghaniian.pokedex.ui.pokedex

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.capitalize
import androidx.compose.ui.text.intl.Locale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import coil.compose.AsyncImage
import com.orghaniian.data.model.Type
import com.orghaniian.pokedex.R
import com.orghaniian.pokedex.ui.components.TypeChip
import com.orghaniian.pokedex.ui.theme.PokedexColor
import com.orghaniian.pokedex.ui.utils.formatOrder
import com.orghaniian.pokedex.ui.utils.value

@Composable
fun PokemonCard(
    pokemon: PokedexItemUiState,
    modifier: Modifier = Modifier,
) {
    Surface (
        color = pokemon.color.value,
        contentColor = PokedexColor.onType,
        shape = RoundedCornerShape(dimensionResource(R.dimen.corner_radius)),
        modifier = modifier,
    ) {
        val leftColumnMargin = dimensionResource(R.dimen.pokemon_list_item_left_column_margin)
        val itemNameMarginBottom = dimensionResource(R.dimen.pokemon_list_item_name_margin_bottom)
        val padding = dimensionResource(R.dimen.pokemon_list_item_padding)

        Box {
            Image(
                painter = painterResource(R.drawable.pokeball),
                contentDescription = null,
                colorFilter = ColorFilter.tint(Color(0x4CFFFFFF)),
                modifier = Modifier
                    .align(Alignment.BottomEnd)
                    .offset(x = 9.dp, y = 13.dp)
            )

            ConstraintLayout(
                modifier = Modifier
                    .fillMaxSize()
            ) {
                val (name, order, types, sprite) = createRefs()

                Text(
                    text = pokemon.name.capitalize(Locale.current),
                    style = MaterialTheme.typography.labelLarge,
                    modifier = Modifier
                        .constrainAs(name) {
                            top.linkTo(parent.top, margin = leftColumnMargin + padding)
                            start.linkTo(parent.start, margin = leftColumnMargin + padding)
                        }
                )
                Text(
                    text = formatOrder(pokemon.order),
                    style = MaterialTheme.typography.labelSmall,
                    color = PokedexColor.onTypeVariant,
                    modifier = Modifier
                        .constrainAs(order) {
                            top.linkTo(parent.top, margin = padding)
                            end.linkTo(parent.end, margin = padding)
                        }
                )

                Column(
                    verticalArrangement = Arrangement.spacedBy(
                        dimensionResource(R.dimen.pokemon_list_item_types_spacing)
                    ),
                    modifier = Modifier.constrainAs(types) {
                        start.linkTo(name.start, leftColumnMargin)
                        top.linkTo(name.bottom, itemNameMarginBottom)
                    }
                ) {
                    pokemon.types.forEach {
                        TypeChip(it)
                    }
                }

                AsyncImage(
                    model = pokemon.spriteUrl,
                    contentDescription = stringResource(
                        R.string.pokemon_image_content_description,
                        pokemon.name
                    ),
                    modifier = Modifier
                        .constrainAs(sprite) {
                            start.linkTo(types.end, padding)
                            end.linkTo(parent.end, padding)
                            top.linkTo(order.bottom, padding)
                            bottom.linkTo(parent.bottom, padding)
                            height = Dimension.fillToConstraints
                        }
                )
            }
        }
    }
}


@Composable
fun SkeletonPokemonCard(
    modifier: Modifier = Modifier
) {
    Surface (
        color = MaterialTheme.colorScheme.onSurfaceVariant,
        contentColor = PokedexColor.onType,
        shape = RoundedCornerShape(dimensionResource(R.dimen.corner_radius)),
        modifier = modifier
    ) {
        CircularProgressIndicator()
    }
}

@Preview(heightDp = 104, widthDp = 160)
@Composable
private fun PokemonCardPreview() {
    MaterialTheme {
        PokemonCard(
            PokedexItemUiState(
                "Bulbizarre",
                1,
                listOf(Type.GRASS, Type.POISON),
                "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/official-artwork/1.png",
                com.orghaniian.data.model.Color.GREEN,
            )
        )
    }
}