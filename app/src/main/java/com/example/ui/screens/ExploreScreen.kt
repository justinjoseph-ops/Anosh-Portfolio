package com.example.ui.screens

import androidx.compose.foundation.background
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
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Bookmark
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Landscape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
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
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.data.PortfolioRepository
import com.example.model.NarrativeLayer
import com.example.model.ProjectItem
import com.example.ui.components.InteractiveExpeditionRadar
import com.example.ui.components.LayerFilterChip
import com.example.ui.components.PillarPhilosophyCard
import com.example.ui.components.ProjectCard
import com.example.ui.theme.AmberGold
import com.example.ui.theme.ForestDeep
import com.example.ui.theme.ForestMoss
import com.example.ui.theme.ForestPine
import com.example.ui.theme.SlateTextLight
import com.example.ui.theme.SlateTextMuted

@Composable
fun ExploreScreen(
  activeFilter: NarrativeLayer?,
  bookmarkedIds: Set<String>,
  onFilterChange: (NarrativeLayer?) -> Unit,
  onSelectProject: (String) -> Unit,
  onToggleBookmark: (String) -> Unit,
  onOpenPhotoLightbox: (ProjectItem) -> Unit,
  onNavigateToConnect: () -> Unit,
  modifier: Modifier = Modifier
) {
  val allProjects = PortfolioRepository.projects

  val filteredProjects = if (activeFilter == null) {
    allProjects
  } else {
    // Return projects highlighting this layer
    allProjects
  }

  LazyColumn(
    modifier = modifier
      .fillMaxSize()
      .background(MaterialTheme.colorScheme.background)
      .testTag("explore_screen"),
    verticalArrangement = Arrangement.spacedBy(18.dp)
  ) {
    // Hero Title & Identity Banner
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
          Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
          ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
              Box(
                modifier = Modifier
                  .size(46.dp)
                  .clip(CircleShape)
                  .background(ForestPine),
                contentAlignment = Alignment.Center
              ) {
                Text(
                  text = "AJ",
                  style = MaterialTheme.typography.titleMedium.copy(
                    fontWeight = FontWeight.Bold,
                    color = AmberGold,
                    fontFamily = FontFamily.Serif
                  )
                )
              }
              Spacer(modifier = Modifier.width(12.dp))
              Column {
                Text(
                  text = "ANOSH JOSEPH",
                  style = MaterialTheme.typography.headlineMedium.copy(
                    fontWeight = FontWeight.Bold,
                    color = Color.White,
                    letterSpacing = 1.sp
                  )
                )
                Text(
                  text = "Tourism Professional • Visual Storyteller • Explorer",
                  style = MaterialTheme.typography.labelSmall.copy(
                    color = AmberGold,
                    fontSize = 11.sp
                  )
                )
              }
            }

            // Quick Saved Counter
            if (bookmarkedIds.isNotEmpty()) {
              Surface(
                shape = RoundedCornerShape(12.dp),
                color = ForestPine.copy(alpha = 0.8f),
                border = androidx.compose.foundation.BorderStroke(1.dp, AmberGold.copy(alpha = 0.5f))
              ) {
                Row(
                  modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp),
                  verticalAlignment = Alignment.CenterVertically
                ) {
                  Icon(
                    imageVector = Icons.Default.Bookmark,
                    contentDescription = null,
                    tint = AmberGold,
                    modifier = Modifier.size(13.dp)
                  )
                  Spacer(modifier = Modifier.width(4.dp))
                  Text(
                    text = "${bookmarkedIds.size}",
                    style = MaterialTheme.typography.labelSmall.copy(
                      color = Color.White,
                      fontWeight = FontWeight.Bold
                    )
                  )
                }
              }
            }
          }

          Spacer(modifier = Modifier.height(18.dp))

          Text(
            text = "“Tourism research becomes the intellectual layer, photography and videography become the visual layer, and hospitality operations become the professional layer.”",
            style = MaterialTheme.typography.bodyLarge.copy(
              fontFamily = FontFamily.Serif,
              color = SlateTextLight,
              lineHeight = 23.sp
            )
          )

          Spacer(modifier = Modifier.height(16.dp))

          // Quick Action CTA
          Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(10.dp)
          ) {
            Button(
              onClick = onNavigateToConnect,
              colors = ButtonDefaults.buttonColors(
                containerColor = AmberGold,
                contentColor = ForestDeep
              ),
              shape = RoundedCornerShape(10.dp),
              modifier = Modifier.weight(1f).height(42.dp).testTag("header_connect_button")
            ) {
              Icon(
                imageVector = Icons.Default.Email,
                contentDescription = null,
                modifier = Modifier.size(15.dp)
              )
              Spacer(modifier = Modifier.width(6.dp))
              Text(
                text = "Inquire / Collaborate",
                style = MaterialTheme.typography.labelMedium.copy(fontWeight = FontWeight.Bold)
              )
            }
          }
        }
      }
    }

    // Triad Philosophy Explanation
    item {
      Box(modifier = Modifier.padding(horizontal = 18.dp)) {
        PillarPhilosophyCard(onLearnMoreClick = {})
      }
    }

    // Interactive Expedition Radar
    item {
      Box(modifier = Modifier.padding(horizontal = 18.dp)) {
        InteractiveExpeditionRadar(
          onSelectProject = onSelectProject
        )
      }
    }

    // Layer Filter Chips
    item {
      Column(modifier = Modifier.padding(horizontal = 18.dp)) {
        Text(
          text = "CURATED EXPEDITION PORTFOLIO",
          style = MaterialTheme.typography.labelMedium.copy(
            color = SlateTextMuted,
            fontWeight = FontWeight.Bold,
            letterSpacing = 1.sp
          )
        )
        Spacer(modifier = Modifier.height(10.dp))
        LazyRow(
          horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
          item {
            LayerFilterChip(
              label = "All Four Pillars",
              isSelected = activeFilter == null,
              onClick = { onFilterChange(null) }
            )
          }
          item {
            LayerFilterChip(
              label = "Intellectual Layer",
              isSelected = activeFilter == NarrativeLayer.INTELLECTUAL,
              layer = NarrativeLayer.INTELLECTUAL,
              onClick = { onFilterChange(NarrativeLayer.INTELLECTUAL) }
            )
          }
          item {
            LayerFilterChip(
              label = "Visual Layer",
              isSelected = activeFilter == NarrativeLayer.VISUAL,
              layer = NarrativeLayer.VISUAL,
              onClick = { onFilterChange(NarrativeLayer.VISUAL) }
            )
          }
          item {
            LayerFilterChip(
              label = "Professional Layer",
              isSelected = activeFilter == NarrativeLayer.PROFESSIONAL,
              layer = NarrativeLayer.PROFESSIONAL,
              onClick = { onFilterChange(NarrativeLayer.PROFESSIONAL) }
            )
          }
        }
      }
    }

    // Project Cards List
    items(filteredProjects, key = { it.id }) { project ->
      Box(modifier = Modifier.padding(horizontal = 18.dp)) {
        ProjectCard(
          project = project,
          isBookmarked = bookmarkedIds.contains(project.id),
          onProjectClick = { onSelectProject(project.id) },
          onBookmarkClick = { onToggleBookmark(project.id) },
          onViewPhotoClick = { onOpenPhotoLightbox(project) }
        )
      }
    }

    // Bottom Spacer
    item {
      Spacer(modifier = Modifier.height(30.dp))
    }
  }
}
