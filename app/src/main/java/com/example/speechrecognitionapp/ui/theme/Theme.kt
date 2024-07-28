package com.example.speechrecognitionapp.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Color

private val LightColors = lightColorScheme(
    primary = com.example.speechrecognitionapp.ui.theme.primaryLight,
    onPrimary = com.example.speechrecognitionapp.ui.theme.onPrimaryLight,
    primaryContainer = com.example.speechrecognitionapp.ui.theme.primaryContainerLight,
    onPrimaryContainer = com.example.speechrecognitionapp.ui.theme.onPrimaryContainerLight,
    secondary = com.example.speechrecognitionapp.ui.theme.secondaryLight,
    onSecondary = com.example.speechrecognitionapp.ui.theme.onSecondaryLight,
    secondaryContainer = com.example.speechrecognitionapp.ui.theme.secondaryContainerLight,
    onSecondaryContainer = com.example.speechrecognitionapp.ui.theme.onSecondaryContainerLight,
    tertiary = com.example.speechrecognitionapp.ui.theme.tertiaryLight,
    onTertiary = com.example.speechrecognitionapp.ui.theme.onTertiaryLight,
    tertiaryContainer = com.example.speechrecognitionapp.ui.theme.tertiaryContainerLight,
    onTertiaryContainer = com.example.speechrecognitionapp.ui.theme.onTertiaryContainerLight,
    error = com.example.speechrecognitionapp.ui.theme.errorLight,
    onError = com.example.speechrecognitionapp.ui.theme.onErrorLight,
    errorContainer = com.example.speechrecognitionapp.ui.theme.errorContainerLight,
    onErrorContainer = com.example.speechrecognitionapp.ui.theme.onErrorContainerLight,
    background = com.example.speechrecognitionapp.ui.theme.backgroundLight,
    onBackground = com.example.speechrecognitionapp.ui.theme.onBackgroundLight,
    surface = com.example.speechrecognitionapp.ui.theme.surfaceLight,
    onSurface = com.example.speechrecognitionapp.ui.theme.onSurfaceLight,
    surfaceVariant = com.example.speechrecognitionapp.ui.theme.surfaceVariantLight,
    onSurfaceVariant = com.example.speechrecognitionapp.ui.theme.onSurfaceVariantLight,
    outline = com.example.speechrecognitionapp.ui.theme.outlineLight,
    outlineVariant = com.example.speechrecognitionapp.ui.theme.outlineVariantLight,
    scrim = com.example.speechrecognitionapp.ui.theme.scrimLight,
    inverseSurface = com.example.speechrecognitionapp.ui.theme.inverseSurfaceLight,
    inverseOnSurface = com.example.speechrecognitionapp.ui.theme.inverseOnSurfaceLight,
    inversePrimary = com.example.speechrecognitionapp.ui.theme.inversePrimaryLight,
    surfaceDim = com.example.speechrecognitionapp.ui.theme.surfaceDimLight,
    surfaceBright = com.example.speechrecognitionapp.ui.theme.surfaceBrightLight,
    surfaceContainerLowest = com.example.speechrecognitionapp.ui.theme.surfaceContainerLowestLight,
    surfaceContainerLow = com.example.speechrecognitionapp.ui.theme.surfaceContainerLowLight,
    surfaceContainer = com.example.speechrecognitionapp.ui.theme.surfaceContainerLight,
    surfaceContainerHigh = com.example.speechrecognitionapp.ui.theme.surfaceContainerHighLight,
    surfaceContainerHighest = com.example.speechrecognitionapp.ui.theme.surfaceContainerHighestLight,
)

