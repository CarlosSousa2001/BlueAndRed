package com.crs.bluered.ui.theme

import androidx.compose.ui.graphics.Color

// ---------------------------------------------------
// BASE — Tailwind-like palette
// ---------------------------------------------------

// Blue scale (Primary brand)
val Blue100 = Color(0xFFDBEAFE)
val Blue400 = Color(0xFF60A5FA)
val Blue500 = Color(0xFF3B82F6)
val Blue600 = Color(0xFF2563EB)
val Blue700 = Color(0xFF1D4ED8)

// Neutrals (Slate / Gray)
val Slate50 = Color(0xFFF9FAFB)
val Slate100 = Color(0xFFF3F4F6)
val Slate800 = Color(0xFF1F2937)
val Slate900 = Color(0xFF0F172A)
val Slate950 = Color(0xFF020617)

// States
val Red500 = Color(0xFFEF4444)
val Green500 = Color(0xFF22C55E)
val Yellow500 = Color(0xFFEAB308)

// Whites / Blacks custom tokens
val PureWhite = Color(0xFFFFFFFF)
val PureBlack = Color(0xFF000000)


// ---------------------------------------------------
// SEMANTIC TOKENS — LIGHT THEME
// ---------------------------------------------------

val PrimaryLight = Blue500
val PrimaryHoverLight = Blue600
val PrimaryPressedLight = Blue700

val OnPrimaryLight = PureWhite

val BgAppLight = Slate50
val BgCardLight = PureWhite
val BgSidebarLight = Slate100

val TextPrimaryLight = Slate900
val TextMutedLight = Color(0xFF6B7280) // slate-500

val BorderSubtleLight = Color(0xFFE5E7EB)
val BorderStrongLight = Color(0xFFD1D5DB)

val ErrorLight = Red500
val OnErrorLight = PureWhite

val SuccessLight = Green500
val WarningLight = Yellow500


// ---------------------------------------------------
// SEMANTIC TOKENS — DARK THEME
// ---------------------------------------------------

val PrimaryDark = Blue400
val PrimaryHoverDark = Blue500
val PrimaryPressedDark = Blue600

val OnPrimaryDark = PureWhite

val BgAppDark = Slate950
val BgCardDark = Slate900
val BgSidebarDark = Slate900

val TextPrimaryDark = Color(0xFFE5E7EB)
val TextMutedDark = Color(0xFF9CA3AF)

val BorderSubtleDark = Slate800
val BorderStrongDark = Color(0xFF4B5563)

val ErrorDark = Red500
val OnErrorDark = PureWhite

val SuccessDark = Green500
val WarningDark = Yellow500