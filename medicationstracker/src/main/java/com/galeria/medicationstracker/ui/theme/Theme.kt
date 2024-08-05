package com.galeria.medicationstracker.ui.theme

import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext

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

private val DarkColorScheme = darkColorScheme(
    primary = Purple80,
    secondary = PurpleGrey80,
    tertiary = Pink80
)

private val LightColorScheme = lightColorScheme(
    primary = Purple40,
    secondary = PurpleGrey40,
    tertiary = Pink40

    /* Other default colors to override
    background = Color(0xFFFFFBFE),
    surface = Color(0xFFFFFBFE),
    onPrimary = Color.White,
    onSecondary = Color.White,
    onTertiary = Color.White,
    onBackground = Color(0xFF1C1B1F),
    onSurface = Color(0xFF1C1B1F),
    */
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
fun MedicationsTrackerAppTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    // Dynamic color is available on Android 12+
    dynamicColor: Boolean = false,
    content: @Composable () -> Unit
) {

    // region Extended colors.
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
    // endregion

    val colorScheme = when {
        dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
            val context = LocalContext.current
            if (darkTheme) dynamicDarkColorScheme(context) else dynamicLightColorScheme(context)
        }

        darkTheme -> DarkColorScheme
        else -> LightColorScheme
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = AppTypography,
        content = content
    )
}


object MedicationsTrackerAppTheme {

    val colors: ExtendedColors
        @Composable
        get() = LocalCustomExtColors.current

}