private val DarkColors = darkColorScheme(
    primary = com.example.speechrecognitionapp.ui.theme.primaryDark,
    onPrimary = com.example.speechrecognitionapp.ui.theme.onPrimaryDark,
    primaryContainer = com.example.speechrecognitionapp.ui.theme.primaryContainerDark,
    onPrimaryContainer = com.example.speechrecognitionapp.ui.theme.onPrimaryContainerDark,
    secondary = com.example.speechrecognitionapp.ui.theme.secondaryDark,
    onSecondary = com.example.speechrecognitionapp.ui.theme.onSecondaryDark,
    secondaryContainer = com.example.speechrecognitionapp.ui.theme.secondaryContainerDark,
    onSecondaryContainer = com.example.speechrecognitionapp.ui.theme.onSecondaryContainerDark,
    tertiary = com.example.speechrecognitionapp.ui.theme.tertiaryDark,
    onTertiary = com.example.speechrecognitionapp.ui.theme.onTertiaryDark,
    tertiaryContainer = com.example.speechrecognitionapp.ui.theme.tertiaryContainerDark,
    onTertiaryContainer = com.example.speechrecognitionapp.ui.theme.onTertiaryContainerDark,
    error = com.example.speechrecognitionapp.ui.theme.errorDark,
    onError = com.example.speechrecognitionapp.ui.theme.onErrorDark,
    errorContainer = com.example.speechrecognitionapp.ui.theme.errorContainerDark,
    onErrorContainer = com.example.speechrecognitionapp.ui.theme.onErrorContainerDark,
    background = com.example.speechrecognitionapp.ui.theme.backgroundDark,
    onBackground = com.example.speechrecognitionapp.ui.theme.onBackgroundDark,
    surface = com.example.speechrecognitionapp.ui.theme.surfaceDark,
    onSurface = com.example.speechrecognitionapp.ui.theme.onSurfaceDark,
    surfaceVariant = com.example.speechrecognitionapp.ui.theme.surfaceVariantDark,
    onSurfaceVariant = com.example.speechrecognitionapp.ui.theme.onSurfaceVariantDark,
    outline = com.example.speechrecognitionapp.ui.theme.outlineDark,
    outlineVariant = com.example.speechrecognitionapp.ui.theme.outlineVariantDark,
    scrim = com.example.speechrecognitionapp.ui.theme.scrimDark,
    inverseSurface = com.example.speechrecognitionapp.ui.theme.inverseSurfaceDark,
    inverseOnSurface = com.example.speechrecognitionapp.ui.theme.inverseOnSurfaceDark,
    inversePrimary = com.example.speechrecognitionapp.ui.theme.inversePrimaryDark,
    surfaceDim = com.example.speechrecognitionapp.ui.theme.surfaceDimDark,
    surfaceBright = com.example.speechrecognitionapp.ui.theme.surfaceBrightDark,
    surfaceContainerLowest = com.example.speechrecognitionapp.ui.theme.surfaceContainerLowestDark,
    surfaceContainerLow = com.example.speechrecognitionapp.ui.theme.surfaceContainerLowDark,
    surfaceContainer = com.example.speechrecognitionapp.ui.theme.surfaceContainerDark,
    surfaceContainerHigh = com.example.speechrecognitionapp.ui.theme.surfaceContainerHighDark,
    surfaceContainerHighest = com.example.speechrecognitionapp.ui.theme.surfaceContainerHighestDark,
)

