package com.example.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

private val DarkColorScheme = darkColorScheme(
  primary = AmberGold,
  onPrimary = ForestDeep,
  primaryContainer = ForestPine,
  onPrimaryContainer = DesertSand,
  secondary = WarmTerracotta,
  onSecondary = Color.White,
  secondaryContainer = ForestSurface,
  onSecondaryContainer = SlateTextLight,
  tertiary = ForestLight,
  onTertiary = Color.White,
  background = ObsidianDark,
  onBackground = DesertSand,
  surface = SlateCard,
  onSurface = DesertSand,
  surfaceVariant = ForestSurface,
  onSurfaceVariant = SlateTextMuted,
  outline = SlateCardBorder,
  outlineVariant = ForestMoss
)

private val LightColorScheme = lightColorScheme(
  primary = ForestPine,
  onPrimary = Color.White,
  primaryContainer = ForestSurface,
  onPrimaryContainer = DesertSand,
  secondary = WarmTerracotta,
  onSecondary = Color.White,
  secondaryContainer = DesertSandDark,
  onSecondaryContainer = ForestDeep,
  tertiary = AmberGold,
  onTertiary = ForestDeep,
  background = ParchmentLight,
  onBackground = ForestDeep,
  surface = Color.White,
  onSurface = ForestDeep,
  surfaceVariant = DesertSand,
  onSurfaceVariant = ForestMoss,
  outline = DesertSandDark,
  outlineVariant = SlateTextMuted
)

@Composable
fun MyApplicationTheme(
  darkTheme: Boolean = isSystemInDarkTheme(),
  dynamicColor: Boolean = false, // Preserve bespoke editorial aesthetic
  content: @Composable () -> Unit,
) {
  val colorScheme = if (darkTheme) DarkColorScheme else LightColorScheme
  MaterialTheme(
    colorScheme = colorScheme,
    typography = Typography,
    content = content
  )
}

