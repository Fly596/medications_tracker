package com.galeria.medicationstracker.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp

private val DarkColorScheme =
  darkColorScheme(primary = Purple80, secondary = PurpleGrey80, tertiary = Pink80)

private val LightColorScheme =
  lightColorScheme(
    primary = Purple40,
    secondary = PurpleGrey40,
    tertiary = Pink40,

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

@Composable
fun MedicationsTrackerAppTheme(
  darkTheme: Boolean = isSystemInDarkTheme(),
  // Dynamic color is available on Android 12+
  dynamicColor: Boolean = false,
  content: @Composable () -> Unit,
) {

  val replacementTypography =
    RepTypography(
      largeTitle =
      TextStyle(
        fontSize = 34.sp,
        fontWeight = FontWeight.Medium,
        fontFamily = FontFamily.Default,
        lineHeight = 41.sp,
        // color = Color.Black
      ),
      title1 =
      TextStyle(
        fontSize = 28.sp,
        fontWeight = FontWeight.Normal,
        fontFamily = FontFamily.Default,
        lineHeight = 34.sp,
        // color = Color.Black
      ),
      title2 =
      TextStyle(
        fontSize = 22.sp,
        fontWeight = FontWeight.Normal,
        fontFamily = FontFamily.Default,
        lineHeight = 28.sp,
        // color = Color.Black
      ),
      title3 =
      TextStyle(
        fontSize = 20.sp,
        fontWeight = FontWeight.Normal,
        fontFamily = FontFamily.Default,
        lineHeight = 25.sp,
        // color = Color.Black
      ),
      headline =
      TextStyle(
        fontSize = 17.sp,
        fontWeight = FontWeight.SemiBold,
        fontFamily = FontFamily.Default,
        lineHeight = 22.sp,
        // color = Color.Black
      ),
      body =
      TextStyle(
        fontSize = 17.sp,
        fontWeight = FontWeight.Normal,
        fontFamily = FontFamily.Default,
        lineHeight = 22.sp,
        // color = Color.Black
      ),
      callout =
      TextStyle(
        fontSize = 16.sp,
        fontWeight = FontWeight.Normal,
        fontFamily = FontFamily.Default,
        lineHeight = 21.sp,
        // color = Color.Black
      ),
      subhead =
      TextStyle(
        fontSize = 15.sp,
        fontWeight = FontWeight.Normal,
        fontFamily = FontFamily.Default,
        lineHeight = 20.sp,
        // color = Color.Black
      ),
      footnote =
      TextStyle(
        fontSize = 13.sp,
        fontWeight = FontWeight.Normal,
        fontFamily = FontFamily.Default,
        lineHeight = 18.sp,
        // color = Color.Black
      ),
      caption1 =
      TextStyle(
        fontSize = 12.sp,
        fontWeight = FontWeight.Normal,
        fontFamily = FontFamily.Default,
        lineHeight = 16.sp,
        // color = Color.Black
      ),
      caption2 =
      TextStyle(
        fontSize = 11.sp,
        fontWeight = FontWeight.Normal,
        fontFamily = FontFamily.Default,
        lineHeight = 13.sp,
        // color = Color.Black
      ),
    )

  val replacementSystemColors =
    AppSystemColors(
      backgroundPrimary = backgroundPrimaryLight,
      backgroundSecondary = backgroundSecondaryLight,
      backgroundTertiary = backgroundTertiaryLight,
      backgroundPrimaryGrouped = backgroundPrimaryGroupedLight,
      backgroundSecondaryGrouped = backgroundSecondaryGroupedLight,
      backgroundTertiaryGrouped = backgroundTertiaryGroupedLight,
      separatorOpaque = separatorOpaque,
      separatorNonOpaque = separatorNonOpaque,
      labelPrimary = labelPrimary,
      labelSecondary = labelSecondary,
      labelTertiary = labelTertiary,
      labelQuaternary = labelQuaternary,
    )

  val extendedColorsLight =
    ExtendedColors(
      extRed = systemLightRed,
      extOrange = systemLightOrange,
      extYellow = systemLightYellow,
      extGreen = systemLightGreen,
      extMint = systemLightMint,
      extCyan = systemLightCyan,
      extBlue = systemLightBlue,
      extIndigo = systemLightIndigo,
      extPink = systemLightPink,
      extGray = systemLightGray,
      extGray2 = systemLightGray2,
      extGray3 = systemLightGray3,
      extGray4 = systemLightGray4,
      extGray5 = systemLightGray5,
      extGray6 = systemLightGray6,
      extWhite = Color.White,
      extBlack = Color.Black,
    )

  CompositionLocalProvider(
    LocalRepAppTypography provides replacementTypography,
    LocalLightSystemColors provides replacementSystemColors,
    LocalExtColorsLight provides extendedColorsLight,
    content = content,
  )
  /*    val colorScheme =
  when {
      dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
          val context = LocalContext.current
          if (darkTheme) dynamicDarkColorScheme(context) else dynamicLightColorScheme(context)
      }

      darkTheme -> DarkColorScheme
      else -> LightColorScheme
  }*/

  // MaterialTheme(/*colorScheme = colorScheme, typography = AppTypography, */content = content)
}

object MedicationsTrackerAppTheme {

  val extendedTypography: RepTypography
    @Composable get() = LocalRepAppTypography.current

  val extendedColorsLight: ExtendedColors
    @Composable get() = LocalExtColorsLight.current

  /*    val extendedColorsDark: ExtendedColors
  @Composable get() = ExtColorsDark.current*/

  val systemColorsLight: AppSystemColors
    @Composable get() = LocalLightSystemColors.current
}
