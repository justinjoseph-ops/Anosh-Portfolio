package com.example.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.border
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
import androidx.compose.material.icons.filled.Apartment
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material.icons.filled.Badge
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.School
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.filled.Verified
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.LinearProgressIndicator
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
import com.example.data.PortfolioRepository
import com.example.model.NarrativeLayer
import com.example.ui.components.LayerBadge
import com.example.ui.theme.AmberGold
import com.example.ui.theme.ForestDeep
import com.example.ui.theme.ForestPine
import com.example.ui.theme.LayerProfessional
import com.example.ui.theme.SlateCardBorder
import com.example.ui.theme.SlateTextLight
import com.example.ui.theme.SlateTextMuted

@Composable
fun ProfessionalScreen(
  onOpenVirajRetreat: () -> Unit,
  onNavigateToConnect: () -> Unit,
  modifier: Modifier = Modifier
) {
  val credentials = PortfolioRepository.credentials
  val competencies = PortfolioRepository.competencies
  val virajProject = PortfolioRepository.projects.first { it.id == "viraj" }

  LazyColumn(
    modifier = modifier
      .fillMaxSize()
      .background(MaterialTheme.colorScheme.background)
      .testTag("professional_screen"),
    verticalArrangement = Arrangement.spacedBy(20.dp)
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
                .background(LayerProfessional.copy(alpha = 0.2f)),
              contentAlignment = Alignment.Center
            ) {
              Icon(
                imageVector = Icons.Default.Apartment,
                contentDescription = null,
                tint = LayerProfessional,
                modifier = Modifier.size(20.dp)
              )
            }
            Spacer(modifier = Modifier.width(12.dp))
            Column {
              Text(
                text = "PROFESSIONAL LAYER",
                style = MaterialTheme.typography.labelMedium.copy(
                  color = LayerProfessional,
                  fontWeight = FontWeight.Bold,
                  letterSpacing = 1.sp
                )
              )
              Text(
                text = "Hospitality Leadership & Operations",
                style = MaterialTheme.typography.headlineSmall.copy(
                  color = Color.White,
                  fontWeight = FontWeight.Bold
                )
              )
            }
          }

          Spacer(modifier = Modifier.height(14.dp))

          Text(
            text = "“Hospitality is where abstract tourism policy confronts the real world. At Hotel Viraj Retreat, theory transformed into operational standard operating procedures, guest journey blueprints, and tangible community empowerment.”",
            style = MaterialTheme.typography.bodyMedium.copy(
              color = SlateTextLight,
              fontFamily = FontFamily.Serif,
              lineHeight = 22.sp
            )
          )
        }
      }
    }

    // Featured Case Study: Hotel Viraj Retreat
    item {
      Column(modifier = Modifier.padding(horizontal = 18.dp)) {
        Text(
          text = "FLAGSHIP OPERATIONAL TENURE",
          style = MaterialTheme.typography.labelMedium.copy(
            color = SlateTextMuted,
            fontWeight = FontWeight.Bold,
            letterSpacing = 1.sp
          )
        )
        Spacer(modifier = Modifier.height(10.dp))

        Surface(
          modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(20.dp))
            .clickable { onOpenVirajRetreat() }
            .testTag("viraj_retreat_feature_card"),
          shape = RoundedCornerShape(20.dp),
          color = MaterialTheme.colorScheme.surface,
          border = androidx.compose.foundation.BorderStroke(1.dp, MaterialTheme.colorScheme.outline),
          tonalElevation = 5.dp
        ) {
          Column(modifier = Modifier.padding(18.dp)) {
            Row(
              modifier = Modifier.fillMaxWidth(),
              horizontalArrangement = Arrangement.SpaceBetween,
              verticalAlignment = Alignment.CenterVertically
            ) {
              LayerBadge(layer = NarrativeLayer.PROFESSIONAL)
              Surface(
                shape = RoundedCornerShape(8.dp),
                color = ForestPine.copy(alpha = 0.15f)
              ) {
                Text(
                  text = "Western Ghats Foothills",
                  style = MaterialTheme.typography.labelSmall.copy(color = ForestPine, fontWeight = FontWeight.Bold),
                  modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp)
                )
              }
            }

            Spacer(modifier = Modifier.height(12.dp))

            Text(
              text = virajProject.title,
              style = MaterialTheme.typography.headlineMedium.copy(
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.onSurface
              )
            )
            Text(
              text = virajProject.subtitle,
              style = MaterialTheme.typography.titleSmall.copy(
                color = AmberGold,
                fontWeight = FontWeight.SemiBold
              )
            )

            Spacer(modifier = Modifier.height(10.dp))

            Text(
              text = virajProject.briefSummary,
              style = MaterialTheme.typography.bodyMedium.copy(
                color = MaterialTheme.colorScheme.onSurfaceVariant,
                lineHeight = 20.sp
              )
            )

            Spacer(modifier = Modifier.height(16.dp))

            // Viraj Metrics
            Row(
              modifier = Modifier.fillMaxWidth(),
              horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
              virajProject.hospitality.metrics.forEach { metric ->
                Surface(
                  modifier = Modifier.weight(1f),
                  shape = RoundedCornerShape(10.dp),
                  color = MaterialTheme.colorScheme.surfaceVariant
                ) {
                  Column(modifier = Modifier.padding(8.dp)) {
                    Text(
                      text = metric.value,
                      style = MaterialTheme.typography.titleMedium.copy(
                        fontWeight = FontWeight.Bold,
                        color = LayerProfessional
                      )
                    )
                    Text(
                      text = metric.label,
                      style = MaterialTheme.typography.labelSmall.copy(
                        color = MaterialTheme.colorScheme.onSurfaceVariant,
                        fontSize = 10.sp
                      )
                    )
                  }
                }
              }
            }

            Spacer(modifier = Modifier.height(16.dp))

            Button(
              onClick = onOpenVirajRetreat,
              colors = ButtonDefaults.buttonColors(
                containerColor = ForestPine,
                contentColor = Color.White
              ),
              shape = RoundedCornerShape(10.dp),
              modifier = Modifier.fillMaxWidth().testTag("open_viraj_retreat_button")
            ) {
              Text("Inspect Viraj Retreat Operational Case Study", fontWeight = FontWeight.Bold)
              Spacer(modifier = Modifier.width(6.dp))
              Icon(imageVector = Icons.Default.ArrowForward, contentDescription = null, modifier = Modifier.size(16.dp))
            }
          }
        }
      }
    }

    // Core Competency Matrix
    item {
      Column(modifier = Modifier.padding(horizontal = 18.dp)) {
        Text(
          text = "CORE INDUSTRY COMPETENCIES",
          style = MaterialTheme.typography.labelMedium.copy(
            color = SlateTextMuted,
            fontWeight = FontWeight.Bold,
            letterSpacing = 1.sp
          )
        )
        Spacer(modifier = Modifier.height(10.dp))

        Surface(
          shape = RoundedCornerShape(18.dp),
          color = MaterialTheme.colorScheme.surface,
          border = androidx.compose.foundation.BorderStroke(1.dp, MaterialTheme.colorScheme.outline),
          modifier = Modifier.fillMaxWidth()
        ) {
          Column(modifier = Modifier.padding(16.dp)) {
            competencies.forEachIndexed { index, comp ->
              Column {
                Row(
                  modifier = Modifier.fillMaxWidth(),
                  horizontalArrangement = Arrangement.SpaceBetween,
                  verticalAlignment = Alignment.CenterVertically
                ) {
                  Text(
                    text = comp.name,
                    style = MaterialTheme.typography.titleSmall.copy(
                      fontWeight = FontWeight.Bold,
                      color = MaterialTheme.colorScheme.onSurface
                    )
                  )
                  Text(
                    text = "${(comp.proficiency * 100).toInt()}%",
                    style = MaterialTheme.typography.labelSmall.copy(
                      fontWeight = FontWeight.Bold,
                      color = ForestPine
                    )
                  )
                }
                Spacer(modifier = Modifier.height(4.dp))
                LinearProgressIndicator(
                  progress = { comp.proficiency },
                  modifier = Modifier
                    .fillMaxWidth()
                    .height(6.dp)
                    .clip(RoundedCornerShape(3.dp)),
                  color = ForestPine,
                  trackColor = MaterialTheme.colorScheme.surfaceVariant
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                  text = comp.description,
                  style = MaterialTheme.typography.bodySmall.copy(
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                    fontSize = 11.5.sp,
                    lineHeight = 16.sp
                  )
                )
              }
              if (index < competencies.size - 1) {
                Spacer(modifier = Modifier.height(14.dp))
              }
            }
          }
        }
      }
    }

    // Professional Credentials & Education
    item {
      Column(modifier = Modifier.padding(horizontal = 18.dp)) {
        Text(
          text = "ACCREDITATIONS & QUALIFICATIONS",
          style = MaterialTheme.typography.labelMedium.copy(
            color = SlateTextMuted,
            fontWeight = FontWeight.Bold,
            letterSpacing = 1.sp
          )
        )
        Spacer(modifier = Modifier.height(10.dp))
      }
    }

    items(credentials) { cred ->
      Box(modifier = Modifier.padding(horizontal = 18.dp)) {
        Surface(
          shape = RoundedCornerShape(14.dp),
          color = MaterialTheme.colorScheme.surface,
          border = androidx.compose.foundation.BorderStroke(1.dp, MaterialTheme.colorScheme.outline),
          modifier = Modifier.fillMaxWidth()
        ) {
          Row(
            modifier = Modifier.padding(14.dp),
            verticalAlignment = Alignment.Top
          ) {
            val icon = when (cred.category) {
              "Education" -> Icons.Default.School
              "Experience" -> Icons.Default.Apartment
              else -> Icons.Default.Verified
            }
            Box(
              modifier = Modifier
                .size(36.dp)
                .clip(CircleShape)
                .background(ForestPine.copy(alpha = 0.15f)),
              contentAlignment = Alignment.Center
            ) {
              Icon(
                imageVector = icon,
                contentDescription = null,
                tint = ForestPine,
                modifier = Modifier.size(18.dp)
              )
            }
            Spacer(modifier = Modifier.width(12.dp))
            Column(modifier = Modifier.weight(1f)) {
              Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
              ) {
                Text(
                  text = cred.title,
                  style = MaterialTheme.typography.titleSmall.copy(
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.onSurface
                  )
                )
                Text(
                  text = cred.year,
                  style = MaterialTheme.typography.labelSmall.copy(
                    color = AmberGold,
                    fontWeight = FontWeight.SemiBold
                  )
                )
              }
              Text(
                text = cred.organization,
                style = MaterialTheme.typography.labelSmall.copy(
                  color = ForestPine,
                  fontWeight = FontWeight.Medium
                )
              )
              Spacer(modifier = Modifier.height(4.dp))
              Text(
                text = cred.description,
                style = MaterialTheme.typography.bodySmall.copy(
                  color = MaterialTheme.colorScheme.onSurfaceVariant,
                  lineHeight = 17.sp
                )
              )
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