private val mediumContrastLightColorScheme = lightColorScheme(
    primary = com.example.speechrecognitionapp.ui.theme.primaryLightMediumContrast,
    onPrimary = com.example.speechrecognitionapp.ui.theme.onPrimaryLightMediumContrast,
    primaryContainer = com.example.speechrecognitionapp.ui.theme.primaryContainerLightMediumContrast,
    onPrimaryContainer = com.example.speechrecognitionapp.ui.theme.onPrimaryContainerLightMediumContrast,
    secondary = com.example.speechrecognitionapp.ui.theme.secondaryLightMediumContrast,
    onSecondary = com.example.speechrecognitionapp.ui.theme.onSecondaryLightMediumContrast,
    secondaryContainer = com.example.speechrecognitionapp.ui.theme.secondaryContainerLightMediumContrast,
    onSecondaryContainer = com.example.speechrecognitionapp.ui.theme.onSecondaryContainerLightMediumContrast,
    tertiary = com.example.speechrecognitionapp.ui.theme.tertiaryLightMediumContrast,
    onTertiary = com.example.speechrecognitionapp.ui.theme.onTertiaryLightMediumContrast,
    tertiaryContainer = com.example.speechrecognitionapp.ui.theme.tertiaryContainerLightMediumContrast,
    onTertiaryContainer = com.example.speechrecognitionapp.ui.theme.onTertiaryContainerLightMediumContrast,
    error = com.example.speechrecognitionapp.ui.theme.errorLightMediumContrast,
    onError = com.example.speechrecognitionapp.ui.theme.onErrorLightMediumContrast,
    errorContainer = com.example.speechrecognitionapp.ui.theme.errorContainerLightMediumContrast,
    onErrorContainer = com.example.speechrecognitionapp.ui.theme.onErrorContainerLightMediumContrast,
    background = com.example.speechrecognitionapp.ui.theme.backgroundLightMediumContrast,
    onBackground = com.example.speechrecognitionapp.ui.theme.onBackgroundLightMediumContrast,
    surface = com.example.speechrecognitionapp.ui.theme.surfaceLightMediumContrast,
    onSurface = com.example.speechrecognitionapp.ui.theme.onSurfaceLightMediumContrast,
    surfaceVariant = com.example.speechrecognitionapp.ui.theme.surfaceVariantLightMediumContrast,
    onSurfaceVariant = com.example.speechrecognitionapp.ui.theme.onSurfaceVariantLightMediumContrast,
    outline = com.example.speechrecognitionapp.ui.theme.outlineLightMediumContrast,
    outlineVariant = com.example.speechrecognitionapp.ui.theme.outlineVariantLightMediumContrast,
    scrim = com.example.speechrecognitionapp.ui.theme.scrimLightMediumContrast,
    inverseSurface = com.example.speechrecognitionapp.ui.theme.inverseSurfaceLightMediumContrast,
    inverseOnSurface = com.example.speechrecognitionapp.ui.theme.inverseOnSurfaceLightMediumContrast,
    inversePrimary = com.example.speechrecognitionapp.ui.theme.inversePrimaryLightMediumContrast,
    surfaceDim = com.example.speechrecognitionapp.ui.theme.surfaceDimLightMediumContrast,
    surfaceBright = com.example.speechrecognitionapp.ui.theme.surfaceBrightLightMediumContrast,
    surfaceContainerLowest = com.example.speechrecognitionapp.ui.theme.surfaceContainerLowestLightMediumContrast,
    surfaceContainerLow = com.example.speechrecognitionapp.ui.theme.surfaceContainerLowLightMediumContrast,
    surfaceContainer = com.example.speechrecognitionapp.ui.theme.surfaceContainerLightMediumContrast,
    surfaceContainerHigh = com.example.speechrecognitionapp.ui.theme.surfaceContainerHighLightMediumContrast,
    surfaceContainerHighest = com.example.speechrecognitionapp.ui.theme.surfaceContainerHighestLightMediumContrast,
)

