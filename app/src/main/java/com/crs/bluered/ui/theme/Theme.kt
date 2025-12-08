package com.crs.bluered.ui.theme

import android.app.Activity
import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.platform.LocalContext

private val LightColorScheme = lightColorScheme(
    primary = PrimaryLight,
    onPrimary = OnPrimaryLight,
    primaryContainer = Blue100,
    onPrimaryContainer = TextPrimaryLight,

    secondary = PrimaryLight,
    onSecondary = OnPrimaryLight,
    secondaryContainer = Blue100,
    onSecondaryContainer = TextPrimaryLight,

    background = BgAppLight,
    onBackground = TextPrimaryLight,

    surface = BgCardLight,
    onSurface = TextPrimaryLight,

    surfaceVariant = BgSidebarLight,
    onSurfaceVariant = TextMutedLight,

    outline = BorderSubtleLight,

    error = ErrorLight,
    onError = OnErrorLight,
)

private val DarkColorScheme = darkColorScheme(
    primary = PrimaryDark,
    onPrimary = OnPrimaryDark,
    primaryContainer = PrimaryPressedDark,
    onPrimaryContainer = OnPrimaryDark,

    secondary = PrimaryDark,
    onSecondary = OnPrimaryDark,
    secondaryContainer = PrimaryPressedDark,
    onSecondaryContainer = OnPrimaryDark,

    background = BgAppDark,
    onBackground = TextPrimaryDark,

    surface = BgCardDark,
    onSurface = TextPrimaryDark,

    surfaceVariant = BgSidebarDark,
    onSurfaceVariant = TextMutedDark,

    outline = BorderSubtleDark,

    error = ErrorDark,
    onError = OnErrorDark,
)

@Composable
fun BlueRedTheme(
    sizing: Sizing = Sizing(),
    darkTheme: Boolean = isSystemInDarkTheme(),
    // Dynamic color is available on Android 12+
    dynamicColor: Boolean = false,
    content: @Composable () -> Unit
) {
    val colorScheme = when {
        dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
            val context = LocalContext.current
            if (darkTheme) dynamicDarkColorScheme(context) else dynamicLightColorScheme(context)
        }

        darkTheme -> DarkColorScheme
        else -> LightColorScheme
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = AppTypography,
        content = {
            CompositionLocalProvider(
                LocalSizing provides sizing,
            ) {
                content()
            }
        }
    )
}

object BlueRedThemeSizing {
    val sizing: Sizing
        @Composable
        get() = LocalSizing.current
}