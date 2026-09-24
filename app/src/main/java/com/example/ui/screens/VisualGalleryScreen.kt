package com.example.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
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
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CameraAlt
import androidx.compose.material.icons.filled.Fullscreen
import androidx.compose.material.icons.filled.Landscape
import androidx.compose.material3.Icon
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
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.data.PortfolioRepository
import com.example.model.NarrativeLayer
import com.example.model.ProjectItem
import com.example.ui.components.LayerBadge
import com.example.ui.theme.AmberGold
import com.example.ui.theme.ForestDeep
import com.example.ui.theme.ForestPine
import com.example.ui.theme.SlateCardBorder
import com.example.ui.theme.SlateTextLight
import com.example.ui.theme.SlateTextMuted

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun VisualGalleryScreen(
  onSelectProjectPhoto: (ProjectItem) -> Unit,
  modifier: Modifier = Modifier
) {
  val projects = PortfolioRepository.projects

  LazyColumn(
    modifier = modifier
      .fillMaxSize()
      .background(MaterialTheme.colorScheme.background)
      .testTag("visual_gallery_screen"),
    verticalArrangement = Arrangement.spacedBy(18.dp)
  ) {
    // Header Banner
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
                imageVector = Icons.Default.CameraAlt,
                contentDescription = null,
                tint = AmberGold,
                modifier = Modifier.size(20.dp)
              )
            }
            Spacer(modifier = Modifier.width(12.dp))
            Column {
              Text(
                text = "VISUAL STORYTELLER",
                style = MaterialTheme.typography.labelMedium.copy(
                  color = AmberGold,
                  fontWeight = FontWeight.Bold,
                  letterSpacing = 1.sp
                )
              )
              Text(
                text = "Cinematography & Field Photography",
                style = MaterialTheme.typography.headlineSmall.copy(
                  color = Color.White,
                  fontWeight = FontWeight.Bold
                )
              )
            }
          }

          Spacer(modifier = Modifier.height(14.dp))

          Text(
            text = "“Photography does not merely illustrate tourism research—it is the sensory bridge. By recording light, weather, and vernacular human dignity, images transform statistical carrying capacity into deep emotional empathy for places.”",
            style = MaterialTheme.typography.bodyMedium.copy(
              fontStyle = FontStyle.Italic,
              fontFamily = FontFamily.Serif,
              color = SlateTextLight,
              lineHeight = 22.sp
            )
          )
        }
      }
    }

    // Exhibition Items
    items(projects, key = { it.id }) { project ->
      val visual = project.visual
      val exif = visual.exif

      Surface(
        modifier = Modifier
          .fillMaxWidth()
          .padding(horizontal = 18.dp)
          .clip(RoundedCornerShape(20.dp))
          .clickable { onSelectProjectPhoto(project) }
          .testTag("gallery_card_${project.id}"),
        shape = RoundedCornerShape(20.dp),
        color = MaterialTheme.colorScheme.surface,
        border = androidx.compose.foundation.BorderStroke(1.dp, MaterialTheme.colorScheme.outline),
        tonalElevation = 5.dp
      ) {
        Column {
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
                      Color.Black.copy(alpha = 0.3f),
                      Color.Transparent,
                      Color.Black.copy(alpha = 0.85f)
                    )
                  )
                )
            )

            // Top badge
            Row(
              modifier = Modifier
                .align(Alignment.TopEnd)
                .padding(12.dp)
            ) {
              Surface(
                shape = CircleShape,
                color = ForestDeep.copy(alpha = 0.8f),
                border = androidx.compose.foundation.BorderStroke(1.dp, SlateCardBorder)
              ) {
                Box(
                  modifier = Modifier.padding(8.dp),
                  contentAlignment = Alignment.Center
                ) {
                  Icon(
                    imageVector = Icons.Default.Fullscreen,
                    contentDescription = "Expand Story",
                    tint = Color.White,
                    modifier = Modifier.size(16.dp)
                  )
                }
              }
            }

            // Bottom overlay
            Column(
              modifier = Modifier
                .align(Alignment.BottomStart)
                .padding(14.dp)
            ) {
              LayerBadge(layer = NarrativeLayer.VISUAL, compact = true)
              Spacer(modifier = Modifier.height(4.dp))
              Text(
                text = visual.title,
                style = MaterialTheme.typography.titleMedium.copy(
                  fontWeight = FontWeight.Bold,
                  color = Color.White,
                  fontFamily = FontFamily.Serif
                )
              )
            }
          }

          // Card Body: EXIF & Narrative Summary
          Column(modifier = Modifier.padding(16.dp)) {
            Text(
              text = visual.narrative,
              style = MaterialTheme.typography.bodyMedium.copy(
                color = MaterialTheme.colorScheme.onSurfaceVariant,
                lineHeight = 20.sp
              ),
              maxLines = 3
            )

            Spacer(modifier = Modifier.height(12.dp))

            // EXIF Bar
            Surface(
              shape = RoundedCornerShape(10.dp),
              color = ForestDeep,
              modifier = Modifier.fillMaxWidth()
            ) {
              Row(
                modifier = Modifier
                  .fillMaxWidth()
                  .padding(horizontal = 12.dp, vertical = 8.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
              ) {
                Text(
                  text = "${exif.camera} • ${exif.lens.take(15)}...",
                  style = MaterialTheme.typography.labelSmall.copy(
                    color = SlateTextLight,
                    fontSize = 10.sp
                  )
                )
                Text(
                  text = "${exif.shutter} | ${exif.aperture} | ISO ${exif.iso}",
                  style = MaterialTheme.typography.labelSmall.copy(
                    color = AmberGold,
                    fontSize = 10.sp,
                    fontWeight = FontWeight.Bold
                  )
                )
              }
            }

            Spacer(modifier = Modifier.height(10.dp))

            FlowRow(
              horizontalArrangement = Arrangement.spacedBy(6.dp),
              verticalArrangement = Arrangement.spacedBy(6.dp)
            ) {
              visual.photoTags.forEach { tag ->
                Surface(
                  shape = RoundedCornerShape(6.dp),
                  color = MaterialTheme.colorScheme.surfaceVariant
                ) {
                  Text(
                    text = tag,
                    style = MaterialTheme.typography.labelSmall.copy(
                      color = MaterialTheme.colorScheme.onSurfaceVariant,
                      fontSize = 9.5.sp
                    ),
                    modifier = Modifier.padding(horizontal = 6.dp, vertical = 3.dp)
                  )
                }
              }
            }
          }
        }
      }
    }

    item {
      Spacer(modifier = Modifier.height(30.dp))
    }
  }
}
