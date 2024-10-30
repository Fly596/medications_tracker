package com.galeria.medicationstracker.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ProvideTextStyle
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp

@Immutable
data class MedTrackerColors(
  val primary400: Color,
  val primary500: Color,
  val primary600: Color,
  val secondary400: Color,
  val secondary500: Color,
  val secondary600: Color,
  val primaryFill: Color, // for thin and small shapes, such as slider's track.
  val secondaryFill: Color, // for medium-size shapes, such as the background of a switch.
  val tertiaryFill: Color, // for large shapes, such as input fields, search bars, or buttons.
  val primaryBackground: Color,
  val secondaryBackground: Color,
  val tertiaryBackground: Color,
  // Containers.
  val primaryBackgroundGrouped: Color,
  val secondaryBackgroundGrouped: Color,
  val tertiaryBackgroundGrouped: Color,
  val primaryBackgroundDark: Color,
  val secondaryBackgroundDark: Color,
  val tertiaryBackgroundDark: Color,
  // Containers.
  val primaryGroupedBackgroundDark: Color,
  val secondaryGroupedBackgroundDark: Color,
  val tertiaryGroupedBackgroundDark: Color,

  // typo.
  val primaryLabel: Color,
  val secondaryLabel: Color,
  val tertiaryLabel: Color,
  val primaryLabelDark: Color,
  val secondaryLabelDark: Color,
  val tertiaryLabelDark: Color,

  // Separators.
  val separator: Color,
  val opaqueSeparator: Color,

  // Functional colors.
  val sysError: Color,
  val sysWarning: Color,
  val sysSuccess: Color,
)

@Immutable
data class MedTrackerTypography(
  val largeTitle: TextStyle,
  // Used for titles and headings that need to make a strong visual impact.
  val title1: TextStyle,
  // A major section heading, often used for top-level content within a page or screen.
  val title2: TextStyle,
  // A secondary heading, used to introduce subsections or groups of related content.
  val title3: TextStyle,
  // A tertiary heading, used to organize content within a subsection.

  // Body Text and Related Styles.
  val headline: TextStyle,
  // A short, attention-grabbing piece of text, often used for promotional or
  // call-to-action purposes.
  val body: TextStyle,
  // The main content of a page or screen, used for paragraphs and blocks of text.
  val callout: TextStyle,
  // Text that highlights important information or provides additional context.
  val subhead: TextStyle,
  // A smaller heading used to introduce a paragraph or section within the body
  // content.
  val footnote: TextStyle,
  // Small-sized text typically placed at the bottom of a page or screen for additional
  // information or credits.
  val caption1: TextStyle,
  // Descriptive text accompanying an image or graphic, often placed below the visual
  // content.
  val caption2: TextStyle,
  // Smaller caption text, used for additional details or metadata related to an image
  // or graphic.
)

// TODO: shapes

val LocalMedTrackerColors = staticCompositionLocalOf {
  MedTrackerColors(
    primary400 = Color.Unspecified,
    primary500 = Color.Unspecified,
    primary600 = Color.Unspecified,
    secondary400 = Color.Unspecified,
    secondary500 = Color.Unspecified,
    secondary600 = Color.Unspecified,
    primaryFill = Color.Unspecified, // for thin and small shapes, such as slider's track.
    secondaryFill =
    Color.Unspecified, // for medium-size shapes, such as the background of a switch.
    tertiaryFill =
    Color.Unspecified, // for large shapes, such as input fields, search bars, or buttons.
    primaryBackground = Color.Unspecified,
    secondaryBackground = Color.Unspecified,
    tertiaryBackground = Color.Unspecified,
    primaryBackgroundGrouped = Color.Unspecified,
    secondaryBackgroundGrouped = Color.Unspecified,
    tertiaryBackgroundGrouped = Color.Unspecified,
    primaryBackgroundDark = Color.Unspecified,
    secondaryBackgroundDark = Color.Unspecified,
    tertiaryBackgroundDark = Color.Unspecified,
    primaryGroupedBackgroundDark = Color.Unspecified,
    secondaryGroupedBackgroundDark = Color.Unspecified,
    tertiaryGroupedBackgroundDark = Color.Unspecified,
    primaryLabel = Color.Unspecified,
    secondaryLabel = Color.Unspecified,
    tertiaryLabel = Color.Unspecified,
    primaryLabelDark = Color.Unspecified,
    secondaryLabelDark = Color.Unspecified,
    tertiaryLabelDark = Color.Unspecified,
    separator = Color.Unspecified,
    opaqueSeparator = Color.Unspecified,
    sysError = Color.Unspecified,
    sysWarning = Color.Unspecified,
    sysSuccess = Color.Unspecified,
  )
}

