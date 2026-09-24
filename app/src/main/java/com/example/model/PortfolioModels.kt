package com.example.model

import androidx.compose.ui.graphics.Color
import com.example.ui.theme.LayerIntellectual
import com.example.ui.theme.LayerProfessional
import com.example.ui.theme.LayerVisual

enum class NarrativeLayer(
  val displayName: String,
  val subtitle: String,
  val color: Color,
  val iconName: String
) {
  INTELLECTUAL(
    displayName = "Intellectual",
    subtitle = "Tourism Research & Carrying Capacity",
    color = LayerIntellectual,
    iconName = "research"
  ),
  VISUAL(
    displayName = "Visual",
    subtitle = "Photography & Cinematography",
    color = LayerVisual,
    iconName = "camera"
  ),
  PROFESSIONAL(
    displayName = "Professional",
    subtitle = "Hospitality Experience & Operations",
    color = LayerProfessional,
    iconName = "hospitality"
  )
}

data class ProjectMetric(
  val label: String,
  val value: String,
  val context: String
)

data class PhotoExif(
  val camera: String,
  val lens: String,
  val shutter: String,
  val aperture: String,
  val iso: String,
  val focalLength: String,
  val elevation: String,
  val coordinates: String
)

data class VisualStory(
  val title: String,
  val narrative: String,
  val exif: PhotoExif,
  val photoTags: List<String>
)

data class ResearchInsight(
  val academicFocus: String,
  val problemStatement: String,
  val methodology: String,
  val findings: List<String>,
  val strategicRecommendations: List<String>,
  val metrics: List<ProjectMetric>
)

data class HospitalityOperations(
  val propertyRole: String,
  val coreInterventions: List<String>,
  val guestJourneyMilestones: List<String>,
  val sustainabilityPractices: List<String>,
  val metrics: List<ProjectMetric>
)

data class ProjectItem(
  val id: String,
  val title: String,
  val subtitle: String,
  val location: String,
  val region: String,
  val coordinates: String,
  val elevation: String,
  val heroDrawableResId: Int,
  val briefSummary: String,
  val narrativeQuote: String,
  val research: ResearchInsight,
  val visual: VisualStory,
  val hospitality: HospitalityOperations,
  val tags: List<String>
)

data class FieldJournalEntry(
  val id: String,
  val date: String,
  val location: String,
  val altitude: String,
  val weather: String,
  val title: String,
  val notes: String,
  val layer: NarrativeLayer
)

data class ProfessionalCredential(
  val title: String,
  val organization: String,
  val year: String,
  val description: String,
  val category: String
)

data class CompetencyItem(
  val name: String,
  val category: String,
  val proficiency: Float,
  val description: String
)

data class InquiryType(
  val id: String,
  val title: String,
  val description: String,
  val defaultSubject: String,
  val suggestedPrompt: String
)
