package com.galeria.medicationstracker.ui.theme

import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp

// Used for titles and headings that need to make a strong visual impact.
/* val largeTitle = TextStyle(
  fontSize = 34.sp,
  fontWeight = FontWeight.W600,
  fontFamily = FontFamily.Default,
  lineHeight = 41.sp,
) // Used for titles and headings that need to make a strong visual impact.

val title1 =
  TextStyle(
    fontSize = 28.sp,
    fontWeight = FontWeight.W600,
    fontFamily = FontFamily.Default,
    lineHeight = 34.sp,
// color = Color.Black
  )
val title2 =
  TextStyle(
    fontSize = 22.sp,
    fontWeight = FontWeight.Normal,
    fontFamily = FontFamily.Default,
    lineHeight = 28.sp,
// color = Color.Black
  )
val title3 =
  TextStyle(
    fontSize = 20.sp,
    fontWeight = FontWeight.Normal,
    fontFamily = FontFamily.Default,
    lineHeight = 25.sp,
// color = Color.Black
  )
val headline =
  TextStyle(
    fontSize = 17.sp,
    fontWeight = FontWeight.SemiBold,
    fontFamily = FontFamily.Default,
    lineHeight = 22.sp,
// color = Color.Black
  )
val body =
  TextStyle(
    fontSize = 17.sp,
    fontWeight = FontWeight.Normal,
    fontFamily = FontFamily.Default,
    lineHeight = 22.sp,
// color = Color.Black
  )
val callout =
  TextStyle(
    fontSize = 16.sp,
    fontWeight = FontWeight.Normal,
    fontFamily = FontFamily.Default,
    lineHeight = 21.sp,
// color = Color.Black
  )
val subhead =
  TextStyle(
    fontSize = 15.sp,
    fontWeight = FontWeight.Normal,
    fontFamily = FontFamily.Default,
    lineHeight = 20.sp,
// color = Color.Black
  )
val footnote =
  TextStyle(
    fontSize = 13.sp,
    fontWeight = FontWeight.Normal,
    fontFamily = FontFamily.Default,
    lineHeight = 18.sp,
// color = Color.Black
  )
val caption1 =
  TextStyle(
    fontSize = 12.sp,
    fontWeight = FontWeight.Normal,
    fontFamily = FontFamily.Default,
    lineHeight = 16.sp,
// color = Color.Black
  )
val caption2 =
  TextStyle(
    fontSize = 11.sp,
    fontWeight = FontWeight.Normal,
    fontFamily = FontFamily.Default,
    lineHeight = 13.sp,
// color = Color.Black
  ) */

val AppTypography =
  MedTrackerTypography(
    largeTitle =
      TextStyle(
        fontSize = 34.sp,
        fontWeight = FontWeight.Normal,
        fontFamily = FontFamily.Default,
        lineHeight = 41.sp,
      ), // Used for titles and headings that need to make a strong visual impact.
    largeTitleEmphasized =
      TextStyle(
        fontSize = 34.sp,
        fontWeight = FontWeight.Bold,
        fontFamily = FontFamily.Default,
        lineHeight = 41.sp,
      ), // Used for titles and headings that need to make a strong visual impact.
    title1 =
      TextStyle(
        fontSize = 28.sp,
        fontWeight = FontWeight.Normal,
        fontFamily = FontFamily.Default,
        lineHeight = 34.sp,
        // color = Color.Black
      ),
    title1Emphasized =
      TextStyle(
        fontSize = 28.sp,
        fontWeight = FontWeight.Bold,
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
    title2Emphasized =
      TextStyle(
        fontSize = 22.sp,
        fontWeight = FontWeight.Bold,
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
    title3Emphasized =
      TextStyle(
        fontSize = 20.sp,
        fontWeight = FontWeight.SemiBold,
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
    headlineItalic =
      TextStyle(
        fontSize = 17.sp,
        fontWeight = FontWeight.SemiBold,
        fontStyle = FontStyle.Italic,
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
    bodyEmphasized =
      TextStyle(
        fontSize = 17.sp,
        fontWeight = FontWeight.SemiBold,
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
    calloutEmphasized =
      TextStyle(
        fontSize = 16.sp,
        fontWeight = FontWeight.SemiBold,
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
    subheadEmphasized =
      TextStyle(
        fontSize = 15.sp,
        fontWeight = FontWeight.SemiBold,
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
    footnoteEmphasized =
      TextStyle(
        fontSize = 13.sp,
        fontWeight = FontWeight.SemiBold,
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
    caption1Emphasized =
      TextStyle(
        fontSize = 12.sp,
        fontWeight = FontWeight.Medium,
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
    caption2Emphasized =
      TextStyle(
        fontSize = 11.sp,
        fontWeight = FontWeight.SemiBold,
        fontFamily = FontFamily.Default,
        lineHeight = 13.sp,
        // color = Color.Black
      ),
  )
