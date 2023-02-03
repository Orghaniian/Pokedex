package com.orghaniian.pokedex.ui.theme

import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp

private val lightColors = lightColorScheme(
    primary = PokedexColor.light.primary,
    onPrimary = PokedexColor.light.onPrimary,
    primaryContainer = PokedexColor.light.primaryContainer,
    onPrimaryContainer = PokedexColor.light.onPrimaryContainer,
    secondary = PokedexColor.light.secondary,
    onSecondary = PokedexColor.light.onSecondary,
    secondaryContainer = PokedexColor.light.secondaryContainer,
    onSecondaryContainer = PokedexColor.light.onSecondaryContainer,
    tertiary = PokedexColor.light.tertiary,
    onTertiary = PokedexColor.light.onTertiary,
    tertiaryContainer = PokedexColor.light.tertiaryContainer,
    onTertiaryContainer = PokedexColor.light.onTertiaryContainer,
    error = PokedexColor.light.error,
    onError = PokedexColor.light.onError,
    errorContainer = PokedexColor.light.errorContainer,
    onErrorContainer = PokedexColor.light.onErrorContainer,
    outline = PokedexColor.light.outline,
    background = PokedexColor.light.background,
    onBackground = PokedexColor.light.onBackground,
    surface = PokedexColor.light.surface,
    onSurface = PokedexColor.light.onSurface,
    surfaceVariant = PokedexColor.light.surfaceVariant,
    onSurfaceVariant = PokedexColor.light.onSurfaceVariant,
    inverseSurface = PokedexColor.light.inverseSurface,
    inverseOnSurface = PokedexColor.light.inverseOnSurface,
    inversePrimary = PokedexColor.light.inversePrimary,
    surfaceTint = PokedexColor.light.surfaceTint
)

private val darkColors = darkColorScheme(
    primary = PokedexColor.dark.primary,
    onPrimary = PokedexColor.dark.onPrimary,
    primaryContainer = PokedexColor.dark.primaryContainer,
    onPrimaryContainer = PokedexColor.dark.onPrimaryContainer,
    secondary = PokedexColor.dark.secondary,
    onSecondary = PokedexColor.dark.onSecondary,
    secondaryContainer = PokedexColor.dark.secondaryContainer,
    onSecondaryContainer = PokedexColor.dark.onSecondaryContainer,
    tertiary = PokedexColor.dark.tertiary,
    onTertiary = PokedexColor.dark.onTertiary,
    tertiaryContainer = PokedexColor.dark.tertiaryContainer,
    onTertiaryContainer = PokedexColor.dark.onTertiaryContainer,
    error = PokedexColor.dark.error,
    onError = PokedexColor.dark.onError,
    errorContainer = PokedexColor.dark.errorContainer,
    onErrorContainer = PokedexColor.dark.onErrorContainer,
    outline = PokedexColor.dark.outline,
    background = PokedexColor.dark.background,
    onBackground = PokedexColor.dark.onBackground,
    surface = PokedexColor.dark.surface,
    onSurface = PokedexColor.dark.onSurface,
    surfaceVariant = PokedexColor.dark.surfaceVariant,
    onSurfaceVariant = PokedexColor.dark.onSurfaceVariant,
    inverseSurface = PokedexColor.dark.inverseSurface,
    inverseOnSurface = PokedexColor.dark.inverseOnSurface,
    inversePrimary = PokedexColor.dark.inversePrimary,
    surfaceTint = PokedexColor.dark.surfaceTint
)

private val typography = Typography(
    displayLarge = TextStyle(
        fontFamily = publicSans,
        fontWeight = FontWeight.Normal,
        lineHeight = 64.sp,
        fontSize = 57.sp,
        letterSpacing = (-.25).sp
    ),
    displayMedium = TextStyle(
        fontFamily = publicSans,
        fontWeight = FontWeight.Normal,
        lineHeight = 52.sp,
        fontSize = 45.sp,
        letterSpacing = 0.sp
    ),
    displaySmall = TextStyle(
        fontFamily = publicSans,
        fontWeight = FontWeight.Normal,
        lineHeight = 44.sp,
        fontSize = 36.sp,
        letterSpacing = 0.sp
    ),
    headlineLarge = TextStyle(
        fontFamily = publicSans,
        fontWeight = FontWeight.ExtraBold,
        lineHeight = 40.sp,
        fontSize = 32.sp,
        letterSpacing = 0.sp
    ),
    headlineMedium = TextStyle(
        fontFamily = publicSans,
        fontWeight = FontWeight.Normal,
        lineHeight = 36.sp,
        fontSize = 28.sp,
        letterSpacing = 0.sp
    ),
    headlineSmall = TextStyle(
        fontFamily = publicSans,
        fontWeight = FontWeight.Normal,
        lineHeight = 32.sp,
        fontSize = 24.sp,
        letterSpacing = 0.sp
    ),
    titleLarge = TextStyle(
        fontFamily = publicSans,
        fontWeight = FontWeight.Normal,
        lineHeight = 28.sp,
        fontSize = 22.sp,
        letterSpacing = 0.sp
    ),
    titleMedium = TextStyle(
        fontFamily = publicSans,
        fontWeight = FontWeight.Medium,
        lineHeight = 24.sp,
        fontSize = 16.sp,
        letterSpacing = .15.sp
    ),
    titleSmall = TextStyle(
        fontFamily = publicSans,
        fontWeight = FontWeight.Medium,
        lineHeight = 20.sp,
        fontSize = 14.sp,
        letterSpacing = .1.sp
    ),
    bodyLarge = TextStyle(
        fontFamily = publicSans,
        fontWeight = FontWeight.Normal,
        lineHeight = 24.sp,
        fontSize = 16.sp,
        letterSpacing = .5.sp
    ),
    bodyMedium = TextStyle(
        fontFamily = publicSans,
        fontWeight = FontWeight.Normal,
        lineHeight = 20.sp,
        fontSize = 14.sp,
        letterSpacing = .25.sp
    ),
    bodySmall = TextStyle(
        fontFamily = publicSans,
        fontWeight = FontWeight.Normal,
        lineHeight = 16.sp,
        fontSize = 12.sp,
        letterSpacing = .4.sp
    ),
    labelLarge = TextStyle(
        fontFamily = publicSans,
        fontWeight = FontWeight.SemiBold,
        lineHeight = 20.sp,
        fontSize = 14.sp,
        letterSpacing = .1.sp
    ),
    labelMedium = TextStyle(
        fontFamily = publicSans,
        fontWeight = FontWeight.Medium,
        lineHeight = 16.sp,
        fontSize = 12.sp,
        letterSpacing = .5.sp
    ),
    labelSmall = TextStyle(
        fontFamily = publicSans,
        fontWeight = FontWeight.Medium,
        lineHeight = 16.sp,
        fontSize = 11.sp,
        letterSpacing = .5.sp
    ),
)

@Composable
fun PokedexTheme(
    useDarkTheme: Boolean = isSystemInDarkTheme(),
    // Dynamic color is available on Android 12+
    dynamicColor: Boolean = true,
    content: @Composable () -> Unit
) {
    val colorScheme = when {
        dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
            val context = LocalContext.current
            if (useDarkTheme) dynamicDarkColorScheme(context) else dynamicLightColorScheme(context)
        }

        useDarkTheme -> darkColors
        else -> lightColors
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography= typography,
        content = content
    )
}