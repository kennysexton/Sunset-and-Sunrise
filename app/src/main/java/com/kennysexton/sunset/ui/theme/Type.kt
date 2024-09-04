package com.kennysexton.sunset.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.kennysexton.sunset.R

object AppFont {
    val Monsterrat = FontFamily(
        Font(R.font.montserrat_medium, FontWeight.Medium),
        Font(R.font.montserrat_regular, FontWeight.Normal),
        Font(R.font.montserrat_light, FontWeight.Light),
        Font(R.font.montserrat_extra_light, FontWeight.ExtraLight)
    )
}


private val defaultTypography = Typography()
val Typography = Typography(
    displayLarge = defaultTypography.displayLarge.copy(fontFamily = AppFont.Monsterrat),
    displayMedium = defaultTypography.displayMedium.copy(fontFamily = AppFont.Monsterrat),
    displaySmall = defaultTypography.displaySmall.copy(fontFamily = AppFont.Monsterrat),

    headlineLarge = defaultTypography.headlineLarge.copy(fontFamily = AppFont.Monsterrat),
    headlineMedium = defaultTypography.headlineMedium.copy(fontFamily = AppFont.Monsterrat),
    headlineSmall = defaultTypography.headlineSmall.copy(fontFamily = AppFont.Monsterrat),

    titleLarge = defaultTypography.titleLarge.copy(fontFamily = AppFont.Monsterrat),
    titleMedium = defaultTypography.titleMedium.copy(fontFamily = AppFont.Monsterrat),
    titleSmall = defaultTypography.titleSmall.copy(fontFamily = AppFont.Monsterrat),

    bodyLarge = defaultTypography.bodyLarge.copy(fontFamily = AppFont.Monsterrat),
    bodyMedium = defaultTypography.bodyMedium.copy(fontFamily = AppFont.Monsterrat),
    bodySmall = defaultTypography.bodySmall.copy(fontFamily = AppFont.Monsterrat),

    labelLarge = defaultTypography.labelLarge.copy(fontFamily = AppFont.Monsterrat),
    labelMedium = defaultTypography.labelMedium.copy(fontFamily = AppFont.Monsterrat),
    labelSmall = defaultTypography.labelSmall.copy(fontFamily = AppFont.Monsterrat)
)
/* Other default text styles to override
titleLarge = TextStyle(
    fontFamily = FontFamily.Default,
    fontWeight = FontWeight.Normal,
    fontSize = 22.sp,
    lineHeight = 28.sp,
    letterSpacing = 0.sp
),
labelSmall = TextStyle(
    fontFamily = FontFamily.Default,
    fontWeight = FontWeight.Medium,
    fontSize = 11.sp,
    lineHeight = 16.sp,
    letterSpacing = 0.5.sp
)
*/
