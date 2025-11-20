package com.example.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.sp
import com.example.bookshelf.R

val MomoSignature = FontFamily(
    Font(R.font.momo_signature_regular)
)

val Montserrat = FontFamily(
    Font(R.font.montserrat_regular)
)

val AppTypography = Typography(
    displaySmall = TextStyle(
        fontFamily = MomoSignature,
        fontSize = 40.sp
    ),
    bodyMedium = TextStyle(
        fontFamily = Montserrat,
        fontSize = 16.sp
    )
)
