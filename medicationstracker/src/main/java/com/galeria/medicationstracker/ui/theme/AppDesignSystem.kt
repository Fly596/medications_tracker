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

@Immutable
data class MedTrackerColors(
  val primary400: Color,
  val primary500: Color,
  val primary600: Color,
  val primaryTinted: Color,
  val secondary400: Color,
  val secondary500: Color,
  val secondary600: Color,
  val secondaryTinted: Color,
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
  val sysWhite: Color,
  val sysBlack: Color,
  val sysTransparent: Color,
)

@Immutable
data class MedTrackerTypography(
  val largeTitle:
    TextStyle, // Used for titles and headings that need to make a strong visual impact.
  val largeTitleEmphasized: TextStyle,
  val title1:
    TextStyle, // A major section heading, often used for top-level content within a page or screen.
  val title1Emphasized:
    TextStyle, // A major section heading, often used for top-level content within a page or screen.
  val title2:
    TextStyle, // A secondary heading, used to introduce subsections or groups of related content.
  val title2Emphasized:
    TextStyle, // A secondary heading, used to introduce subsections or groups of related content.
  val title3: TextStyle, // A tertiary heading, used to organize content within a subsection.
  val title3Emphasized:
    TextStyle, // A tertiary heading, used to organize content within a subsection.

  // Body Text and Related Styles.
  val headline:
    TextStyle, // A short, attention-grabbing piece of text, often used for promotional or
  // call-to-action purposes.
  val headlineItalic:
    TextStyle, // A short, attention-grabbing piece of text, often used for promotional or
  // call-to-action purposes.
  val body:
    TextStyle, // The main content of a page or screen, used for paragraphs and blocks of text.
  val bodyEmphasized:
    TextStyle, // The main content of a page or screen, used for paragraphs and blocks of text.
  val callout:
    TextStyle, // Text that highlights important information or provides additional context.
  val calloutEmphasized:
    TextStyle, // Text that highlights important information or provides additional context.
  val subhead:
    TextStyle, // A smaller heading used to introduce a paragraph or section within the body
  // content.
  val subheadEmphasized:
    TextStyle, // A smaller heading used to introduce a paragraph or section within the body
  // content.
  val footnote:
    TextStyle, // Small-sized text typically placed at the bottom of a page or screen for additional
  // information or credits.
  val footnoteEmphasized:
    TextStyle, // Small-sized text typically placed at the bottom of a page or screen for additional
  // information or credits.
  val caption1:
    TextStyle, // Descriptive text accompanying an image or graphic, often placed below the visual
  // content.
  val caption1Emphasized:
    TextStyle, // Descriptive text accompanying an image or graphic, often placed below the visual
  // content.
  val caption2:
    TextStyle, // Smaller caption text, used for additional details or metadata related to an image
  // or graphic.
  val caption2Emphasized:
    TextStyle, // Smaller caption text, used for additional details or metadata related to an image
  // or graphic.
)

// TODO: shapes

val LocalMedTrackerColors = staticCompositionLocalOf {
  MedTrackerColors(
    primary400 = Color.Unspecified,
    primary500 = Color.Unspecified,
    primary600 = Color.Unspecified,
    primaryTinted = Color.Unspecified,
    secondary400 = Color.Unspecified,
    secondary500 = Color.Unspecified,
    secondary600 = Color.Unspecified,
    secondaryTinted = Color.Unspecified,
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
    sysWhite = Color.Unspecified,
    sysBlack = Color.Unspecified,
    sysTransparent = Color.Unspecified,
  )
}

val LocalMedTrackerTypography = staticCompositionLocalOf {
  MedTrackerTypography(
    // Used for titles and headings that need to make a strong visual impact.
    largeTitle =
      TextStyle.Default, // Used for titles and headings that need to make a strong visual impact.
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
    largeTitleEmphasized =
      TextStyle.Default, // Used for titles and headings that need to make a strong visual impact.
    title1Emphasized = TextStyle.Default,
    title2Emphasized = TextStyle.Default,
    title3Emphasized = TextStyle.Default,
    headlineItalic = TextStyle.Default,
    bodyEmphasized = TextStyle.Default,
    calloutEmphasized = TextStyle.Default,
    subheadEmphasized = TextStyle.Default,
    footnoteEmphasized = TextStyle.Default,
    caption1Emphasized = TextStyle.Default,
    caption2Emphasized = TextStyle.Default,
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
      primaryTinted,
      secondary400,
      secondary500,
      secondary600,
      secondaryTinted,
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
      sysWhite,
      sysBlack,
      sysTransparent,
    )

  val medTrackerTypography = AppTypography

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
  content: @Composable RowScope.() -> Unit,
) {
  Button(
    colors =
      ButtonDefaults.buttonColors(
        containerColor = MedTrackerTheme.colors.primary400,
        contentColor = MedTrackerTheme.colors.primaryLabelDark,
        disabledContainerColor = MedTrackerTheme.colors.primaryFill,
        disabledContentColor = MedTrackerTheme.colors.secondaryLabelDark,
      ),
    shape = ButtonShape,
    onClick = onClick,
    modifier = modifier,
    content = { ProvideTextStyle(value = MedTrackerTheme.typography.body) { content() } },
  )
}

val ButtonShape = RoundedCornerShape(percent = 50)
