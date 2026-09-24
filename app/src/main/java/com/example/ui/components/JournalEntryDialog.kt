package com.example.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
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
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Cloud
import androidx.compose.material.icons.filled.Landscape
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
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
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import com.example.model.FieldJournalEntry
import com.example.ui.theme.AmberGold
import com.example.ui.theme.ForestDeep
import com.example.ui.theme.ForestPine
import com.example.ui.theme.SlateCard
import com.example.ui.theme.SlateCardBorder
import com.example.ui.theme.SlateTextLight
import com.example.ui.theme.SlateTextMuted

@Composable
fun JournalEntryDialog(
  entry: FieldJournalEntry,
  onDismiss: () -> Unit
) {
  Dialog(
    onDismissRequest = onDismiss,
    properties = DialogProperties(usePlatformDefaultWidth = false)
  ) {
    Surface(
      modifier = Modifier
        .fillMaxWidth(0.92f)
        .clip(RoundedCornerShape(22.dp))
        .border(1.dp, SlateCardBorder, RoundedCornerShape(22.dp))
        .testTag("journal_entry_dialog"),
      color = SlateCard,
      tonalElevation = 12.dp
    ) {
      Column(
        modifier = Modifier
          .verticalScroll(rememberScrollState())
          .padding(22.dp)
      ) {
        Row(
          modifier = Modifier.fillMaxWidth(),
          horizontalArrangement = Arrangement.SpaceBetween,
          verticalAlignment = Alignment.CenterVertically
        ) {
          LayerBadge(layer = entry.layer)
          IconButton(
            onClick = onDismiss,
            modifier = Modifier
              .size(34.dp)
              .clip(CircleShape)
              .background(ForestDeep)
              .testTag("close_journal_dialog_button")
          ) {
            Icon(
              imageVector = Icons.Default.Close,
              contentDescription = "Close",
              tint = Color.White,
              modifier = Modifier.size(18.dp)
            )
          }
        }

        Spacer(modifier = Modifier.height(14.dp))

        Text(
          text = entry.title,
          style = MaterialTheme.typography.headlineMedium.copy(
            fontFamily = FontFamily.Serif,
            fontWeight = FontWeight.Bold,
            color = Color.White
          )
        )

        Spacer(modifier = Modifier.height(12.dp))

        Surface(
          shape = RoundedCornerShape(12.dp),
          color = ForestDeep,
          border = androidx.compose.foundation.BorderStroke(1.dp, SlateCardBorder)
        ) {
          Column(modifier = Modifier.padding(12.dp)) {
            Row(verticalAlignment = Alignment.CenterVertically) {
              Icon(imageVector = Icons.Default.LocationOn, contentDescription = null, tint = AmberGold, modifier = Modifier.size(14.dp))
              Spacer(modifier = Modifier.width(6.dp))
              Text(text = "${entry.location} • ${entry.altitude}", style = MaterialTheme.typography.labelSmall.copy(color = SlateTextLight))
            }
            Spacer(modifier = Modifier.height(6.dp))
            Row(verticalAlignment = Alignment.CenterVertically) {
              Icon(imageVector = Icons.Default.Cloud, contentDescription = null, tint = ForestPine, modifier = Modifier.size(14.dp))
              Spacer(modifier = Modifier.width(6.dp))
              Text(text = "${entry.date} • ${entry.weather}", style = MaterialTheme.typography.labelSmall.copy(color = SlateTextMuted))
            }
          }
        }

        Spacer(modifier = Modifier.height(16.dp))

        Text(
          text = entry.notes,
          style = MaterialTheme.typography.bodyLarge.copy(
            fontFamily = FontFamily.Serif,
            color = SlateTextLight,
            lineHeight = 24.sp
          )
        )

        Spacer(modifier = Modifier.height(20.dp))

        Button(
          onClick = onDismiss,
          colors = ButtonDefaults.buttonColors(
            containerColor = ForestPine,
            contentColor = Color.White
          ),
          modifier = Modifier.fillMaxWidth(),
          shape = RoundedCornerShape(10.dp)
        ) {
          Text("Close Field Log", fontWeight = FontWeight.Bold)
        }
      }
    }
  }
}
