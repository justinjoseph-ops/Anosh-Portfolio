package com.example.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Apartment
import androidx.compose.material.icons.filled.CameraAlt
import androidx.compose.material.icons.filled.MenuBook
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.model.NarrativeLayer
import com.example.model.ProjectMetric
import com.example.ui.theme.AmberGold
import com.example.ui.theme.ForestDeep
import com.example.ui.theme.ForestPine
import com.example.ui.theme.SlateCardBorder
import com.example.ui.theme.SlateTextLight
import com.example.ui.theme.SlateTextMuted

@Composable
fun LayerBadge(
  layer: NarrativeLayer,
  modifier: Modifier = Modifier,
  compact: Boolean = false
) {
  val icon = when (layer) {
    NarrativeLayer.INTELLECTUAL -> Icons.Default.MenuBook
    NarrativeLayer.VISUAL -> Icons.Default.CameraAlt
    NarrativeLayer.PROFESSIONAL -> Icons.Default.Apartment
  }

  val backgroundColor = layer.color.copy(alpha = 0.15f)
  val borderColor = layer.color.copy(alpha = 0.45f)

  Row(
    modifier = modifier
      .clip(RoundedCornerShape(20.dp))
      .background(backgroundColor)
      .border(1.dp, borderColor, RoundedCornerShape(20.dp))
      .padding(horizontal = if (compact) 8.dp else 12.dp, vertical = if (compact) 3.dp else 6.dp),
    verticalAlignment = Alignment.CenterVertically,
    horizontalArrangement = Arrangement.Center
  ) {
    Icon(
      imageVector = icon,
      contentDescription = layer.displayName,
      tint = layer.color,
      modifier = Modifier.size(if (compact) 12.dp else 14.dp)
    )
    Spacer(modifier = Modifier.width(6.dp))
    Text(
      text = if (compact) layer.displayName else "${layer.displayName} Layer",
      style = MaterialTheme.typography.labelMedium.copy(
        fontSize = if (compact) 10.sp else 11.sp,
        fontWeight = FontWeight.Bold,
        color = layer.color
      )
    )
  }
}

@Composable
fun LayerFilterChip(
  label: String,
  isSelected: Boolean,
  layer: NarrativeLayer? = null,
  onClick: () -> Unit,
  modifier: Modifier = Modifier
) {
  val accentColor = layer?.color ?: AmberGold
  val bgColor = if (isSelected) accentColor else MaterialTheme.colorScheme.surfaceVariant
  val textColor = if (isSelected) ForestDeep else MaterialTheme.colorScheme.onSurfaceVariant
  val borderColor = if (isSelected) accentColor else MaterialTheme.colorScheme.outline

  Surface(
    modifier = modifier
      .clip(RoundedCornerShape(24.dp))
      .clickable(onClick = onClick)
      .testTag("filter_chip_${label.lowercase()}"),
    shape = RoundedCornerShape(24.dp),
    color = bgColor,
    border = androidx.compose.foundation.BorderStroke(1.dp, borderColor)
  ) {
    Row(
      modifier = Modifier.padding(horizontal = 14.dp, vertical = 8.dp),
      verticalAlignment = Alignment.CenterVertically
    ) {
      if (layer != null) {
        val icon = when (layer) {
          NarrativeLayer.INTELLECTUAL -> Icons.Default.MenuBook
          NarrativeLayer.VISUAL -> Icons.Default.CameraAlt
          NarrativeLayer.PROFESSIONAL -> Icons.Default.Apartment
        }
        Icon(
          imageVector = icon,
          contentDescription = null,
          tint = textColor,
          modifier = Modifier.size(14.dp)
        )
        Spacer(modifier = Modifier.width(6.dp))
      }
      Text(
        text = label,
        style = MaterialTheme.typography.labelLarge.copy(
          color = textColor,
          fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Medium
        )
      )
    }
  }
}

