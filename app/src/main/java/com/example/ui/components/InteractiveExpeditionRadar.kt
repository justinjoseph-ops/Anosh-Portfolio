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
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material.icons.filled.Explore
import androidx.compose.material.icons.filled.Landscape
import androidx.compose.material.icons.filled.MyLocation
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.data.PortfolioRepository
import com.example.model.ProjectItem
import com.example.ui.theme.AmberGold
import com.example.ui.theme.ForestDeep
import com.example.ui.theme.ForestPine
import com.example.ui.theme.SlateCardBorder
import com.example.ui.theme.SlateTextLight
import com.example.ui.theme.SlateTextMuted
import com.example.ui.theme.WarmTerracotta

@Composable
fun InteractiveExpeditionRadar(
  onSelectProject: (String) -> Unit,
  modifier: Modifier = Modifier
) {
  val projects = PortfolioRepository.projects
  var activeProjectIndex by remember { mutableStateOf(0) }
  val currentProject = projects.getOrNull(activeProjectIndex) ?: projects.first()

  Surface(
    modifier = modifier
      .fillMaxWidth()
      .testTag("interactive_expedition_radar"),
    shape = RoundedCornerShape(22.dp),
    color = MaterialTheme.colorScheme.surface,
    border = androidx.compose.foundation.BorderStroke(1.dp, MaterialTheme.colorScheme.outline),
    tonalElevation = 5.dp
  ) {
    Column(
      modifier = Modifier.padding(18.dp)
    ) {
      Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
      ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
          Box(
            modifier = Modifier
              .size(34.dp)
              .clip(CircleShape)
              .background(ForestPine.copy(alpha = 0.2f)),
            contentAlignment = Alignment.Center
          ) {
            Icon(
              imageVector = Icons.Default.Explore,
              contentDescription = null,
              tint = ForestPine,
              modifier = Modifier.size(18.dp)
            )
          }
          Spacer(modifier = Modifier.width(10.dp))
          Column {
            Text(
              text = "Expedition Field Radar",
              style = MaterialTheme.typography.titleMedium.copy(
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.onSurface
              )
            )
            Text(
              text = "Geographic coordinates of four research hubs",
              style = MaterialTheme.typography.bodySmall.copy(color = SlateTextMuted)
            )
          }
        }
      }

      Spacer(modifier = Modifier.height(14.dp))

      // Tab selector pills for the 4 locations
      Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(6.dp)
      ) {
        projects.forEachIndexed { index, project ->
          val isSelected = index == activeProjectIndex
          val shortName = when (project.id) {
            "wayanad" -> "Wayanad"
            "thangalpara" -> "Thangalpara"
            "goa" -> "Goa"
            "viraj" -> "Viraj Retreat"
            else -> project.title.take(8)
          }

          Surface(
            modifier = Modifier
              .weight(1f)
              .clip(RoundedCornerShape(12.dp))
              .clickable { activeProjectIndex = index }
              .testTag("radar_tab_${project.id}"),
            shape = RoundedCornerShape(12.dp),
            color = if (isSelected) ForestPine else MaterialTheme.colorScheme.surfaceVariant,
            border = androidx.compose.foundation.BorderStroke(
              1.dp,
              if (isSelected) AmberGold else MaterialTheme.colorScheme.outline
            )
          ) {
            Box(
              modifier = Modifier.padding(vertical = 8.dp),
              contentAlignment = Alignment.Center
            ) {
              Text(
                text = shortName,
                style = MaterialTheme.typography.labelSmall.copy(
                  color = if (isSelected) Color.White else MaterialTheme.colorScheme.onSurfaceVariant,
                  fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Medium,
                  fontSize = 10.sp
                ),
                maxLines = 1
              )
            }
          }
        }
      }

      Spacer(modifier = Modifier.height(14.dp))

      // Active Location Telemetry Card
      Surface(
        modifier = Modifier
          .fillMaxWidth()
          .clip(RoundedCornerShape(16.dp))
          .clickable { onSelectProject(currentProject.id) }
          .testTag("radar_active_card"),
        shape = RoundedCornerShape(16.dp),
        color = MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.7f),
        border = androidx.compose.foundation.BorderStroke(1.dp, SlateCardBorder)
      ) {
        Column(
          modifier = Modifier.padding(14.dp)
        ) {
          Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
          ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
              Icon(
                imageVector = Icons.Default.MyLocation,
                contentDescription = null,
                tint = AmberGold,
                modifier = Modifier.size(14.dp)
              )
              Spacer(modifier = Modifier.width(6.dp))
              Text(
                text = currentProject.location,
                style = MaterialTheme.typography.titleSmall.copy(
                  fontWeight = FontWeight.Bold,
                  color = MaterialTheme.colorScheme.onSurface
                )
              )
            }

            Surface(
              shape = RoundedCornerShape(8.dp),
              color = ForestDeep,
              border = androidx.compose.foundation.BorderStroke(0.5.dp, AmberGold)
            ) {
              Text(
                text = currentProject.elevation,
                style = MaterialTheme.typography.labelSmall.copy(
                  color = AmberGold,
                  fontSize = 10.sp,
                  fontWeight = FontWeight.Bold
                ),
                modifier = Modifier.padding(horizontal = 8.dp, vertical = 3.dp)
              )
            }
          }

          Spacer(modifier = Modifier.height(8.dp))

          Text(
            text = currentProject.briefSummary,
            style = MaterialTheme.typography.bodyMedium.copy(
              color = MaterialTheme.colorScheme.onSurfaceVariant,
              lineHeight = 18.sp
            ),
            maxLines = 2
          )

          Spacer(modifier = Modifier.height(10.dp))

          Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
          ) {
            Text(
              text = currentProject.coordinates,
              style = MaterialTheme.typography.labelSmall.copy(
                color = SlateTextMuted,
                fontSize = 10.sp
              )
            )

            Row(verticalAlignment = Alignment.CenterVertically) {
              Text(
                text = "Open Deep Dive",
                style = MaterialTheme.typography.labelSmall.copy(
                  color = ForestPine,
                  fontWeight = FontWeight.Bold
                )
              )
              Spacer(modifier = Modifier.width(4.dp))
              Icon(
                imageVector = Icons.Default.ArrowForward,
                contentDescription = null,
                tint = ForestPine,
                modifier = Modifier.size(13.dp)
              )
            }
          }
        }
      }
    }
  }
}
