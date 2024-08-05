package com.galeria.medicationstracker.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp


@Immutable
data class RepTypography(
    val largeTitle: TextStyle, // Used for titles and headings that need to make a strong visual impact.
    val title1: TextStyle, // A major section heading, often used for top-level content within a page or screen.
    val title2: TextStyle, // A secondary heading, used to introduce subsections or groups of related content.
    val title3: TextStyle,  // A tertiary heading, used to organize content within a subsection.

    // Body Text and Related Styles.
    val headline: TextStyle, // A short, attention-grabbing piece of text, often used for promotional or call-to-action purposes.
    val body: TextStyle, // The main content of a page or screen, used for paragraphs and blocks of text.
    val callout: TextStyle, // Text that highlights important information or provides additional context.
    val subhead: TextStyle, // A smaller heading used to introduce a paragraph or section within the body content.
    val footnote: TextStyle, // Small-sized text typically placed at the bottom of a page or screen for additional information or credits.

    val caption1: TextStyle, // Descriptive text accompanying an image or graphic, often placed below the visual content.
    val caption2: TextStyle, // Smaller caption text, used for additional details or metadata related to an image or graphic.
)

val LocalRepAppTypography = staticCompositionLocalOf{
    RepTypography(
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
        caption2 = TextStyle.Default
    )
}

val RepAppTypography = RepTypography(
    largeTitle = TextStyle(
        fontSize = 34.sp,
        fontWeight = FontWeight.Medium,
        fontFamily = FontFamily.Default,
        lineHeight = 41.sp,
        // color = Color.Black
    ),
    title1 = TextStyle(
        fontSize = 28.sp,
        fontWeight = FontWeight.Normal,
        fontFamily = FontFamily.Default,
        lineHeight = 34.sp,
        // color = Color.Black
    ),
    title2 = TextStyle(
        fontSize = 22.sp,
        fontWeight = FontWeight.Normal,
        fontFamily = FontFamily.Default,
        lineHeight = 28.sp,
        // color = Color.Black
    ),
    title3 = TextStyle(
        fontSize = 20.sp,
        fontWeight = FontWeight.Normal,
        fontFamily = FontFamily.Default,
        lineHeight = 25.sp,
        // color = Color.Black
    ),
    headline = TextStyle(
        fontSize = 17.sp,
        fontWeight = FontWeight.SemiBold,
        fontFamily = FontFamily.Default,
        lineHeight = 22.sp,
        // color = Color.Black
    ),
    body = TextStyle(
        fontSize = 17.sp,
        fontWeight = FontWeight.Normal,
        fontFamily = FontFamily.Default,
        lineHeight = 22.sp,
        // color = Color.Black
    ),
    callout = TextStyle(
        fontSize = 16.sp,
        fontWeight = FontWeight.Normal,
        fontFamily = FontFamily.Default,
        lineHeight = 21.sp,
        // color = Color.Black
    ),
    subhead = TextStyle(
        fontSize = 15.sp,
        fontWeight = FontWeight.Normal,
        fontFamily = FontFamily.Default,
        lineHeight = 20.sp,
        // color = Color.Black
    ),
    footnote = TextStyle(
        fontSize = 13.sp,
        fontWeight = FontWeight.Normal,
        fontFamily = FontFamily.Default,
        lineHeight = 18.sp,
        // color = Color.Black
    ),
    caption1 = TextStyle(
        fontSize = 12.sp,
        fontWeight = FontWeight.Normal,
        fontFamily = FontFamily.Default,
        lineHeight = 16.sp,
        // color = Color.Black
    ),
    caption2 = TextStyle(
        fontSize = 11.sp,
        fontWeight = FontWeight.Normal,
        fontFamily = FontFamily.Default,
        lineHeight = 13.sp,
        // color = Color.Black
    )
)

// Set of Material typography styles to start with
val AppTypography = Typography(
    displayLarge = TextStyle(
        fontSize = 34.sp,
        fontWeight = FontWeight.Medium,
        fontFamily = FontFamily.Default,
        lineHeight = 41.sp
    ),
    displayMedium = TextStyle(
        fontSize = 28.sp,
        fontWeight = FontWeight.Normal,
        fontFamily = FontFamily.Default,
        lineHeight = 34.sp
    ),
    displaySmall = TextStyle(
        fontSize = 22.sp,
        fontWeight = FontWeight.Normal,
        fontFamily = FontFamily.Default,
        lineHeight = 28.sp
    ),
    headlineLarge = TextStyle(
        fontSize = 20.sp,
        fontWeight = FontWeight.Normal,
        fontFamily = FontFamily.Default,
        lineHeight = 25.sp
    ),
    headlineMedium = TextStyle(
        fontSize = 17.sp,
        fontWeight = FontWeight.SemiBold,
        fontFamily = FontFamily.Default,
        lineHeight = 22.sp
    ),
    bodyLarge = TextStyle(
        fontSize = 17.sp,
        fontWeight = FontWeight.Normal,
        fontFamily = FontFamily.Default,
        lineHeight = 22.sp
    ),
    bodyMedium = TextStyle(
        fontSize = 16.sp,
        fontWeight = FontWeight.Normal,
        fontFamily = FontFamily.Default,
        lineHeight = 21.sp
    ),
    bodySmall = TextStyle(
        fontSize = 15.sp,
        fontWeight = FontWeight.Normal,
        fontFamily = FontFamily.Default,
        lineHeight = 20.sp
    ),
    labelLarge = TextStyle(
        fontSize = 13.sp,
        fontWeight = FontWeight.Normal,
        fontFamily = FontFamily.Default,
        lineHeight = 18.sp
    ),
    labelMedium = TextStyle(
        fontSize = 12.sp,
        fontWeight = FontWeight.Normal,
        fontFamily = FontFamily.Default,
        lineHeight = 16.sp
    ),
    labelSmall = TextStyle(
        fontSize = 11.sp,
        fontWeight = FontWeight.Normal,
        fontFamily = FontFamily.Default,
        lineHeight = 13.sp,
    )
)