package com.galeria.medicationstracker.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontVariation
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp

@Immutable
data class RepTypography(

    // Used for titles and headings that need to make a strong visual impact.
    val largeTitle: TextStyle,
    val largeTitleEmphasized: TextStyle,

    // A major section heading, often used for top-level content within a page or screen.
    val title1: TextStyle,
    val title1Emphasized: TextStyle,

    // A secondary heading, used to introduce subsections or groups of related content.
    val title2: TextStyle,
    val title2Emphasized: TextStyle,

    // A tertiary heading, used to organize content within a subsection.
    val title3: TextStyle,
    val title3Emphasized: TextStyle,

    // Body Text and Related Styles.

    // A short, attention-grabbing piece of text, often used for promotional or call-to-action purposes.
    val headline: TextStyle,
    val headlineItalic: TextStyle,

    // The main content of a page or screen, used for paragraphs and blocks of text.
    val body: TextStyle,
    val bodyEmphasized: TextStyle,
    val bodyItalic: TextStyle,
    val bodyEmphasizedItalic: TextStyle,

    // Text that highlights important information or provides additional context.
    val callout: TextStyle,
    val calloutEmphasized: TextStyle,
    val calloutItalic: TextStyle,
    val calloutEmphasizedItalic: TextStyle,

    // A smaller heading used to introduce a paragraph or section within the body content.
    val subhead: TextStyle,
    val subheadEmphasized: TextStyle,
    val subheadItalic: TextStyle,
    val subheadEmphasizedItalic: TextStyle,

    // Small-sized text typically placed at the bottom of a page or screen for additional information or credits.
    val footnote: TextStyle,
    val footnoteEmphasized: TextStyle,
    val footnoteItalic: TextStyle,
    val footnoteEmphasizedItalic: TextStyle,

    // Descriptive text accompanying an image or graphic, often placed below the visual content.
    val caption1: TextStyle,
    val caption1Emphasized: TextStyle,
    val caption1Italic: TextStyle,
    val caption1EmphasizedItalic: TextStyle,

    // Smaller caption text, used for additional details or metadata related to an image or graphic.
    val caption2: TextStyle,
    val caption2Emphasized: TextStyle,
    val caption2Italic: TextStyle,
    val caption2EmphasizedItalic: TextStyle
)

val LocalRepAppTypography = staticCompositionLocalOf {
    RepTypography(
        largeTitle = TextStyle.Default,
        largeTitleEmphasized = TextStyle.Default,
        title1 = TextStyle.Default,
        title1Emphasized = TextStyle.Default,
        title2 = TextStyle.Default,
        title2Emphasized = TextStyle.Default,
        title3 = TextStyle.Default,
        title3Emphasized = TextStyle.Default,
        headline = TextStyle.Default,
        headlineItalic = TextStyle.Default,
        body = TextStyle.Default,
        bodyEmphasized = TextStyle.Default,
        bodyItalic = TextStyle.Default,
        bodyEmphasizedItalic = TextStyle.Default,
        callout = TextStyle.Default,
        calloutEmphasized = TextStyle.Default,
        calloutItalic = TextStyle.Default,
        calloutEmphasizedItalic = TextStyle.Default,
        subhead = TextStyle.Default,
        subheadEmphasized = TextStyle.Default,
        subheadItalic = TextStyle.Default,
        subheadEmphasizedItalic = TextStyle.Default,
        footnote = TextStyle.Default,
        footnoteEmphasized = TextStyle.Default,
        footnoteItalic = TextStyle.Default,
        footnoteEmphasizedItalic = TextStyle.Default,
        caption1 = TextStyle.Default,
        caption1Emphasized = TextStyle.Default,
        caption1Italic = TextStyle.Default,
        caption1EmphasizedItalic = TextStyle.Default,
        caption2 = TextStyle.Default,
        caption2Emphasized = TextStyle.Default,
        caption2Italic = TextStyle.Default,
        caption2EmphasizedItalic = TextStyle.Default
    )
}

