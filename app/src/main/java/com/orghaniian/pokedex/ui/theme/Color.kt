package com.orghaniian.pokedex.ui.theme

import androidx.compose.ui.graphics.Color

object PokedexColor {
    val seed = Color(0xFF6A77DA)
    val light = Colors(
        primary = Color(0xFF4855B6),
        onPrimary = Color(0xFFFFFFFF),
        primaryContainer = Color(0xFFDFE0FF),
        onPrimaryContainer = Color(0xFF000B62),
        secondary = Color(0xFF3A6A1D),
        onSecondary = Color(0xFFFFFFFF),
        secondaryContainer = Color(0xFFBAF295),
        onSecondaryContainer = Color(0xFF092100),
        tertiary = Color(0xFF9A4522),
        onTertiary = Color(0xFFFFFFFF),
        tertiaryContainer = Color(0xFFFFDBCE),
        onTertiaryContainer = Color(0xFF380D00),
        error = Color(0xFFBA1A1A),
        errorContainer = Color(0xFFFFDAD6),
        onError = Color(0xFFFFFFFF),
        onErrorContainer = Color(0xFF410002),
        background = Color(0xFFF6FEFF),
        onBackground = Color(0xFF001F24),
        surface = Color(0xFFF6FEFF),
        onSurface = Color(0xFF001F24),
        surfaceVariant = Color(0xFFE3E1EC),
        onSurfaceVariant = Color(0xFF46464F),
        outline = Color(0xFF777680),
        inverseOnSurface = Color(0xFFD0F8FF),
        inverseSurface = Color(0xFF00363D),
        inversePrimary = Color(0xFFBCC2FF),
        shadow = Color(0xFF000000),
        surfaceTint = Color(0xFF4855B6),
        surfaceTintColor = Color(0xFF4855B6),
    )
    val dark = Colors(
        primary = Color(0xFFBCC2FF),
        onPrimary = Color(0xFF132286),
        primaryContainer = Color(0xFF2F3C9D),
        onPrimaryContainer = Color(0xFFDFE0FF),
        secondary = Color(0xFF9FD67B),
        onSecondary = Color(0xFF153800),
        secondaryContainer = Color(0xFF235104),
        onSecondaryContainer = Color(0xFFBAF295),
        tertiary = Color(0xFFFFB59A),
        onTertiary = Color(0xFF5B1B00),
        tertiaryContainer = Color(0xFF7B2E0C),
        onTertiaryContainer = Color(0xFFFFDBCE),
        error = Color(0xFFFFB4AB),
        errorContainer = Color(0xFF93000A),
        onError = Color(0xFF690005),
        onErrorContainer = Color(0xFFFFDAD6),
        background = Color(0xFF001F24),
        onBackground = Color(0xFFF6FEFF),
        surface = Color(0xFF001F24),
        onSurface = Color(0xFFF6FEFF),
        surfaceVariant = Color(0xFF46464F),
        onSurfaceVariant = Color(0xFFC7C5D0),
        outline = Color(0xFF90909A),
        inverseOnSurface = Color(0xFF001F24),
        inverseSurface = Color(0xFF97F0FF),
        inversePrimary = Color(0xFF4855B6),
        shadow = Color(0xFF000000),
        surfaceTint = Color(0xFFBCC2FF),
        surfaceTintColor = Color(0xFFBCC2FF),
    )

    val green = Color(0xFF46D1B1)
    val yellow = Color(0xFFFFCF4A)
    val red = Color(0xFFFB6C6C)
    val blue = Color(0xFF77BEFE)
    val brown = Color(0xFFB2746C)
    val purple = Color(0xFF7D528D)
    val white = Color(0xFFC2C2C2)
    val pink = Color(0xffff88dc)
    val gray = Color(0xFF72727E)
    val black = Color(0xFF272727)


    val typeChipBackground = Color(0x4DFFFFFF)

    val onType = Color(0xFFFFFFFF)
    val onTypeVariant = Color(0x42000000)
}

data class Colors (
    val primary : Color,
    val onPrimary : Color,
    val primaryContainer : Color,
    val onPrimaryContainer : Color,
    val secondary : Color,
    val onSecondary : Color,
    val secondaryContainer : Color,
    val onSecondaryContainer : Color,
    val tertiary : Color,
    val onTertiary : Color,
    val tertiaryContainer : Color,
    val onTertiaryContainer : Color,
    val error : Color,
    val errorContainer : Color,
    val onError : Color,
    val onErrorContainer : Color,
    val background : Color,
    val onBackground : Color,
    val surface : Color,
    val onSurface : Color,
    val surfaceVariant : Color,
    val onSurfaceVariant : Color,
    val outline : Color,
    val inverseOnSurface : Color,
    val inverseSurface : Color,
    val inversePrimary : Color,
    val shadow : Color,
    val surfaceTint : Color,
    val surfaceTintColor : Color,
)