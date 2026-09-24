package com.example.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Book
import androidx.compose.material.icons.filled.Cloud
import androidx.compose.material.icons.filled.Landscape
import androidx.compose.material.icons.filled.LocationOn
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
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.data.PortfolioRepository
import com.example.model.FieldJournalEntry
import com.example.ui.components.LayerBadge
import com.example.ui.theme.AmberGold
import com.example.ui.theme.ForestDeep
import com.example.ui.theme.ForestPine
import com.example.ui.theme.SlateCardBorder
import com.example.ui.theme.SlateTextLight
import com.example.ui.theme.SlateTextMuted

@Composable
fun JournalScreen(
  onSelectEntry: (FieldJournalEntry) -> Unit,
  modifier: Modifier = Modifier
) {
  val journalEntries = PortfolioRepository.fieldJournal

  LazyColumn(
    modifier = modifier
      .fillMaxSize()
      .background(MaterialTheme.colorScheme.background)
      .testTag("journal_screen"),
    verticalArrangement = Arrangement.spacedBy(16.dp)
  ) {
    // Header
    item {
      Surface(
        modifier = Modifier.fillMaxWidth(),
        color = ForestDeep
      ) {
        Column(
          modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 20.dp, vertical = 24.dp)
        ) {
          Row(verticalAlignment = Alignment.CenterVertically) {
            Box(
              modifier = Modifier
                .size(40.dp)
                .clip(CircleShape)
                .background(AmberGold.copy(alpha = 0.2f)),
              contentAlignment = Alignment.Center
            ) {
              Icon(
                imageVector = Icons.Default.Book,
                contentDescription = null,
                tint = AmberGold,
                modifier = Modifier.size(20.dp)
              )
            }
            Spacer(modifier = Modifier.width(12.dp))
            Column {
              Text(
                text = "FIELD NOTEBOOK",
                style = MaterialTheme.typography.labelMedium.copy(
                  color = AmberGold,
                  fontWeight = FontWeight.Bold,
                  letterSpacing = 1.sp
                )
              )
              Text(
                text = "Explorer’s Dispatches & Logs",
                style = MaterialTheme.typography.headlineSmall.copy(
                  color = Color.White,
                  fontWeight = FontWeight.Bold
                )
              )
            }
          }

          Spacer(modifier = Modifier.height(14.dp))

          Text(
            text = "Unedited observations written in real-time from mountain ridges, village ferries, and resort verandas—where research, imagery, and hospitality intersect.",
            style = MaterialTheme.typography.bodyMedium.copy(
              color = SlateTextLight,
              fontFamily = FontFamily.Serif,
              lineHeight = 22.sp
            )
          )
        }
      }
    }

    items(journalEntries, key = { it.id }) { entry ->
      Box(modifier = Modifier.padding(horizontal = 18.dp)) {
        Surface(
          shape = RoundedCornerShape(18.dp),
          color = MaterialTheme.colorScheme.surface,
          border = androidx.compose.foundation.BorderStroke(1.dp, MaterialTheme.colorScheme.outline),
          modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(18.dp))
            .clickable { onSelectEntry(entry) }
            .testTag("journal_card_${entry.id}"),
          tonalElevation = 4.dp
        ) {
          Column(modifier = Modifier.padding(18.dp)) {
            // Meta bar
            Row(
              modifier = Modifier.fillMaxWidth(),
              horizontalArrangement = Arrangement.SpaceBetween,
              verticalAlignment = Alignment.CenterVertically
            ) {
              LayerBadge(layer = entry.layer, compact = true)
              Text(
                text = entry.date,
                style = MaterialTheme.typography.labelSmall.copy(
                  color = AmberGold,
                  fontWeight = FontWeight.Bold
                )
              )
            }

            Spacer(modifier = Modifier.height(10.dp))

            Text(
              text = entry.title,
              style = MaterialTheme.typography.headlineSmall.copy(
                fontFamily = FontFamily.Serif,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.onSurface,
                fontSize = 18.sp
              )
            )

            Spacer(modifier = Modifier.height(8.dp))

            // Weather & Altitude Row
            Row(
              modifier = Modifier.fillMaxWidth(),
              verticalAlignment = Alignment.CenterVertically
            ) {
              Icon(
                imageVector = Icons.Default.LocationOn,
                contentDescription = null,
                tint = ForestPine,
                modifier = Modifier.size(13.dp)
              )
              Spacer(modifier = Modifier.width(4.dp))
              Text(
                text = entry.location,
                style = MaterialTheme.typography.labelSmall.copy(
                  color = MaterialTheme.colorScheme.onSurfaceVariant,
                  fontWeight = FontWeight.Medium
                )
              )
              Spacer(modifier = Modifier.width(8.dp))
              Text(text = "•", color = SlateTextMuted)
              Spacer(modifier = Modifier.width(8.dp))
              Icon(
                imageVector = Icons.Default.Landscape,
                contentDescription = null,
                tint = SlateTextMuted,
                modifier = Modifier.size(12.dp)
              )
              Spacer(modifier = Modifier.width(4.dp))
              Text(
                text = entry.altitude,
                style = MaterialTheme.typography.labelSmall.copy(color = SlateTextMuted)
              )
            }

            Spacer(modifier = Modifier.height(12.dp))

            Surface(
              shape = RoundedCornerShape(10.dp),
              color = MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.5f),
              border = androidx.compose.foundation.BorderStroke(0.5.dp, MaterialTheme.colorScheme.outline)
            ) {
              Row(
                modifier = Modifier.padding(horizontal = 10.dp, vertical = 6.dp),
                verticalAlignment = Alignment.CenterVertically
              ) {
                Icon(
                  imageVector = Icons.Default.Cloud,
                  contentDescription = null,
                  tint = AmberGold,
                  modifier = Modifier.size(13.dp)
                )
                Spacer(modifier = Modifier.width(6.dp))
                Text(
                  text = entry.weather,
                  style = MaterialTheme.typography.labelSmall.copy(
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                    fontSize = 11.sp
                  )
                )
              }
            }

            Spacer(modifier = Modifier.height(12.dp))

            Text(
              text = entry.notes,
              style = MaterialTheme.typography.bodyMedium.copy(
                fontFamily = FontFamily.Serif,
                color = MaterialTheme.colorScheme.onSurface,
                lineHeight = 22.sp
              )
            )
          }
        }
      }
    }

    item {
      Spacer(modifier = Modifier.height(30.dp))
    }
  }
}
