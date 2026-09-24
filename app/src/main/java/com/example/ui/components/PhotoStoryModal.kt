package com.example.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
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
import androidx.compose.material.icons.filled.CameraAlt
import androidx.compose.material.icons.filled.Close
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
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import com.example.model.NarrativeLayer
import com.example.model.ProjectItem
import com.example.ui.theme.AmberGold
import com.example.ui.theme.ForestDeep
import com.example.ui.theme.ForestPine
import com.example.ui.theme.SlateCard
import com.example.ui.theme.SlateCardBorder
import com.example.ui.theme.SlateTextLight
import com.example.ui.theme.SlateTextMuted

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun PhotoStoryModal(
  project: ProjectItem,
  onDismiss: () -> Unit,
  onExploreProject: () -> Unit
) {
  val visual = project.visual
  val exif = visual.exif

  Dialog(
    onDismissRequest = onDismiss,
    properties = DialogProperties(usePlatformDefaultWidth = false)
  ) {
    Surface(
      modifier = Modifier
        .fillMaxWidth(0.95f)
        .clip(RoundedCornerShape(24.dp))
        .border(1.dp, SlateCardBorder, RoundedCornerShape(24.dp))
        .testTag("photo_story_dialog"),
      color = SlateCard,
      tonalElevation = 12.dp
    ) {
      Column(
        modifier = Modifier
          .verticalScroll(rememberScrollState())
          .padding(bottom = 20.dp)
      ) {
        // Photo Header with close icon
        Box(
          modifier = Modifier
            .fillMaxWidth()
            .aspectRatio(16f / 10f)
        ) {
          Image(
            painter = painterResource(id = project.heroDrawableResId),
            contentDescription = visual.title,
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Crop
          )

          Box(
            modifier = Modifier
              .fillMaxSize()
              .background(
                Brush.verticalGradient(
                  colors = listOf(
                    Color.Black.copy(alpha = 0.5f),
                    Color.Transparent,
                    Color.Black.copy(alpha = 0.8f)
                  )
                )
              )
          )

          IconButton(
            onClick = onDismiss,
            modifier = Modifier
              .align(Alignment.TopEnd)
              .padding(14.dp)
              .size(36.dp)
              .clip(CircleShape)
              .background(Color.Black.copy(alpha = 0.65f))
              .testTag("close_story_dialog_button")
          ) {
            Icon(
              imageVector = Icons.Default.Close,
              contentDescription = "Close",
              tint = Color.White,
              modifier = Modifier.size(20.dp)
            )
          }

          Row(
            modifier = Modifier
              .align(Alignment.BottomStart)
              .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
          ) {
            LayerBadge(layer = NarrativeLayer.VISUAL)
            Spacer(modifier = Modifier.width(8.dp))
            Surface(
              shape = RoundedCornerShape(12.dp),
              color = ForestDeep.copy(alpha = 0.8f)
            ) {
              Text(
                text = "${project.title} • Field Archive",
                style = MaterialTheme.typography.labelSmall.copy(color = SlateTextLight),
                modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp)
              )
            }
          }
        }

        // Story & Narrative Body
        Column(
          modifier = Modifier.padding(20.dp)
        ) {
          Text(
            text = visual.title,
            style = MaterialTheme.typography.headlineMedium.copy(
              fontFamily = FontFamily.Serif,
              fontWeight = FontWeight.Bold,
              color = Color.White
            )
          )

          Spacer(modifier = Modifier.height(10.dp))

          Text(
            text = visual.narrative,
            style = MaterialTheme.typography.bodyLarge.copy(
              color = SlateTextLight,
              lineHeight = 23.sp
            )
          )

          Spacer(modifier = Modifier.height(16.dp))

          // Telemetry Grid
          Text(
            text = "CAMERA & EXIF TELEMETRY",
            style = MaterialTheme.typography.labelMedium.copy(
              color = AmberGold,
              fontWeight = FontWeight.Bold,
              letterSpacing = 1.sp
            )
          )

          Spacer(modifier = Modifier.height(10.dp))

          Surface(
            shape = RoundedCornerShape(16.dp),
            color = ForestDeep,
            border = androidx.compose.foundation.BorderStroke(1.dp, SlateCardBorder),
            modifier = Modifier.fillMaxWidth()
          ) {
            Column(modifier = Modifier.padding(14.dp)) {
              Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
              ) {
                TelemetryItem(label = "BODY", value = exif.camera)
                TelemetryItem(label = "LENS", value = exif.lens)
              }
              Spacer(modifier = Modifier.height(10.dp))
              Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
              ) {
                TelemetryItem(label = "EXPOSURE", value = "${exif.shutter} • ${exif.aperture}")
                TelemetryItem(label = "ISO & FOCAL", value = "ISO ${exif.iso} • ${exif.focalLength}")
              }
              Spacer(modifier = Modifier.height(10.dp))
              Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
              ) {
                TelemetryItem(label = "ELEVATION", value = exif.elevation)
                TelemetryItem(label = "COORDINATES", value = exif.coordinates)
              }
            }
          }

          Spacer(modifier = Modifier.height(16.dp))

          // Photo Tags
          FlowRow(
            horizontalArrangement = Arrangement.spacedBy(6.dp),
            verticalArrangement = Arrangement.spacedBy(6.dp)
          ) {
            visual.photoTags.forEach { tag ->
              Surface(
                shape = RoundedCornerShape(8.dp),
                color = ForestPine.copy(alpha = 0.5f),
                border = androidx.compose.foundation.BorderStroke(0.5.dp, AmberGold.copy(alpha = 0.4f))
              ) {
                Text(
                  text = tag,
                  style = MaterialTheme.typography.labelSmall.copy(color = AmberGold),
                  modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp)
                )
              }
            }
          }

          Spacer(modifier = Modifier.height(20.dp))

          // Dialog Actions
          Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(10.dp)
          ) {
            Button(
              onClick = {
                onDismiss()
                onExploreProject()
              },
              modifier = Modifier
                .weight(1f)
                .height(48.dp)
                .testTag("dialog_explore_case_study_button"),
              colors = ButtonDefaults.buttonColors(
                containerColor = ForestPine,
                contentColor = Color.White
              ),
              shape = RoundedCornerShape(12.dp)
            ) {
              Text("Read Complete Case Study", fontWeight = FontWeight.Bold)
            }
          }
        }
      }
    }
  }
}

@Composable
private fun TelemetryItem(
  label: String,
  value: String
) {
  Column {
    Text(
      text = label,
      style = MaterialTheme.typography.labelSmall.copy(
        color = SlateTextMuted,
        fontSize = 9.sp,
        fontWeight = FontWeight.Bold
      )
    )
    Text(
      text = value,
      style = MaterialTheme.typography.labelMedium.copy(
        color = SlateTextLight,
        fontSize = 11.5.sp,
        fontWeight = FontWeight.Medium
      )
    )
  }
}
