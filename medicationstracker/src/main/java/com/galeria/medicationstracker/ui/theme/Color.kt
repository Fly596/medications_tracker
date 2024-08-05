package com.galeria.medicationstracker.ui.theme

import androidx.compose.runtime.Immutable
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Color

val Purple80 = Color(0xFFD0BCFF)
val PurpleGrey80 = Color(0xFFCCC2DC)
val Pink80 = Color(0xFFEFB8C8)

val Purple40 = Color(0xFF6650a4)
val PurpleGrey40 = Color(0xFF625b71)
val Pink40 = Color(0xFF7D5260)


val systemLightRed = Color(0xFFff3b30)
val systemLightOrange = Color(0xFFff9500)
val systemLightYellow = Color(0xFFffcc00)
val systemLightGreen = Color(0xFF34c759)
val systemLightMint = Color(0xFF00c7be)
val systemLightCyan = Color(0xFF32ade6)
val systemLightBlue = Color(0xFF007aff)
val systemLightIndigo = Color(0xFF5856d6)
val systemLightPink = Color(0xFFff2d55)
val systemLightGray = Color(0xFF8e8e93)
val systemLightGray2 = Color(0xFFaeaeb2)
val systemLightGray3 = Color(0xFFc7c7cc)
val systemLightGray4 = Color(0xFFd1d1d6)
val systemLightGray5 = Color(0xFFe5e5ea)
val systemLightGray6 = Color(0xFFf2f2f7)

val systemDarkRed = Color(0xFFff453a)
val systemDarkOrange = Color(0xFFff9f0a)
val systemDarkYellow = Color(0xFFffd60a)
val systemDarkGreen = Color(0xFF30d158)
val systemDarkMint = Color(0xFF66d4cf)
val systemDarkCyan = Color(0xFF64d2ff)
val systemDarkBlue = Color(0xFF0a84ff)
val systemDarkIndigo = Color(0xFF5e5ce6)
val systemDarkPink = Color(0xFFff375f)
val systemDarkGray = Color(0xFF8e8e93)
val systemDarkGray2 = Color(0xFF636366)
val systemDarkGray3 = Color(0xFF48484a)
val systemDarkGray4 = Color(0xFF3a3a3c)
val systemDarkGray5 = Color(0xFF2c2c2e)
val systemDarkGray6 = Color(0xFF1c1c1e)

val backgroundLightPrimary = Color(0xFFFFFFFF)
val backgroundLightSecondary = Color(0xFFF2F2F7)
val backgroundLightTertiary = Color(0xFFFFFFFF)

// Containers.
val backgroundLightPrimaryGrouped = Color(0xFFf2f2f7)
val backgroundLightSecondaryGrouped = Color(0xFFffffff)
val backgroundLightTertiaryGrouped = Color(0xFFf2f2f7)

// Separators.
val separatorOpaque = Color(0xFFC6C6C8)
val separatorNonOpaque = Color(0x57545456)


@Immutable
data class RepExtendedColors(
    val backgroundLightPrimary: Color,
    val backgroundLightSecondary: Color,
    val backgroundLightTertiary: Color,
    val backgroundLightPrimaryGrouped: Color,
    val backgroundLightSecondaryGrouped: Color,
    val backgroundLightTertiaryGrouped: Color,
    val separatorOpaque: Color,
    val separatorNonOpaque: Color,
)

val LightRepCustomColors = staticCompositionLocalOf {
    RepExtendedColors(
        backgroundLightPrimary = backgroundLightPrimary,
        backgroundLightSecondary = backgroundLightSecondary,
        backgroundLightTertiary = backgroundLightTertiary,
        backgroundLightPrimaryGrouped = backgroundLightPrimaryGrouped,
        backgroundLightSecondaryGrouped = backgroundLightSecondaryGrouped,
        backgroundLightTertiaryGrouped = backgroundLightTertiaryGrouped,
        separatorOpaque = separatorOpaque,
        separatorNonOpaque = separatorNonOpaque,
    )
}


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
    val extWhite: ColorSpecs,
    val extBlack: ColorSpecs,
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
        extWhite = ColorSpecs(
            Color.White,
            Color.White,
        ),
        extBlack = ColorSpecs(
            Color.Black,
            Color.Black,
        ),
    )
}