private val highContrastLightColorScheme = lightColorScheme(
    primary = com.example.speechrecognitionapp.ui.theme.primaryLightHighContrast,
    onPrimary = com.example.speechrecognitionapp.ui.theme.onPrimaryLightHighContrast,
    primaryContainer = com.example.speechrecognitionapp.ui.theme.primaryContainerLightHighContrast,
    onPrimaryContainer = com.example.speechrecognitionapp.ui.theme.onPrimaryContainerLightHighContrast,
    secondary = com.example.speechrecognitionapp.ui.theme.secondaryLightHighContrast,
    onSecondary = com.example.speechrecognitionapp.ui.theme.onSecondaryLightHighContrast,
    secondaryContainer = com.example.speechrecognitionapp.ui.theme.secondaryContainerLightHighContrast,
    onSecondaryContainer = com.example.speechrecognitionapp.ui.theme.onSecondaryContainerLightHighContrast,
    tertiary = com.example.speechrecognitionapp.ui.theme.tertiaryLightHighContrast,
    onTertiary = com.example.speechrecognitionapp.ui.theme.onTertiaryLightHighContrast,
    tertiaryContainer = com.example.speechrecognitionapp.ui.theme.tertiaryContainerLightHighContrast,
    onTertiaryContainer = com.example.speechrecognitionapp.ui.theme.onTertiaryContainerLightHighContrast,
    error = com.example.speechrecognitionapp.ui.theme.errorLightHighContrast,
    onError = com.example.speechrecognitionapp.ui.theme.onErrorLightHighContrast,
    errorContainer = com.example.speechrecognitionapp.ui.theme.errorContainerLightHighContrast,
    onErrorContainer = com.example.speechrecognitionapp.ui.theme.onErrorContainerLightHighContrast,
    background = com.example.speechrecognitionapp.ui.theme.backgroundLightHighContrast,
    onBackground = com.example.speechrecognitionapp.ui.theme.onBackgroundLightHighContrast,
    surface = com.example.speechrecognitionapp.ui.theme.surfaceLightHighContrast,
    onSurface = com.example.speechrecognitionapp.ui.theme.onSurfaceLightHighContrast,
    surfaceVariant = com.example.speechrecognitionapp.ui.theme.surfaceVariantLightHighContrast,
    onSurfaceVariant = com.example.speechrecognitionapp.ui.theme.onSurfaceVariantLightHighContrast,
    outline = com.example.speechrecognitionapp.ui.theme.outlineLightHighContrast,
    outlineVariant = com.example.speechrecognitionapp.ui.theme.outlineVariantLightHighContrast,
    scrim = com.example.speechrecognitionapp.ui.theme.scrimLightHighContrast,
    inverseSurface = com.example.speechrecognitionapp.ui.theme.inverseSurfaceLightHighContrast,
    inverseOnSurface = com.example.speechrecognitionapp.ui.theme.inverseOnSurfaceLightHighContrast,
    inversePrimary = com.example.speechrecognitionapp.ui.theme.inversePrimaryLightHighContrast,
    surfaceDim = com.example.speechrecognitionapp.ui.theme.surfaceDimLightHighContrast,
    surfaceBright = com.example.speechrecognitionapp.ui.theme.surfaceBrightLightHighContrast,
    surfaceContainerLowest = com.example.speechrecognitionapp.ui.theme.surfaceContainerLowestLightHighContrast,
    surfaceContainerLow = com.example.speechrecognitionapp.ui.theme.surfaceContainerLowLightHighContrast,
    surfaceContainer = com.example.speechrecognitionapp.ui.theme.surfaceContainerLightHighContrast,
    surfaceContainerHigh = com.example.speechrecognitionapp.ui.theme.surfaceContainerHighLightHighContrast,
    surfaceContainerHighest = com.example.speechrecognitionapp.ui.theme.surfaceContainerHighestLightHighContrast,
)