@Composable
fun PillarPhilosophyCard(
  onLearnMoreClick: () -> Unit,
  modifier: Modifier = Modifier
) {
  Surface(
    modifier = modifier
      .fillMaxWidth()
      .testTag("pillar_philosophy_card"),
    shape = RoundedCornerShape(20.dp),
    color = MaterialTheme.colorScheme.surface,
    border = androidx.compose.foundation.BorderStroke(1.dp, MaterialTheme.colorScheme.outline),
    tonalElevation = 4.dp
  ) {
    Column(
      modifier = Modifier.padding(20.dp)
    ) {
      Row(
        verticalAlignment = Alignment.CenterVertically
      ) {
        Box(
          modifier = Modifier
            .size(36.dp)
            .clip(CircleShape)
            .background(AmberGold.copy(alpha = 0.2f)),
          contentAlignment = Alignment.Center
        ) {
          Text(
            text = "III",
            style = MaterialTheme.typography.labelMedium.copy(
              fontFamily = FontFamily.Serif,
              fontWeight = FontWeight.Bold,
              color = AmberGold
            )
          )
        }
        Spacer(modifier = Modifier.width(12.dp))
        Column {
          Text(
            text = "The Triad Architecture",
            style = MaterialTheme.typography.titleMedium.copy(
              fontWeight = FontWeight.Bold,
              color = MaterialTheme.colorScheme.onSurface
            )
          )
          Text(
            text = "More than a CV: An integrated narrative of place",
            style = MaterialTheme.typography.bodyMedium.copy(
              color = SlateTextMuted
            )
          )
        }
      }

      Spacer(modifier = Modifier.height(16.dp))

      TriadLayerRow(
        number = "01",
        title = "Intellectual Layer",
        subtitle = "Tourism Research",
        desc = "Carrying capacity frameworks, ecological baselines, and tribal community economic retention in the Western Ghats & Goa.",
        layer = NarrativeLayer.INTELLECTUAL
      )

      Spacer(modifier = Modifier.height(12.dp))

      TriadLayerRow(
        number = "02",
        title = "Visual Layer",
        subtitle = "Photography & Videography",
        desc = "Evocative medium format and aerial cinematography capturing landscape spirit, morning mist, and cultural dignity.",
        layer = NarrativeLayer.VISUAL
      )

      Spacer(modifier = Modifier.height(12.dp))

      TriadLayerRow(
        number = "03",
        title = "Professional Layer",
        subtitle = "Hospitality Experience",
        desc = "Ground-level operations at Hotel Viraj Retreat: guest journey architecture, SOP authoring, and circular eco-resort practices.",
        layer = NarrativeLayer.PROFESSIONAL
      )
    }
  }
}

@Composable
private fun TriadLayerRow(
  number: String,
  title: String,
  subtitle: String,
  desc: String,
  layer: NarrativeLayer
) {
  Row(
    modifier = Modifier
      .fillMaxWidth()
      .clip(RoundedCornerShape(12.dp))
      .background(MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.4f))
      .padding(12.dp),
    verticalAlignment = Alignment.Top
  ) {
    Box(
      modifier = Modifier
        .size(26.dp)
        .clip(CircleShape)
        .background(layer.color.copy(alpha = 0.2f)),
      contentAlignment = Alignment.Center
    ) {
      Text(
        text = number,
        style = MaterialTheme.typography.labelSmall.copy(
          fontWeight = FontWeight.Bold,
          color = layer.color
        )
      )
    }
    Spacer(modifier = Modifier.width(12.dp))
    Column(modifier = Modifier.weight(1f)) {
      Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween,
        modifier = Modifier.fillMaxWidth()
      ) {
        Text(
          text = title,
          style = MaterialTheme.typography.titleMedium.copy(
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colorScheme.onSurface
          )
        )
        Text(
          text = subtitle,
          style = MaterialTheme.typography.labelSmall.copy(
            color = layer.color,
            fontWeight = FontWeight.SemiBold
          )
        )
      }
      Spacer(modifier = Modifier.height(4.dp))
      Text(
        text = desc,
        style = MaterialTheme.typography.bodyMedium.copy(
          color = MaterialTheme.colorScheme.onSurfaceVariant,
          fontSize = 12.5.sp,
          lineHeight = 17.sp
        )
      )
    }
  }
}

@Composable
fun ProjectMetricPill(
  metric: ProjectMetric,
  modifier: Modifier = Modifier
) {
  Surface(
    modifier = modifier,
    shape = RoundedCornerShape(12.dp),
    color = MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.6f),
    border = androidx.compose.foundation.BorderStroke(1.dp, SlateCardBorder)
  ) {
    Column(
      modifier = Modifier.padding(horizontal = 12.dp, vertical = 8.dp)
    ) {
      Text(
        text = metric.value,
        style = MaterialTheme.typography.titleMedium.copy(
          fontWeight = FontWeight.Bold,
          color = AmberGold
        )
      )
      Text(
        text = metric.label,
        style = MaterialTheme.typography.labelSmall.copy(
          color = SlateTextLight,
          fontWeight = FontWeight.Medium
        )
      )
      Text(
        text = metric.context,
        style = MaterialTheme.typography.labelSmall.copy(
          color = SlateTextMuted,
          fontSize = 9.5.sp
        )
      )
    }
  }
}
