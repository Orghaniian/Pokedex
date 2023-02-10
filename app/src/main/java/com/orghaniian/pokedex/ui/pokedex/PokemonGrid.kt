package com.orghaniian.pokedex.ui.pokedex

import android.os.Build.VERSION.SDK_INT
import androidx.compose.animation.core.AnimationVector1D
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.TwoWayConverter
import androidx.compose.animation.core.animateValue
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyGridScope
import androidx.compose.foundation.lazy.grid.LazyGridState
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.paging.LoadState.Loading
import androidx.paging.compose.LazyPagingItems
import coil.ImageLoader
import coil.compose.rememberAsyncImagePainter
import coil.decode.GifDecoder
import coil.decode.ImageDecoderDecoder.Factory
import coil.request.ImageRequest.Builder
import coil.size.Size
import com.orghaniian.data.model.Color
import com.orghaniian.data.model.Type
import com.orghaniian.pokedex.R.raw
import com.orghaniian.pokedex.ui.theme.Dimensions

@Composable
fun PokemonGrid(
    modifier: Modifier = Modifier,
    pokemons: LazyPagingItems<PokedexItemUiState>,
    onPokemonClick: (PokedexItemUiState) -> Unit,
    state: LazyGridState = rememberLazyGridState(),
) = Box(modifier = modifier.fillMaxSize()) {
    CustomGrid(
        state = state
    ) {
        items(
            count = pokemons.itemCount,
            key = { index -> index }
        ) { index ->
            pokemons[index]?.let {
                PokemonCard(
                    it,
                    modifier = Modifier
                        .clickable {
                            onPokemonClick(it)
                        }
                        .height(100.dp)
                )
            } ?: SkeletonPokemonCard(modifier = Modifier.height(100.dp))
        }
    }

    val isLoading = pokemons.loadState.mediator
        ?.let { it.append is Loading || it.prepend is Loading || it.refresh is Loading }
        ?: false

    if(isLoading) Loader(modifier = Modifier.align(Alignment.BottomCenter))
}

@Composable
private fun Loader(
    modifier: Modifier = Modifier
) {
    val gifSize = 30.dp
    val screenWidth = LocalConfiguration.current.screenWidthDp.dp

    val infiniteTransition = rememberInfiniteTransition()
    val translation : Dp by infiniteTransition.animateValue(
        initialValue = -gifSize,
        targetValue = screenWidth + gifSize/2,
        animationSpec = infiniteRepeatable(
            animation = tween(5000, easing = LinearEasing),
            repeatMode = RepeatMode.Restart
        ),
        typeConverter = TwoWayConverter(
            convertToVector = { size: Dp ->
                AnimationVector1D(size.value)
            },
            convertFromVector = { vector: AnimationVector1D ->
                vector.value.dp
            }
        )
    )

    return Row(modifier = modifier.fillMaxWidth()) {
        Image(
            painter = rememberAsyncImagePainter(
                Builder(LocalContext.current).data(
                    data = raw.walking_bulbasaur
                ).apply { size(Size.ORIGINAL) }.build(),
                imageLoader = ImageLoader.Builder(LocalContext.current)
                    .components {
                        if (SDK_INT >= 28) {
                            add(Factory())
                        } else {
                            add(GifDecoder.Factory())
                        }
                    }
                    .build()
            ),
            contentDescription = "Loading",
            modifier = Modifier
                .wrapContentSize(unbounded = true)
                .size(gifSize)
                .offset(x = translation, y = 2.dp)
                .graphicsLayer { rotationY = 180f }
        )
    }
}

@Composable
private fun CustomGrid(
    modifier: Modifier = Modifier,
    contentPadding: PaddingValues = PaddingValues(16.dp),
    state: LazyGridState = rememberLazyGridState(),
    content: LazyGridScope.() -> Unit,
) = LazyVerticalGrid(
    verticalArrangement = Arrangement.spacedBy(Dimensions.marginM),
    horizontalArrangement = Arrangement.spacedBy(Dimensions.marginM),
    modifier = modifier,
    columns = GridCells.Adaptive(minSize = 128.dp),
    contentPadding = contentPadding,
    state = state,
    content = content
)

@Preview(widthDp = 412, heightDp = 892)
@Composable
private fun PreviewCustomGrid() {
    val pokemons = buildList {
        repeat(16) {
            add(
                PokedexItemUiState(
                    "Bulbizarre",
                    1,
                    listOf(Type.GRASS, Type.POISON),
                    "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/official-artwork/1.png",
                    Color.GREEN,
                )
            )
            add(
                PokedexItemUiState(
                    "Bulbizarre",
                    1,
                    listOf(Type.GRASS, Type.POISON),
                    "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/official-artwork/1.png",
                    Color.GREEN,
                )
            )
            add(
                PokedexItemUiState(
                    "Bulbizarre",
                    1,
                    listOf(Type.GRASS),
                    "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/official-artwork/1.png",
                    Color.RED,
                )
            )
        }
    }
    
    MaterialTheme {
        CustomGrid{
            items(pokemons) {
                PokemonCard(it, modifier = Modifier.height(100.dp))
            }
        }
    }
}