private val mediumContrastDarkColorScheme = darkColorScheme(
    primary = com.example.speechrecognitionapp.ui.theme.primaryDarkMediumContrast,
    onPrimary = com.example.speechrecognitionapp.ui.theme.onPrimaryDarkMediumContrast,
    primaryContainer = com.example.speechrecognitionapp.ui.theme.primaryContainerDarkMediumContrast,
    onPrimaryContainer = com.example.speechrecognitionapp.ui.theme.onPrimaryContainerDarkMediumContrast,
    secondary = com.example.speechrecognitionapp.ui.theme.secondaryDarkMediumContrast,
    onSecondary = com.example.speechrecognitionapp.ui.theme.onSecondaryDarkMediumContrast,
    secondaryContainer = com.example.speechrecognitionapp.ui.theme.secondaryContainerDarkMediumContrast,
    onSecondaryContainer = com.example.speechrecognitionapp.ui.theme.onSecondaryContainerDarkMediumContrast,
    tertiary = com.example.speechrecognitionapp.ui.theme.tertiaryDarkMediumContrast,
    onTertiary = com.example.speechrecognitionapp.ui.theme.onTertiaryDarkMediumContrast,
    tertiaryContainer = com.example.speechrecognitionapp.ui.theme.tertiaryContainerDarkMediumContrast,
    onTertiaryContainer = com.example.speechrecognitionapp.ui.theme.onTertiaryContainerDarkMediumContrast,
    error = com.example.speechrecognitionapp.ui.theme.errorDarkMediumContrast,
    onError = com.example.speechrecognitionapp.ui.theme.onErrorDarkMediumContrast,
    errorContainer = com.example.speechrecognitionapp.ui.theme.errorContainerDarkMediumContrast,
    onErrorContainer = com.example.speechrecognitionapp.ui.theme.onErrorContainerDarkMediumContrast,
    background = com.example.speechrecognitionapp.ui.theme.backgroundDarkMediumContrast,
    onBackground = com.example.speechrecognitionapp.ui.theme.onBackgroundDarkMediumContrast,
    surface = com.example.speechrecognitionapp.ui.theme.surfaceDarkMediumContrast,
    onSurface = com.example.speechrecognitionapp.ui.theme.onSurfaceDarkMediumContrast,
    surfaceVariant = com.example.speechrecognitionapp.ui.theme.surfaceVariantDarkMediumContrast,
    onSurfaceVariant = com.example.speechrecognitionapp.ui.theme.onSurfaceVariantDarkMediumContrast,
    outline = com.example.speechrecognitionapp.ui.theme.outlineDarkMediumContrast,
    outlineVariant = com.example.speechrecognitionapp.ui.theme.outlineVariantDarkMediumContrast,
    scrim = com.example.speechrecognitionapp.ui.theme.scrimDarkMediumContrast,
    inverseSurface = com.example.speechrecognitionapp.ui.theme.inverseSurfaceDarkMediumContrast,
    inverseOnSurface = com.example.speechrecognitionapp.ui.theme.inverseOnSurfaceDarkMediumContrast,
    inversePrimary = com.example.speechrecognitionapp.ui.theme.inversePrimaryDarkMediumContrast,
    surfaceDim = com.example.speechrecognitionapp.ui.theme.surfaceDimDarkMediumContrast,
    surfaceBright = com.example.speechrecognitionapp.ui.theme.surfaceBrightDarkMediumContrast,
    surfaceContainerLowest = com.example.speechrecognitionapp.ui.theme.surfaceContainerLowestDarkMediumContrast,
    surfaceContainerLow = com.example.speechrecognitionapp.ui.theme.surfaceContainerLowDarkMediumContrast,
    surfaceContainer = com.example.speechrecognitionapp.ui.theme.surfaceContainerDarkMediumContrast,
    surfaceContainerHigh = com.example.speechrecognitionapp.ui.theme.surfaceContainerHighDarkMediumContrast,
    surfaceContainerHighest = com.example.speechrecognitionapp.ui.theme.surfaceContainerHighestDarkMediumContrast,
)