val RepAppTypography = RepTypography(
    largeTitle = TextStyle(
        fontSize = 34.sp,
        fontWeight = FontWeight.Normal,
        fontFamily = FontFamily.Default,
        lineHeight = 41.sp,
        // color = Color.Black
    ),
    largeTitleEmphasized = TextStyle(
        fontSize = 34.sp,
        fontWeight = FontWeight.Bold,
        fontFamily = FontFamily.Default,
        lineHeight = 41.sp
    ),
    title1 = TextStyle(
        fontSize = 28.sp,
        fontWeight = FontWeight.Normal,
        fontFamily = FontFamily.Default,
        lineHeight = 34.sp,
        // color = Color.Black
    ),
    title1Emphasized = TextStyle(
        fontSize = 28.sp,
        fontWeight = FontWeight.Bold,
        fontFamily = FontFamily.Default,
        lineHeight = 34.sp
    ),

    title2 = TextStyle(
        fontSize = 22.sp,
        fontWeight = FontWeight.Normal,
        fontFamily = FontFamily.Default,
        lineHeight = 28.sp,
        // color = Color.Black
    ),
    title2Emphasized = TextStyle(
        fontSize = 22.sp,
        fontWeight = FontWeight.Bold,
        fontFamily = FontFamily.Default,
        lineHeight = 28.sp
    ),

    title3 = TextStyle(
        fontSize = 20.sp,
        fontWeight = FontWeight.Normal,
        fontFamily = FontFamily.Default,
        lineHeight = 25.sp,
        // color = Color.Black
    ),
    title3Emphasized = TextStyle(
        fontSize = 20.sp,
        fontWeight = FontWeight.Bold,
        fontFamily = FontFamily.Default,
        lineHeight = 25.sp
    ),

    headline = TextStyle(
        fontSize = 17.sp,
        fontWeight = FontWeight.SemiBold,
        fontFamily = FontFamily.Default,
        lineHeight = 22.sp,
        // color = Color.Black
    ),
    headlineItalic = TextStyle(
        fontSize = 17.sp,
        fontWeight = FontWeight.Normal,
        fontFamily = FontFamily.Default,
        fontStyle = FontStyle.Italic,
        lineHeight = 22.sp,
        // color = Color.Black
    ),

    body = TextStyle(
        fontSize = 16.sp,
        fontWeight = FontWeight.Normal,
        fontFamily = FontFamily.Default,
        lineHeight = 22.sp,
        // color = Color.Black
    ),
    bodyEmphasized = TextStyle(
        fontSize = 17.sp,
        fontWeight = FontWeight.SemiBold,
        fontFamily = FontFamily.Default,
        lineHeight = 22.sp
    ),
    bodyItalic = TextStyle(
        fontSize = 17.sp,
        fontWeight = FontWeight.Normal,
        fontFamily = FontFamily.Default,
        fontStyle = FontStyle.Italic,
        lineHeight = 22.sp,
    ),
    bodyEmphasizedItalic = TextStyle(
        fontSize = 17.sp,
        fontWeight = FontWeight.SemiBold,
        fontFamily = FontFamily.Default,
        fontStyle = FontStyle.Italic
    ),

    callout = TextStyle(
        fontSize = 16.sp,
        fontWeight = FontWeight.Normal,
        fontFamily = FontFamily.Default,
        lineHeight = 21.sp,
        // color = Color.Black
    ),
    calloutEmphasized = TextStyle(
        fontSize = 16.sp,
        fontWeight = FontWeight.SemiBold,
        fontFamily = FontFamily.Default,
        lineHeight = 21.sp
    ),
    calloutItalic = TextStyle(
        fontSize = 16.sp,
        fontWeight = FontWeight.Normal,
        fontFamily = FontFamily.Default,
        fontStyle = FontStyle.Italic,
    ),
    calloutEmphasizedItalic = TextStyle(
        fontSize = 16.sp,
        fontWeight = FontWeight.SemiBold,
        fontFamily = FontFamily.Default,
        fontStyle = FontStyle.Italic
    ),


    subhead = TextStyle(
        fontSize = 15.sp,
        fontWeight = FontWeight.Normal,
        fontFamily = FontFamily.Default,
        lineHeight = 20.sp,
        // color = Color.Black
    ),
    subheadEmphasized = TextStyle(
        fontSize = 15.sp,
        fontWeight = FontWeight.SemiBold,
        fontFamily = FontFamily.Default,
        lineHeight = 20.sp
    ),
    subheadItalic = TextStyle(
        fontSize = 15.sp,
        fontWeight = FontWeight.Normal,
        fontFamily = FontFamily.Default,
        fontStyle = FontStyle.Italic,
        lineHeight = 20.sp,
    ),
    subheadEmphasizedItalic = TextStyle(
        fontSize = 15.sp,
        fontWeight = FontWeight.SemiBold,
        fontFamily = FontFamily.Default,
        fontStyle = FontStyle.Italic,
    ),


    footnote = TextStyle(
        fontSize = 13.sp,
        fontWeight = FontWeight.Normal,
        fontFamily = FontFamily.Default,
        lineHeight = 18.sp,
        // color = Color.Black
    ),
    footnoteEmphasized = TextStyle(
        fontSize = 13.sp,
        fontWeight = FontWeight.SemiBold,
        fontFamily = FontFamily.Default,
        lineHeight = 18.sp
    ),
    footnoteItalic = TextStyle(
        fontSize = 13.sp,
        fontWeight = FontWeight.Normal,
        fontFamily = FontFamily.Default,
        fontStyle = FontStyle.Italic,
        lineHeight = 18.sp,
    ),
    footnoteEmphasizedItalic = TextStyle(
        fontSize = 13.sp,
        fontWeight = FontWeight.SemiBold,
        fontFamily = FontFamily.Default,
        fontStyle = FontStyle.Italic,
    ),


    caption1 = TextStyle(
        fontSize = 12.sp,
        fontWeight = FontWeight.Normal,
        fontFamily = FontFamily.Default,
        lineHeight = 16.sp,
        // color = Color.Black
    ),
    caption1Emphasized = TextStyle(
        fontSize = 12.sp,
        fontWeight = FontWeight.SemiBold,
        fontFamily = FontFamily.Default,
        lineHeight = 16.sp
    ),
    caption1Italic = TextStyle(
        fontSize = 12.sp,
        fontWeight = FontWeight.Normal,
        fontFamily = FontFamily.Default,
        fontStyle = FontStyle.Italic,
        lineHeight = 16.sp,
    ),
    caption1EmphasizedItalic = TextStyle(
        fontSize = 12.sp,
        fontWeight = FontWeight.SemiBold,
        fontFamily = FontFamily.Default,
        fontStyle = FontStyle.Italic,
    ),

    caption2 = TextStyle(
        fontSize = 11.sp,
        fontWeight = FontWeight.Normal,
        fontFamily = FontFamily.Default,
        lineHeight = 13.sp,
        // color = Color.Black
    ),
    caption2Emphasized = TextStyle(
        fontSize = 11.sp,
        fontWeight = FontWeight.SemiBold,
        fontFamily = FontFamily.Default,
        lineHeight = 13.sp
    ),
    caption2Italic = TextStyle(
        fontSize = 11.sp,
        fontWeight = FontWeight.Normal,
        fontFamily = FontFamily.Default,
        fontStyle = FontStyle.Italic,
        lineHeight = 13.sp,
    ),
    caption2EmphasizedItalic = TextStyle(
        fontSize = 11.sp,
        fontWeight = FontWeight.SemiBold,
        fontFamily = FontFamily.Default,
        fontStyle = FontStyle.Italic,
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