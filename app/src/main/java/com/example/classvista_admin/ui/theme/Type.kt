package com.example.classvista_admin.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.example.classvista_admin.R

object AppFont {
    val TitilliumWeb = FontFamily(
        Font(R.font.f300, FontWeight.Light),
        Font(R.font.f400, FontWeight.Normal),
        Font(R.font.f500, FontWeight.Medium),
        Font(R.font.f600, FontWeight.SemiBold),
        Font(R.font.f700, FontWeight.Bold),
        Font(R.font.f800, FontWeight.ExtraBold),
        Font(R.font.f900, FontWeight.Black),


        )
}

private val defaultTypography = Typography()
val Typography = Typography(
    displayLarge = defaultTypography.displayLarge.copy(fontFamily = AppFont.TitilliumWeb),
    displayMedium = defaultTypography.displayMedium.copy(fontFamily = AppFont.TitilliumWeb),
    displaySmall = defaultTypography.displaySmall.copy(fontFamily = AppFont.TitilliumWeb),

    headlineLarge = defaultTypography.headlineLarge.copy(fontFamily = AppFont.TitilliumWeb),
    headlineMedium = defaultTypography.headlineMedium.copy(fontFamily = AppFont.TitilliumWeb),
    headlineSmall = defaultTypography.headlineSmall.copy(fontFamily = AppFont.TitilliumWeb),

    titleLarge = defaultTypography.titleLarge.copy(fontFamily = AppFont.TitilliumWeb),
    titleMedium = defaultTypography.titleMedium.copy(fontFamily = AppFont.TitilliumWeb),
    titleSmall = defaultTypography.titleSmall.copy(fontFamily = AppFont.TitilliumWeb),

    bodyLarge = defaultTypography.bodyLarge.copy(fontFamily = AppFont.TitilliumWeb),
    bodyMedium = defaultTypography.bodyMedium.copy(fontFamily = AppFont.TitilliumWeb),
    bodySmall = defaultTypography.bodySmall.copy(fontFamily = AppFont.TitilliumWeb),

    labelLarge = defaultTypography.labelLarge.copy(fontFamily = AppFont.TitilliumWeb),
    labelMedium = defaultTypography.labelMedium.copy(fontFamily = AppFont.TitilliumWeb),
    labelSmall = defaultTypography.labelSmall.copy(fontFamily = AppFont.TitilliumWeb)
)