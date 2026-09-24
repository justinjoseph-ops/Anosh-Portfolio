package com.example.ui.screens

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
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Apartment
import androidx.compose.material.icons.filled.Bookmark
import androidx.compose.material.icons.filled.BookmarkBorder
import androidx.compose.material.icons.filled.CameraAlt
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Landscape
import androidx.compose.material.icons.filled.MenuBook
import androidx.compose.material.icons.filled.Share
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.TabRowDefaults
import androidx.compose.material3.TabRowDefaults.tabIndicatorOffset
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
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
import com.example.ui.components.LayerBadge
import com.example.ui.components.ProjectMetricPill
import com.example.ui.theme.AmberGold
import com.example.ui.theme.ForestDeep
import com.example.ui.theme.ForestPine
import com.example.ui.theme.LayerIntellectual
import com.example.ui.theme.LayerProfessional
import com.example.ui.theme.LayerVisual
import com.example.ui.theme.SlateCardBorder
import com.example.ui.theme.SlateTextLight
import com.example.ui.theme.SlateTextMuted

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun ProjectDetailScreen(
  project: ProjectItem,
  isBookmarked: Boolean,
  onBack: () -> Unit,
  onToggleBookmark: () -> Unit,
  onOpenPhotoLightbox: () -> Unit,
  onShareInquiry: () -> Unit,
  modifier: Modifier = Modifier
) {
  var selectedTabIndex by remember { mutableIntStateOf(0) }
  val tabs = listOf(
    NarrativeLayer.INTELLECTUAL,
    NarrativeLayer.VISUAL,
    NarrativeLayer.PROFESSIONAL
  )

  Column(
    modifier = modifier
      .fillMaxSize()
      .background(MaterialTheme.colorScheme.background)
      .verticalScroll(rememberScrollState())
      .testTag("project_detail_screen_${project.id}")
  ) {
    // Top Hero Image Header
    Box(
      modifier = Modifier
        .fillMaxWidth()
        .aspectRatio(16f / 11f)
    ) {
      Image(
        painter = painterResource(id = project.heroDrawableResId),
        contentDescription = project.title,
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
                Color.Black.copy(alpha = 0.85f)
              )
            )
          )
      )

      // Back & Bookmark Action Bar
      Row(
        modifier = Modifier
          .fillMaxWidth()
          .padding(16.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
      ) {
        IconButton(
          onClick = onBack,
          modifier = Modifier
            .size(42.dp)
            .clip(CircleShape)
            .background(ForestDeep.copy(alpha = 0.8f))
            .border(1.dp, SlateCardBorder, CircleShape)
            .testTag("detail_back_button")
        ) {
          Icon(
            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
            contentDescription = "Back",
            tint = Color.White
          )
        }

        Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
          IconButton(
            onClick = onToggleBookmark,
            modifier = Modifier
              .size(42.dp)
              .clip(CircleShape)
              .background(ForestDeep.copy(alpha = 0.8f))
              .border(1.dp, SlateCardBorder, CircleShape)
              .testTag("detail_bookmark_button")
          ) {
            Icon(
              imageVector = if (isBookmarked) Icons.Default.Bookmark else Icons.Default.BookmarkBorder,
              contentDescription = "Bookmark",
              tint = if (isBookmarked) AmberGold else Color.White
            )
          }

          IconButton(
            onClick = onShareInquiry,
            modifier = Modifier
              .size(42.dp)
              .clip(CircleShape)
              .background(ForestDeep.copy(alpha = 0.8f))
              .border(1.dp, SlateCardBorder, CircleShape)
              .testTag("detail_inquiry_button")
          ) {
            Icon(
              imageVector = Icons.Default.Share,
              contentDescription = "Discuss Project",
              tint = Color.White
            )
          }
        }
      }

      // Bottom Telemetry on Hero Image
      Column(
        modifier = Modifier
          .align(Alignment.BottomStart)
          .padding(18.dp)
      ) {
        Surface(
          shape = RoundedCornerShape(10.dp),
          color = ForestDeep.copy(alpha = 0.85f),
          border = androidx.compose.foundation.BorderStroke(1.dp, AmberGold.copy(alpha = 0.5f))
        ) {
          Row(
            modifier = Modifier.padding(horizontal = 10.dp, vertical = 5.dp),
            verticalAlignment = Alignment.CenterVertically
          ) {
            Icon(
              imageVector = Icons.Default.Landscape,
              contentDescription = null,
              tint = AmberGold,
              modifier = Modifier.size(13.dp)
            )
            Spacer(modifier = Modifier.width(6.dp))
            Text(
              text = "${project.location} • ${project.elevation}",
              style = MaterialTheme.typography.labelSmall.copy(
                color = Color.White,
                fontWeight = FontWeight.Medium
              )
            )
          }
        }

        Spacer(modifier = Modifier.height(8.dp))

        Text(
          text = project.title,
          style = MaterialTheme.typography.headlineLarge.copy(
            color = Color.White,
            fontWeight = FontWeight.Bold
          )
        )
        Text(
          text = project.subtitle,
          style = MaterialTheme.typography.titleMedium.copy(
            color = AmberGold
          )
        )
      }
    }

    // Briefing & Manifesto Quote
    Surface(
      color = MaterialTheme.colorScheme.surface,
      modifier = Modifier.fillMaxWidth()
    ) {
      Column(modifier = Modifier.padding(20.dp)) {
        Text(
          text = "\"${project.narrativeQuote}\"",
          style = MaterialTheme.typography.bodyLarge.copy(
            fontStyle = FontStyle.Italic,
            fontFamily = FontFamily.Serif,
            color = MaterialTheme.colorScheme.onSurface,
            lineHeight = 24.sp
          )
        )

        Spacer(modifier = Modifier.height(12.dp))

        Text(
          text = project.briefSummary,
          style = MaterialTheme.typography.bodyMedium.copy(
            color = MaterialTheme.colorScheme.onSurfaceVariant,
            lineHeight = 21.sp
          )
        )

        Spacer(modifier = Modifier.height(14.dp))

        FlowRow(
          horizontalArrangement = Arrangement.spacedBy(6.dp),
          verticalArrangement = Arrangement.spacedBy(6.dp)
        ) {
          project.tags.forEach { tag ->
            Surface(
              shape = RoundedCornerShape(8.dp),
              color = MaterialTheme.colorScheme.surfaceVariant,
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
      }
    }

    Spacer(modifier = Modifier.height(8.dp))

    // Triad Layers Tab Bar
    TabRow(
      selectedTabIndex = selectedTabIndex,
      containerColor = MaterialTheme.colorScheme.surface,
      contentColor = MaterialTheme.colorScheme.primary,
      indicator = { tabPositions ->
        TabRowDefaults.SecondaryIndicator(
          modifier = Modifier.tabIndicatorOffset(tabPositions[selectedTabIndex]),
          color = tabs[selectedTabIndex].color,
          height = 3.dp
        )
      }
    ) {
      tabs.forEachIndexed { index, layer ->
        val isSelected = selectedTabIndex == index
        Tab(
          selected = isSelected,
          onClick = { selectedTabIndex = index },
          text = {
            Row(verticalAlignment = Alignment.CenterVertically) {
              val icon = when (layer) {
                NarrativeLayer.INTELLECTUAL -> Icons.Default.MenuBook
                NarrativeLayer.VISUAL -> Icons.Default.CameraAlt
                NarrativeLayer.PROFESSIONAL -> Icons.Default.Apartment
              }
              Icon(
                imageVector = icon,
                contentDescription = null,
                tint = if (isSelected) layer.color else SlateTextMuted,
                modifier = Modifier.size(15.dp)
              )
              Spacer(modifier = Modifier.width(6.dp))
              Text(
                text = layer.displayName,
                style = MaterialTheme.typography.labelMedium.copy(
                  fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Medium,
                  color = if (isSelected) layer.color else SlateTextMuted
                )
              )
            }
          }
        )
      }
    }

    // Layer-specific Content View
    Box(modifier = Modifier.padding(20.dp)) {
      when (tabs[selectedTabIndex]) {
        NarrativeLayer.INTELLECTUAL -> IntellectualLayerView(project)
        NarrativeLayer.VISUAL -> VisualLayerView(project, onOpenPhotoLightbox)
        NarrativeLayer.PROFESSIONAL -> ProfessionalLayerView(project)
      }
    }

    // Connect / Collaborate Banner at bottom of detail screen
    Surface(
      modifier = Modifier
        .fillMaxWidth()
        .padding(20.dp),
      shape = RoundedCornerShape(18.dp),
      color = ForestPine,
      border = androidx.compose.foundation.BorderStroke(1.dp, AmberGold.copy(alpha = 0.4f))
    ) {
      Column(modifier = Modifier.padding(20.dp)) {
        Text(
          text = "Commission Research or Storytelling",
          style = MaterialTheme.typography.titleMedium.copy(
            fontWeight = FontWeight.Bold,
            color = Color.White
          )
        )
        Spacer(modifier = Modifier.height(6.dp))
        Text(
          text = "Discuss applying the ${project.title} methodologies to your destination or hospitality project.",
          style = MaterialTheme.typography.bodyMedium.copy(
            color = SlateTextLight,
            lineHeight = 20.sp
          )
        )
        Spacer(modifier = Modifier.height(14.dp))
        Button(
          onClick = onShareInquiry,
          colors = ButtonDefaults.buttonColors(
            containerColor = AmberGold,
            contentColor = ForestDeep
          ),
          shape = RoundedCornerShape(10.dp),
          modifier = Modifier.fillMaxWidth().testTag("detail_propose_inquiry_button")
        ) {
          Text(
            text = "Inquire with Anosh Joseph",
            style = MaterialTheme.typography.labelLarge.copy(fontWeight = FontWeight.Bold)
          )
        }
      }
    }
  }
}

@Composable
private fun IntellectualLayerView(project: ProjectItem) {
  val research = project.research
  Column {
    LayerBadge(layer = NarrativeLayer.INTELLECTUAL)
    Spacer(modifier = Modifier.height(10.dp))

    Text(
      text = research.academicFocus,
      style = MaterialTheme.typography.headlineMedium.copy(
        fontWeight = FontWeight.Bold,
        color = MaterialTheme.colorScheme.onSurface
      )
    )

    Spacer(modifier = Modifier.height(16.dp))

    // Key Metrics Strip
    Text(
      text = "EMPIRICAL RESEARCH METRICS",
      style = MaterialTheme.typography.labelMedium.copy(
        color = LayerIntellectual,
        fontWeight = FontWeight.Bold
      )
    )
    Spacer(modifier = Modifier.height(8.dp))
    Row(
      modifier = Modifier.fillMaxWidth(),
      horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
      research.metrics.forEach { metric ->
        ProjectMetricPill(
          metric = metric,
          modifier = Modifier.weight(1f)
        )
      }
    }

    Spacer(modifier = Modifier.height(20.dp))

    // Problem Statement Card
    ContentSectionCard(
      title = "Research Problem Statement",
      body = research.problemStatement,
      accentColor = LayerIntellectual
    )

    Spacer(modifier = Modifier.height(14.dp))

    // Methodology Card
    ContentSectionCard(
      title = "Field Methodology",
      body = research.methodology,
      accentColor = LayerIntellectual
    )

    Spacer(modifier = Modifier.height(16.dp))

    // Findings List
    Text(
      text = "Key Empirical Findings",
      style = MaterialTheme.typography.titleMedium.copy(
        fontWeight = FontWeight.Bold,
        color = MaterialTheme.colorScheme.onSurface
      )
    )
    Spacer(modifier = Modifier.height(10.dp))
    research.findings.forEach { finding ->
      BulletPointItem(text = finding, bulletColor = LayerIntellectual)
      Spacer(modifier = Modifier.height(8.dp))
    }

    Spacer(modifier = Modifier.height(16.dp))

    // Strategic Recommendations
    Text(
      text = "Policy & Strategic Recommendations",
      style = MaterialTheme.typography.titleMedium.copy(
        fontWeight = FontWeight.Bold,
        color = MaterialTheme.colorScheme.onSurface
      )
    )
    Spacer(modifier = Modifier.height(10.dp))
    research.strategicRecommendations.forEach { rec ->
      BulletPointItem(text = rec, bulletColor = AmberGold)
      Spacer(modifier = Modifier.height(8.dp))
    }
  }
}

@Composable
private fun VisualLayerView(
  project: ProjectItem,
  onOpenLightbox: () -> Unit
) {
  val visual = project.visual
  val exif = visual.exif

  Column {
    LayerBadge(layer = NarrativeLayer.VISUAL)
    Spacer(modifier = Modifier.height(10.dp))

    Text(
      text = visual.title,
      style = MaterialTheme.typography.headlineMedium.copy(
        fontFamily = FontFamily.Serif,
        fontWeight = FontWeight.Bold,
        color = MaterialTheme.colorScheme.onSurface
      )
    )

    Spacer(modifier = Modifier.height(14.dp))

    Text(
      text = visual.narrative,
      style = MaterialTheme.typography.bodyLarge.copy(
        color = MaterialTheme.colorScheme.onSurfaceVariant,
        lineHeight = 24.sp
      )
    )

    Spacer(modifier = Modifier.height(18.dp))

    // Lightbox launch card
    Surface(
      shape = RoundedCornerShape(16.dp),
      color = ForestDeep,
      border = androidx.compose.foundation.BorderStroke(1.dp, SlateCardBorder),
      modifier = Modifier.fillMaxWidth()
    ) {
      Column(modifier = Modifier.padding(16.dp)) {
        Row(
          modifier = Modifier.fillMaxWidth(),
          horizontalArrangement = Arrangement.SpaceBetween,
          verticalAlignment = Alignment.CenterVertically
        ) {
          Text(
            text = "CAMERA & EXIF TELEMETRY",
            style = MaterialTheme.typography.labelMedium.copy(
              color = AmberGold,
              fontWeight = FontWeight.Bold
            )
          )
          Button(
            onClick = onOpenLightbox,
            colors = ButtonDefaults.buttonColors(
              containerColor = ForestPine,
              contentColor = Color.White
            ),
            shape = RoundedCornerShape(8.dp)
          ) {
            Icon(
              imageVector = Icons.Default.CameraAlt,
              contentDescription = null,
              modifier = Modifier.size(14.dp)
            )
            Spacer(modifier = Modifier.width(6.dp))
            Text("View Lightbox", style = MaterialTheme.typography.labelSmall)
          }
        }

        Spacer(modifier = Modifier.height(12.dp))

        Row(
          modifier = Modifier.fillMaxWidth(),
          horizontalArrangement = Arrangement.SpaceBetween
        ) {
          TelemetryMini(label = "CAMERA", value = exif.camera)
          TelemetryMini(label = "LENS", value = exif.lens)
        }
        Spacer(modifier = Modifier.height(8.dp))
        Row(
          modifier = Modifier.fillMaxWidth(),
          horizontalArrangement = Arrangement.SpaceBetween
        ) {
          TelemetryMini(label = "EXPOSURE", value = "${exif.shutter} @ ${exif.aperture}")
          TelemetryMini(label = "ISO / FOCAL", value = "ISO ${exif.iso} • ${exif.focalLength}")
        }
        Spacer(modifier = Modifier.height(8.dp))
        Row(
          modifier = Modifier.fillMaxWidth(),
          horizontalArrangement = Arrangement.SpaceBetween
        ) {
          TelemetryMini(label = "ELEVATION", value = exif.elevation)
          TelemetryMini(label = "GPS", value = exif.coordinates)
        }
      }
    }
  }
}

@Composable
private fun ProfessionalLayerView(project: ProjectItem) {
  val hospitality = project.hospitality

  Column {
    LayerBadge(layer = NarrativeLayer.PROFESSIONAL)
    Spacer(modifier = Modifier.height(10.dp))

    Text(
      text = hospitality.propertyRole,
      style = MaterialTheme.typography.headlineMedium.copy(
        fontWeight = FontWeight.Bold,
        color = MaterialTheme.colorScheme.onSurface
      )
    )

    Spacer(modifier = Modifier.height(16.dp))

    // Operational Metrics
    Text(
      text = "OPERATIONAL PERFORMANCE",
      style = MaterialTheme.typography.labelMedium.copy(
        color = LayerProfessional,
        fontWeight = FontWeight.Bold
      )
    )
    Spacer(modifier = Modifier.height(8.dp))
    Row(
      modifier = Modifier.fillMaxWidth(),
      horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
      hospitality.metrics.forEach { metric ->
        ProjectMetricPill(
          metric = metric,
          modifier = Modifier.weight(1f)
        )
      }
    }

    Spacer(modifier = Modifier.height(20.dp))

    // Core Interventions
    Text(
      text = "Core Operational Interventions",
      style = MaterialTheme.typography.titleMedium.copy(
        fontWeight = FontWeight.Bold,
        color = MaterialTheme.colorScheme.onSurface
      )
    )
    Spacer(modifier = Modifier.height(10.dp))
    hospitality.coreInterventions.forEach { item ->
      BulletPointItem(text = item, bulletColor = LayerProfessional)
      Spacer(modifier = Modifier.height(8.dp))
    }

    Spacer(modifier = Modifier.height(16.dp))

    // Guest Journey Milestones
    Text(
      text = "Guest Journey Milestones",
      style = MaterialTheme.typography.titleMedium.copy(
        fontWeight = FontWeight.Bold,
        color = MaterialTheme.colorScheme.onSurface
      )
    )
    Spacer(modifier = Modifier.height(10.dp))
    hospitality.guestJourneyMilestones.forEach { item ->
      BulletPointItem(text = item, bulletColor = AmberGold)
      Spacer(modifier = Modifier.height(8.dp))
    }

    Spacer(modifier = Modifier.height(16.dp))

    // Sustainability Practices
    Text(
      text = "Sustainability & Circular Practices",
      style = MaterialTheme.typography.titleMedium.copy(
        fontWeight = FontWeight.Bold,
        color = MaterialTheme.colorScheme.onSurface
      )
    )
    Spacer(modifier = Modifier.height(10.dp))
    hospitality.sustainabilityPractices.forEach { item ->
      BulletPointItem(text = item, bulletColor = ForestPine)
      Spacer(modifier = Modifier.height(8.dp))
    }
  }
}

@Composable
private fun ContentSectionCard(
  title: String,
  body: String,
  accentColor: Color
) {
  Surface(
    shape = RoundedCornerShape(14.dp),
    color = MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.6f),
    border = androidx.compose.foundation.BorderStroke(1.dp, MaterialTheme.colorScheme.outline),
    modifier = Modifier.fillMaxWidth()
  ) {
    Column(modifier = Modifier.padding(16.dp)) {
      Text(
        text = title,
        style = MaterialTheme.typography.titleSmall.copy(
          fontWeight = FontWeight.Bold,
          color = accentColor
        )
      )
      Spacer(modifier = Modifier.height(6.dp))
      Text(
        text = body,
        style = MaterialTheme.typography.bodyMedium.copy(
          color = MaterialTheme.colorScheme.onSurfaceVariant,
          lineHeight = 21.sp
        )
      )
    }
  }
}

@Composable
private fun BulletPointItem(
  text: String,
  bulletColor: Color
) {
  Row(
    verticalAlignment = Alignment.Top,
    modifier = Modifier.fillMaxWidth()
  ) {
    Icon(
      imageVector = Icons.Default.CheckCircle,
      contentDescription = null,
      tint = bulletColor,
      modifier = Modifier
        .size(16.dp)
        .padding(top = 2.dp)
    )
    Spacer(modifier = Modifier.width(10.dp))
    Text(
      text = text,
      style = MaterialTheme.typography.bodyMedium.copy(
        color = MaterialTheme.colorScheme.onSurface,
        lineHeight = 20.sp
      )
    )
  }
}

@Composable
private fun TelemetryMini(
  label: String,
  value: String
) {
  Column {
    Text(
      text = label,
      style = MaterialTheme.typography.labelSmall.copy(
        color = SlateTextMuted,
        fontSize = 8.5.sp,
        fontWeight = FontWeight.Bold
      )
    )
    Text(
      text = value,
      style = MaterialTheme.typography.labelMedium.copy(
        color = SlateTextLight,
        fontSize = 11.sp
      )
    )
  }
}
