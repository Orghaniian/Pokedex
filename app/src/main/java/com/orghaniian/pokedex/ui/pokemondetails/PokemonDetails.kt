package com.orghaniian.pokedex.ui.pokemondetails

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.capitalize
import androidx.compose.ui.text.intl.Locale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import com.orghaniian.data.model.Type
import com.orghaniian.pokedex.R
import com.orghaniian.pokedex.ui.components.ReturnButton
import com.orghaniian.pokedex.ui.components.TypeChip
import com.orghaniian.pokedex.ui.pokemondetails.about.About
import com.orghaniian.pokedex.ui.pokemondetails.basestats.BaseStats
import com.orghaniian.pokedex.ui.pokemondetails.evolution.Evolution
import com.orghaniian.pokedex.ui.pokemondetails.moves.Moves
import com.orghaniian.pokedex.ui.utils.colorResourceID
import com.orghaniian.pokedex.ui.utils.formatOrder

@Composable
fun PokemonDetails(
    viewModel: PokemonDetailsViewModel = hiltViewModel(),
    onBackPressed: (() -> Unit)? = null
) {
    val state by viewModel.uiState.collectAsState()

    PokemonDetailsContent(
        uiState = state,
        onTabSelected = { viewModel.selectTab(it) },
        onBackPressed = onBackPressed
    )
}

@Composable
fun PokemonDetailsContent(
    uiState: PokemonDetailsUiState,
    onTabSelected: (PokemonDetailsUiState.Tab) -> Unit,
    onBackPressed: (() -> Unit)? = null
) {
    val localDensity = LocalDensity.current

    Surface(
        color = uiState.pokemon?.let{ colorResource(it.color.colorResourceID) }
            ?: MaterialTheme.colorScheme.background,
        contentColor = colorResource(R.color.on_type)
    ) {
        Column(
            modifier = Modifier.statusBarsPadding()
        ) {
            if(onBackPressed != null) {
                ReturnButton(
                    modifier = Modifier.padding(dimensionResource(R.dimen.screen_padding)),
                    onClick = onBackPressed
                )
            }
            if(uiState.pokemon == null) {
                CircularProgressIndicator()
            } else {
                var imageHeight by remember {
                    mutableStateOf(0.dp)
                }

                Box {
                    var columnHeightDp by remember {
                        mutableStateOf(0.dp)
                    }

                    Column {
                        Column(
                            modifier = Modifier
                                .padding(horizontal = dimensionResource(R.dimen.screen_padding))
                                .onGloballyPositioned { coordinates ->
                                    columnHeightDp =
                                        with(localDensity) { coordinates.size.height.toDp() }
                                }
                        ) {
                            Row(
                                verticalAlignment = Alignment.Bottom,
                                horizontalArrangement = Arrangement.SpaceBetween,
                                modifier = Modifier.fillMaxWidth()
                            ) {
                                Text(
                                    text = uiState.pokemon.name.capitalize(Locale.current),
                                    style = MaterialTheme.typography.headlineLarge
                                )
                                Text(
                                    text = formatOrder(uiState.pokemon.order),
                                    style = MaterialTheme.typography.labelLarge,
                                    color = colorResource(R.color.on_type)
                                )
                            }
                            Row(
                                horizontalArrangement = Arrangement.spacedBy(
                                    dimensionResource(R.dimen.pokemon_list_item_types_spacing)
                                ),
                                modifier = Modifier.padding(dimensionResource(R.dimen.margin_m))
                            ) {
                                uiState.pokemon.types.forEach {
                                    TypeChip(it)
                                }
                            }
                        }
                        Spacer(modifier = Modifier.height(imageHeight * 3/5))
                        DetailsTabs(
                            uiState = uiState,
                            onTabSelected = onTabSelected,
                            padding = PaddingValues(top = imageHeight * 1/5)
                        )
                    }
                    AsyncImage(
                        model = uiState.pokemon.spriteUrl,
                        contentDescription = stringResource(
                            R.string.pokemon_image_content_description,
                            uiState.pokemon.name
                        ),
                        modifier = Modifier
                            .height((LocalConfiguration.current.screenHeightDp * .3).dp)
                            .fillMaxWidth()
                            .padding(top = columnHeightDp)
                            .onGloballyPositioned { coordinates ->
                                imageHeight = with(localDensity) { coordinates.size.height.toDp() }
                            }
                    )
                }
            }
        }


    }
}

@Composable
private fun DetailsTabs(
    uiState: PokemonDetailsUiState,
    modifier: Modifier = Modifier,
    padding: PaddingValues,
    onTabSelected: (PokemonDetailsUiState.Tab) -> Unit,
) {
    Surface(
        color = MaterialTheme.colorScheme.background,
        contentColor = MaterialTheme.colorScheme.onBackground,
        shape = RoundedCornerShape(
            topStart = dimensionResource(R.dimen.drawer_corner_radius),
            topEnd = dimensionResource(R.dimen.drawer_corner_radius)
        ),
        modifier = modifier.fillMaxSize()
    ) {
        Column(
            modifier = Modifier.padding(padding)
        ) {
            TabRow(selectedTabIndex = uiState.tabs.indexOf(uiState.currentTab)) {
                uiState.tabs.forEach {
                    Tab(
                        selected = uiState.currentTab == it,
                        onClick = { onTabSelected(it) },
                        text = { Text(stringResource(it.titleId)) }
                    )
                }
            }

            Box(
                modifier = Modifier.padding(dimensionResource(R.dimen.screen_padding))
            ) {
                if (uiState.pokemon == null) {
                    CircularProgressIndicator()
                } else {
                    when(uiState.currentTab) {
                        PokemonDetailsUiState.Tab.About -> About(pokemon = uiState.pokemon)
                        PokemonDetailsUiState.Tab.BaseStats -> BaseStats(pokemon = uiState.pokemon)
                        PokemonDetailsUiState.Tab.Evolution -> Evolution(pokemon = uiState.pokemon)
                        PokemonDetailsUiState.Tab.Moves -> Moves(pokemon = uiState.pokemon)
                    }
                }
            }
        }
    }


}

@Preview(widthDp = 412, heightDp = 892)
@Composable
private fun PreviewPokemonDetails() {
    MaterialTheme{
        var currentTab by remember { mutableStateOf(PokemonDetailsUiState.Tab.About) }

        PokemonDetailsContent(
            uiState = PokemonDetailsUiState(
                pokemon = PokemonDetailsUiState.Pokemon(
                    "Bulbizarre",
                    1,
                    listOf(Type.GRASS, Type.POISON),
                    "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/official-artwork/1.png",
                    com.orghaniian.data.model.Color.GREEN,
                    0.1f,
                    2f,
                    .7f
                ),
                currentTab = currentTab,
                tabs = PokemonDetailsUiState.Tab.values().asList()
            ),
            onTabSelected = { currentTab = it}
        )
    }
}