private val highContrastDarkColorScheme = darkColorScheme(
    primary = com.example.speechrecognitionapp.ui.theme.primaryDarkHighContrast,
    onPrimary = com.example.speechrecognitionapp.ui.theme.onPrimaryDarkHighContrast,
    primaryContainer = com.example.speechrecognitionapp.ui.theme.primaryContainerDarkHighContrast,
    onPrimaryContainer = com.example.speechrecognitionapp.ui.theme.onPrimaryContainerDarkHighContrast,
    secondary = com.example.speechrecognitionapp.ui.theme.secondaryDarkHighContrast,
    onSecondary = com.example.speechrecognitionapp.ui.theme.onSecondaryDarkHighContrast,
    secondaryContainer = com.example.speechrecognitionapp.ui.theme.secondaryContainerDarkHighContrast,
    onSecondaryContainer = com.example.speechrecognitionapp.ui.theme.onSecondaryContainerDarkHighContrast,
    tertiary = com.example.speechrecognitionapp.ui.theme.tertiaryDarkHighContrast,
    onTertiary = com.example.speechrecognitionapp.ui.theme.onTertiaryDarkHighContrast,
    tertiaryContainer = com.example.speechrecognitionapp.ui.theme.tertiaryContainerDarkHighContrast,
    onTertiaryContainer = com.example.speechrecognitionapp.ui.theme.onTertiaryContainerDarkHighContrast,
    error = com.example.speechrecognitionapp.ui.theme.errorDarkHighContrast,
    onError = com.example.speechrecognitionapp.ui.theme.onErrorDarkHighContrast,
    errorContainer = com.example.speechrecognitionapp.ui.theme.errorContainerDarkHighContrast,
    onErrorContainer = com.example.speechrecognitionapp.ui.theme.onErrorContainerDarkHighContrast,
    background = com.example.speechrecognitionapp.ui.theme.backgroundDarkHighContrast,
    onBackground = com.example.speechrecognitionapp.ui.theme.onBackgroundDarkHighContrast,
    surface = com.example.speechrecognitionapp.ui.theme.surfaceDarkHighContrast,
    onSurface = com.example.speechrecognitionapp.ui.theme.onSurfaceDarkHighContrast,
    surfaceVariant = com.example.speechrecognitionapp.ui.theme.surfaceVariantDarkHighContrast,
    onSurfaceVariant = com.example.speechrecognitionapp.ui.theme.onSurfaceVariantDarkHighContrast,
    outline = com.example.speechrecognitionapp.ui.theme.outlineDarkHighContrast,
    outlineVariant = com.example.speechrecognitionapp.ui.theme.outlineVariantDarkHighContrast,
    scrim = com.example.speechrecognitionapp.ui.theme.scrimDarkHighContrast,
    inverseSurface = com.example.speechrecognitionapp.ui.theme.inverseSurfaceDarkHighContrast,
    inverseOnSurface = com.example.speechrecognitionapp.ui.theme.inverseOnSurfaceDarkHighContrast,
    inversePrimary = com.example.speechrecognitionapp.ui.theme.inversePrimaryDarkHighContrast,
    surfaceDim = com.example.speechrecognitionapp.ui.theme.surfaceDimDarkHighContrast,
    surfaceBright = com.example.speechrecognitionapp.ui.theme.surfaceBrightDarkHighContrast,
    surfaceContainerLowest = com.example.speechrecognitionapp.ui.theme.surfaceContainerLowestDarkHighContrast,
    surfaceContainerLow = com.example.speechrecognitionapp.ui.theme.surfaceContainerLowDarkHighContrast,
    surfaceContainer = com.example.speechrecognitionapp.ui.theme.surfaceContainerDarkHighContrast,
    surfaceContainerHigh = com.example.speechrecognitionapp.ui.theme.surfaceContainerHighDarkHighContrast,
    surfaceContainerHighest = com.example.speechrecognitionapp.ui.theme.surfaceContainerHighestDarkHighContrast,
)


@Immutable
data class ColorSpecs(
    val defaultLight: Color,
    val defaultDark: Color,
)

@Immutable
data class ExtendedColors(
    val extRed: ColorSpecs,
    val extOrange: ColorSpecs,
    val extYellow: ColorSpecs,
    val extGreen: ColorSpecs,
    val extMint: ColorSpecs,
    val extCyan: ColorSpecs,
    val extBlue: ColorSpecs,
    val extIndigo: ColorSpecs,
    val extPink: ColorSpecs,
    val extGray: ColorSpecs,
    val extGray2: ColorSpecs,
    val extGray3: ColorSpecs,
    val extGray4: ColorSpecs,
    val extGray5: ColorSpecs,
    val extGray6: ColorSpecs,
)

