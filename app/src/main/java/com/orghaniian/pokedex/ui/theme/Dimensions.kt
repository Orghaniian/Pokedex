package com.orghaniian.pokedex.ui.theme

import androidx.compose.runtime.*
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Composable
fun Dimensions(
    dimensions: DimensionsValues = Dimensions,
    content: @Composable () -> Unit
) {
    val rememberedDimensValues = remember {
        dimensions.copy()
    }.apply {
        updateDimensValuesFrom(dimensions)
    }
    
    CompositionLocalProvider(
        LocalDimens provides rememberedDimensValues
    ) {
        CompositionLocalProvider(content = content)
    }
}

val LocalDimens = staticCompositionLocalOf { 
    DimensionsValues()
}

val Dimensions: DimensionsValues
    @Composable
    @ReadOnlyComposable
    get() = LocalDimens.current

/**
 * Contains functions to access the current theme values provided at the call site's position in
 * the hierarchy.
 */
class DimensionsValues (
    cornerRadius: Dp = 16.dp,
    drawerCornerRadius: Dp = 24.dp,
    typeChipHeight: Dp = 22.dp,
    typeChipPaddingHorizontal: Dp = 6.dp,
    typeChipIconSize: Dp = 14.dp,
    typeChipSpacing: Dp = 4.dp,
    pokemonListItemPadding: Dp = 8.dp,
    pokemonListItemLeftColumnMargin: Dp = 4.dp,
    pokemonListItemNameMarginBottom: Dp = 10.dp,
    pokemonListItemTypesSpacing: Dp = 5.dp,
    screenPadding: Dp = 16.dp,
    marginM: Dp = 8.dp,
    iconButtonRippleRadius: Dp = 16.dp,
) {
    var cornerRadius by mutableStateOf(cornerRadius, structuralEqualityPolicy())
        internal set
    var drawerCornerRadius by mutableStateOf(drawerCornerRadius, structuralEqualityPolicy())
        internal set
    var typeChipHeight by mutableStateOf(typeChipHeight, structuralEqualityPolicy())
        internal set
    var typeChipPaddingHorizontal by mutableStateOf(typeChipPaddingHorizontal, structuralEqualityPolicy())
        internal set
    var typeChipIconSize by mutableStateOf(typeChipIconSize, structuralEqualityPolicy())
        internal set
    var typeChipSpacing by mutableStateOf(typeChipSpacing, structuralEqualityPolicy())
        internal set
    var pokemonListItemPadding by mutableStateOf(pokemonListItemPadding, structuralEqualityPolicy())
        internal set
    var pokemonListItemLeftColumnMargin by mutableStateOf(pokemonListItemLeftColumnMargin, structuralEqualityPolicy())
        internal set
    var pokemonListItemNameMarginBottom by mutableStateOf(pokemonListItemNameMarginBottom, structuralEqualityPolicy())
        internal set
    var pokemonListItemTypesSpacing by mutableStateOf(pokemonListItemTypesSpacing, structuralEqualityPolicy())
        internal set
    var screenPadding by mutableStateOf(screenPadding, structuralEqualityPolicy())
        internal set
    var marginM by mutableStateOf(marginM, structuralEqualityPolicy())
        internal set
    var iconButtonRippleRadius by mutableStateOf(iconButtonRippleRadius, structuralEqualityPolicy())
        internal set
    
    fun copy(
        cornerRadius: Dp = this.cornerRadius,
        drawerCornerRadius: Dp = this.drawerCornerRadius,
        typeChipHeight: Dp = this.typeChipHeight,
        typeChipPaddingHorizontal: Dp = this.typeChipPaddingHorizontal,
        typeChipIconSize: Dp = this.typeChipIconSize,
        typeChipSpacing: Dp = this.typeChipSpacing,
        pokemonListItemPadding: Dp = this.pokemonListItemPadding,
        pokemonListItemLeftColumnMargin: Dp = this.pokemonListItemLeftColumnMargin,
        pokemonListItemNameMarginBottom: Dp = this.pokemonListItemNameMarginBottom,
        pokemonListItemTypesSpacing: Dp = this.pokemonListItemTypesSpacing,
        screenPadding: Dp = this.screenPadding,
        marginM: Dp = this.marginM,
        iconButtonRippleRadius: Dp = this.iconButtonRippleRadius,
    ) = DimensionsValues(
        cornerRadius = cornerRadius,
        drawerCornerRadius = drawerCornerRadius,
        typeChipHeight = typeChipHeight,
        typeChipPaddingHorizontal = typeChipPaddingHorizontal,
        typeChipIconSize = typeChipIconSize,
        typeChipSpacing = typeChipSpacing,
        pokemonListItemPadding = pokemonListItemPadding,
        pokemonListItemLeftColumnMargin = pokemonListItemLeftColumnMargin,
        pokemonListItemNameMarginBottom = pokemonListItemNameMarginBottom,
        pokemonListItemTypesSpacing = pokemonListItemTypesSpacing,
        screenPadding = screenPadding,
        marginM = marginM,
        iconButtonRippleRadius = iconButtonRippleRadius,
    )
}

internal fun DimensionsValues.updateDimensValuesFrom(other: DimensionsValues) {
    cornerRadius = other.cornerRadius
    drawerCornerRadius = other.drawerCornerRadius
    typeChipHeight = other.typeChipHeight
    typeChipPaddingHorizontal = other.typeChipPaddingHorizontal
    typeChipIconSize = other.typeChipIconSize
    typeChipSpacing = other.typeChipSpacing
    pokemonListItemPadding = other.pokemonListItemPadding
    pokemonListItemLeftColumnMargin = other.pokemonListItemLeftColumnMargin
    pokemonListItemNameMarginBottom = other.pokemonListItemNameMarginBottom
    pokemonListItemTypesSpacing = other.pokemonListItemTypesSpacing
    screenPadding = other.screenPadding
    marginM = other.marginM
    iconButtonRippleRadius = other.iconButtonRippleRadius
}