val LocalMedTrackerTypography = staticCompositionLocalOf {
  MedTrackerTypography(
    largeTitle = TextStyle.Default,
    title1 = TextStyle.Default,
    title2 = TextStyle.Default,
    title3 = TextStyle.Default,
    headline = TextStyle.Default,
    body = TextStyle.Default,
    callout = TextStyle.Default,
    subhead = TextStyle.Default,
    footnote = TextStyle.Default,
    caption1 = TextStyle.Default,
    caption2 = TextStyle.Default,
  )
}

@Composable
fun MedTrackerTheme(
  darkTheme: Boolean = isSystemInDarkTheme(),
  // Dynamic color is available on Android 12+
  dynamicColor: Boolean = false,
  content: @Composable () -> Unit,
) {
  val medTrackerColors =
    MedTrackerColors(
      primary400,
      primary500,
      primary600,
      secondary400,
      secondary500,
      secondary600,
      primaryFill,
      secondaryFill,
      tertiaryFill,
      primaryBackground,
      secondaryBackground,
      tertiaryBackground,
      primaryBackgroundGrouped,
      secondaryBackgroundGrouped,
      tertiaryBackgroundGrouped,
      primaryBackgroundDark,
      secondaryBackgroundDark,
      tertiaryBackgroundDark,
      primaryGroupedBackgroundDark,
      secondaryGroupedBackgroundDark,
      tertiaryGroupedBackgroundDark,
      primaryLabel,
      secondaryLabel,
      tertiaryLabel,
      primaryLabelDark,
      secondaryLabelDark,
      tertiaryLabelDark,
      separator,
      opaqueSeparator,
      sysError,
      sysWarning,
      sysSuccess,
    )

  val medTrackerTypography =
    MedTrackerTypography(
      largeTitle =
      TextStyle(
        fontSize = 34.sp,
        fontWeight = FontWeight.Normal,
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

  CompositionLocalProvider(
    LocalMedTrackerColors provides medTrackerColors,
    LocalMedTrackerTypography provides medTrackerTypography,
    content = content,
  )
}

object MedTrackerTheme {

  val colors: MedTrackerColors
    @Composable get() = LocalMedTrackerColors.current

  val typography: MedTrackerTypography
    @Composable get() = LocalMedTrackerTypography.current
}

@Composable
fun MedTrackerButton(
  onClick: () -> Unit,
  modifier: Modifier = Modifier,
  content: @Composable RowScope.() -> Unit
) {
  Button(
    colors = ButtonDefaults.buttonColors(
      containerColor = MedTrackerTheme.colors.primary400,
      contentColor = MedTrackerTheme.colors.primaryLabelDark,
      disabledContainerColor = MedTrackerTheme.colors.primaryFill,
      disabledContentColor = MedTrackerTheme.colors.secondaryLabelDark

    ),
    shape = ButtonShape,
    onClick = onClick,
    modifier = modifier,
    content = {
      ProvideTextStyle(
        value = MedTrackerTheme.typography.body
      ) {
        content()
      }
    }
  )
}

val ButtonShape = RoundedCornerShape(percent = 50)