val LocalCustomExtColors = staticCompositionLocalOf {
    ExtendedColors(
        extRed = ColorSpecs(
            systemLightRed,
            systemDarkRed,
        ),
        extOrange = ColorSpecs(
            systemLightOrange,
            systemDarkOrange,
        ),
        extYellow = ColorSpecs(
            systemLightYellow,
            systemDarkYellow,
        ),
        extGreen = ColorSpecs(
            systemLightGreen,
            systemDarkGreen,
        ),
        extMint = ColorSpecs(
            systemLightMint,
            systemDarkMint,
        ),
        extCyan = ColorSpecs(
            systemLightCyan,
            systemDarkCyan,
        ),
        extBlue = ColorSpecs(
            systemLightBlue,
            systemDarkBlue,
        ),
        extIndigo = ColorSpecs(
            systemLightIndigo,
            systemDarkIndigo,
        ),
        extPink = ColorSpecs(
            systemLightPink,
            systemDarkPink,
        ),
        extGray = ColorSpecs(
            systemLightGray,
            systemDarkGray,
        ),
        extGray2 = ColorSpecs(
            systemLightGray2,
            systemDarkGray2,
        ),
        extGray3 = ColorSpecs(
            systemLightGray3,
            systemDarkGray3,
        ),
        extGray4 = ColorSpecs(
            systemLightGray4,
            systemDarkGray4,
        ),
        extGray5 = ColorSpecs(
            systemLightGray5,
            systemDarkGray5,
        ),
        extGray6 = ColorSpecs(
            systemLightGray6,
            systemDarkGray6,
        ),
    )
}

@Composable
fun SpeechRecognitionAppTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    // Dynamic color is available on Android 12+
    dynamicColor: Boolean = true,
    content: @Composable () -> Unit
) {


    val extendedColors = ExtendedColors(
        extRed = ColorSpecs(
            systemLightRed,
            systemDarkRed,
        ),
        extOrange = ColorSpecs(
            systemLightOrange,
            systemDarkOrange,
        ),
        extYellow = ColorSpecs(
            systemLightYellow,
            systemDarkYellow,
        ),
        extGreen = ColorSpecs(
            systemLightGreen,
            systemDarkGreen,
        ),
        extMint = ColorSpecs(
            systemLightMint,
            systemDarkMint,
        ),
        extCyan = ColorSpecs(
            systemLightCyan,
            systemDarkCyan,
        ),
        extBlue = ColorSpecs(
            systemLightBlue,
            systemDarkBlue,
        ),
        extIndigo = ColorSpecs(
            systemLightIndigo,
            systemDarkIndigo,
        ),
        extPink = ColorSpecs(
            systemLightPink,
            systemDarkPink,
        ),
        extGray = ColorSpecs(
            systemLightGray,
            systemDarkGray,
        ),
        extGray2 = ColorSpecs(
            systemLightGray2,
            systemDarkGray2,
        ),
        extGray3 = ColorSpecs(
            systemLightGray3,
            systemDarkGray3,
        ),
        extGray4 = ColorSpecs(
            systemLightGray4,
            systemDarkGray4,
        ),
        extGray5 = ColorSpecs(
            systemLightGray5,
            systemDarkGray5,
        ),
        extGray6 = ColorSpecs(
            systemLightGray6,
            systemDarkGray6,
        ),
    )


    /*     val colorScheme = when {
            dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
                val context = LocalContext.current
                if (darkTheme) dynamicDarkColorScheme(context) else dynamicLightColorScheme(context)
            }

            darkTheme -> DarkColors
            else -> LightColors
        } */
    val colorScheme = LightColors

    MaterialTheme(
        colorScheme = colorScheme,
        typography = MedTypography,
        shapes = Shapes,
        content = content
    )
}

object SpeechRecognitionAppTheme {

    val colors: ExtendedColors
        @Composable
        get() = LocalCustomExtColors.current

}