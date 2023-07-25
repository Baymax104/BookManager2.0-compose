package com.baymax104.bookmanager20compose.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.PlatformTextStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.em
import androidx.compose.ui.unit.sp
import com.baymax104.bookmanager20compose.R

val BookManagerFont = FontFamily(
    Font(R.font.noto_black, FontWeight.Black),
    Font(R.font.noto_bold, FontWeight.Bold),
    Font(R.font.noto_light, FontWeight.Light),
    Font(R.font.noto_medium, FontWeight.Medium),
    Font(R.font.noto_regular, FontWeight.Normal),
    Font(R.font.noto_thin, FontWeight.Thin)
)

@Suppress("DEPRECATION")
val Typography = Typography(
    titleLarge = TextStyle(
        fontFamily = BookManagerFont,
        fontSize = 18.sp,
        letterSpacing = 1.sp,
        fontWeight = FontWeight.Black,
        platformStyle = PlatformTextStyle(includeFontPadding = false)
    ),
    bodyLarge = TextStyle(
        fontFamily = BookManagerFont,
        fontSize = 16.sp,
        letterSpacing = 0.5.sp,
        fontWeight = FontWeight.Medium,
        platformStyle = PlatformTextStyle(includeFontPadding = false)
    ),
    labelMedium = TextStyle(
        fontFamily = BookManagerFont,
        fontSize = 15.sp,
        fontWeight = FontWeight.Medium,
        platformStyle = PlatformTextStyle(includeFontPadding = false)
    ),
    labelSmall = TextStyle(
        fontFamily = BookManagerFont,
        fontWeight = FontWeight.Normal,
        fontSize = 12.sp,
        platformStyle = PlatformTextStyle(includeFontPadding = false)
    )
)