package com.example.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
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
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material.icons.filled.Bookmark
import androidx.compose.material.icons.filled.BookmarkBorder
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
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.model.NarrativeLayer
import com.example.model.ProjectItem
import com.example.ui.theme.AmberGold
import com.example.ui.theme.ForestDeep
import com.example.ui.theme.ForestPine
import com.example.ui.theme.SlateCardBorder
import com.example.ui.theme.SlateTextLight
import com.example.ui.theme.SlateTextMuted

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun ProjectCard(
  project: ProjectItem,
  isBookmarked: Boolean,
  onProjectClick: () -> Unit,
  onBookmarkClick: () -> Unit,
  onViewPhotoClick: () -> Unit,
  modifier: Modifier = Modifier
) {
  Surface(
    modifier = modifier
      .fillMaxWidth()
      .clip(RoundedCornerShape(22.dp))
      .clickable(onClick = onProjectClick)
      .testTag("project_card_${project.id}"),
    shape = RoundedCornerShape(22.dp),
    color = MaterialTheme.colorScheme.surface,
    border = androidx.compose.foundation.BorderStroke(1.dp, MaterialTheme.colorScheme.outline),
    tonalElevation = 6.dp
  ) {
    Column {
      // Hero image with telemetry overlays
      Box(
        modifier = Modifier
          .fillMaxWidth()
          .aspectRatio(16f / 10f)
      ) {
        Image(
          painter = painterResource(id = project.heroDrawableResId),
          contentDescription = project.title,
          modifier = Modifier.fillMaxSize(),
          contentScale = ContentScale.Crop
        )

        // Gradient overlay
        Box(
          modifier = Modifier
            .fillMaxSize()
            .background(
              Brush.verticalGradient(
                colors = listOf(
                  Color.Black.copy(alpha = 0.35f),
                  Color.Transparent,
                  Color.Black.copy(alpha = 0.85f)
                )
              )
            )
        )

        // Top badges: Location + Bookmark action
        Row(
          modifier = Modifier
            .fillMaxWidth()
            .padding(14.dp),
          horizontalArrangement = Arrangement.SpaceBetween,
          verticalAlignment = Alignment.CenterVertically
        ) {
          Surface(
            shape = RoundedCornerShape(20.dp),
            color = ForestDeep.copy(alpha = 0.85f),
            border = androidx.compose.foundation.BorderStroke(1.dp, SlateCardBorder)
          ) {
            Row(
              modifier = Modifier.padding(horizontal = 10.dp, vertical = 5.dp),
              verticalAlignment = Alignment.CenterVertically
            ) {
              Icon(
                imageVector = Icons.Default.LocationOn,
                contentDescription = null,
                tint = AmberGold,
                modifier = Modifier.size(13.dp)
              )
              Spacer(modifier = Modifier.width(4.dp))
              Text(
                text = project.region,
                style = MaterialTheme.typography.labelSmall.copy(
                  color = Color.White,
                  fontWeight = FontWeight.Medium
                )
              )
            }
          }

          IconButton(
            onClick = onBookmarkClick,
            modifier = Modifier
              .size(38.dp)
              .clip(CircleShape)
              .background(ForestDeep.copy(alpha = 0.85f))
              .border(1.dp, SlateCardBorder, CircleShape)
              .testTag("bookmark_button_${project.id}")
          ) {
            Icon(
              imageVector = if (isBookmarked) Icons.Default.Bookmark else Icons.Default.BookmarkBorder,
              contentDescription = if (isBookmarked) "Bookmarked" else "Bookmark project",
              tint = if (isBookmarked) AmberGold else Color.White,
              modifier = Modifier.size(18.dp)
            )
          }
        }

        // Bottom telemetry inside photo: Coordinates & Altitude
        Row(
          modifier = Modifier
            .align(Alignment.BottomStart)
            .padding(14.dp),
          verticalAlignment = Alignment.CenterVertically
        ) {
          Surface(
            shape = RoundedCornerShape(10.dp),
            color = Color.Black.copy(alpha = 0.7f),
            border = androidx.compose.foundation.BorderStroke(0.5.dp, Color.White.copy(alpha = 0.25f))
          ) {
            Row(
              modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp),
              verticalAlignment = Alignment.CenterVertically
            ) {
              Icon(
                imageVector = Icons.Default.Landscape,
                contentDescription = null,
                tint = SlateTextLight,
                modifier = Modifier.size(12.dp)
              )
              Spacer(modifier = Modifier.width(4.dp))
              Text(
                text = project.elevation,
                style = MaterialTheme.typography.labelSmall.copy(
                  color = Color.White,
                  fontSize = 10.sp
                )
              )
              Spacer(modifier = Modifier.width(8.dp))
              Text(
                text = "•",
                color = SlateTextMuted,
                fontSize = 10.sp
              )
              Spacer(modifier = Modifier.width(8.dp))
              Text(
                text = project.coordinates,
                style = MaterialTheme.typography.labelSmall.copy(
                  color = SlateTextLight,
                  fontSize = 10.sp
                )
              )
            }
          }
        }
      }

      // Body Section
      Column(
        modifier = Modifier.padding(18.dp)
      ) {
        Text(
          text = project.title,
          style = MaterialTheme.typography.headlineMedium.copy(
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colorScheme.onSurface
          )
        )
        Text(
          text = project.subtitle,
          style = MaterialTheme.typography.titleMedium.copy(
            color = AmberGold,
            fontWeight = FontWeight.SemiBold
          )
        )

        Spacer(modifier = Modifier.height(10.dp))

        Text(
          text = "\"${project.narrativeQuote}\"",
          style = MaterialTheme.typography.bodyMedium.copy(
            fontStyle = FontStyle.Italic,
            fontFamily = FontFamily.Serif,
            color = MaterialTheme.colorScheme.onSurfaceVariant,
            lineHeight = 20.sp
          )
        )

        Spacer(modifier = Modifier.height(12.dp))

        // Three Pillars Quick Status Row
        Row(
          modifier = Modifier.fillMaxWidth(),
          horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
          LayerBadge(
            layer = NarrativeLayer.INTELLECTUAL,
            compact = true,
            modifier = Modifier.weight(1f)
          )
          LayerBadge(
            layer = NarrativeLayer.VISUAL,
            compact = true,
            modifier = Modifier.weight(1f)
          )
          LayerBadge(
            layer = NarrativeLayer.PROFESSIONAL,
            compact = true,
            modifier = Modifier.weight(1f)
          )
        }

        Spacer(modifier = Modifier.height(14.dp))

        // Tags
        FlowRow(
          horizontalArrangement = Arrangement.spacedBy(6.dp),
          verticalArrangement = Arrangement.spacedBy(6.dp)
        ) {
          project.tags.forEach { tag ->
            Surface(
              shape = RoundedCornerShape(8.dp),
              color = MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.5f),
              border = androidx.compose.foundation.BorderStroke(0.5.dp, MaterialTheme.colorScheme.outline)
            ) {
              Text(
                text = "#$tag",
                style = MaterialTheme.typography.labelSmall.copy(
                  color = MaterialTheme.colorScheme.onSurfaceVariant,
                  fontSize = 10.sp
                ),
                modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp)
              )
            }
          }
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Action Buttons Row
        Row(
          modifier = Modifier.fillMaxWidth(),
          horizontalArrangement = Arrangement.spacedBy(10.dp),
          verticalAlignment = Alignment.CenterVertically
        ) {
          Button(
            onClick = onProjectClick,
            modifier = Modifier
              .weight(1f)
              .height(46.dp)
              .testTag("explore_layers_button_${project.id}"),
            colors = ButtonDefaults.buttonColors(
              containerColor = ForestPine,
              contentColor = Color.White
            ),
            shape = RoundedCornerShape(12.dp)
          ) {
            Text(
              text = "Explore All 3 Layers",
              style = MaterialTheme.typography.labelLarge.copy(
                fontWeight = FontWeight.Bold
              )
            )
            Spacer(modifier = Modifier.width(6.dp))
            Icon(
              imageVector = Icons.Default.ArrowForward,
              contentDescription = null,
              modifier = Modifier.size(16.dp)
            )
          }

          Surface(
            modifier = Modifier
              .height(46.dp)
              .clip(RoundedCornerShape(12.dp))
              .clickable(onClick = onViewPhotoClick)
              .testTag("view_photo_story_button_${project.id}"),
            shape = RoundedCornerShape(12.dp),
            color = MaterialTheme.colorScheme.surfaceVariant,
            border = androidx.compose.foundation.BorderStroke(1.dp, MaterialTheme.colorScheme.outline)
          ) {
            Box(
              modifier = Modifier.padding(horizontal = 14.dp),
              contentAlignment = Alignment.Center
            ) {
              Text(
                text = "EXIF & Story",
                style = MaterialTheme.typography.labelLarge.copy(
                  color = MaterialTheme.colorScheme.onSurface,
                  fontWeight = FontWeight.SemiBold
                )
              )
            }
          }
        }
      }
    }
  }
}
