package com.example.ui

import androidx.lifecycle.ViewModel
import com.example.data.PortfolioRepository
import com.example.model.FieldJournalEntry
import com.example.model.InquiryType
import com.example.model.NarrativeLayer
import com.example.model.ProjectItem
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

enum class AppNavigationTab(val label: String, val iconName: String) {
  EXPLORE("Pillars", "compass"),
  VISUAL("Visuals", "camera"),
  PROFESSIONAL("Hospitality", "domain"),
  JOURNAL("Field Notes", "book"),
  COLLABORATE("Connect", "mail")
}

data class PortfolioUiState(
  val currentTab: AppNavigationTab = AppNavigationTab.EXPLORE,
  val selectedProjectId: String? = null,
  val activeLayerFilter: NarrativeLayer? = null,
  val selectedLightboxProject: ProjectItem? = null,
  val selectedJournalEntry: FieldJournalEntry? = null,
  val bookmarkedProjectIds: Set<String> = setOf("wayanad", "viraj"),
  val selectedInquiryType: InquiryType = PortfolioRepository.inquiryTypes.first(),
  val inquiryMessage: String = "",
  val toastMessage: String? = null
)

class PortfolioViewModel : ViewModel() {

  private val _uiState = MutableStateFlow(
    PortfolioUiState(
      inquiryMessage = PortfolioRepository.inquiryTypes.first().suggestedPrompt
    )
  )
  val uiState: StateFlow<PortfolioUiState> = _uiState.asStateFlow()

  fun selectTab(tab: AppNavigationTab) {
    _uiState.update { it.copy(currentTab = tab, selectedProjectId = null) }
  }

  fun selectProject(projectId: String?) {
    _uiState.update { it.copy(selectedProjectId = projectId) }
  }

  fun setLayerFilter(layer: NarrativeLayer?) {
    _uiState.update { it.copy(activeLayerFilter = layer) }
  }

  fun openLightbox(project: ProjectItem) {
    _uiState.update { it.copy(selectedLightboxProject = project) }
  }

  fun closeLightbox() {
    _uiState.update { it.copy(selectedLightboxProject = null) }
  }

  fun openJournalModal(entry: FieldJournalEntry) {
    _uiState.update { it.copy(selectedJournalEntry = entry) }
  }

  fun closeJournalModal() {
    _uiState.update { it.copy(selectedJournalEntry = null) }
  }

  fun toggleBookmark(projectId: String) {
    _uiState.update { state ->
      val updated = state.bookmarkedProjectIds.toMutableSet()
      val isAdded = if (updated.contains(projectId)) {
        updated.remove(projectId)
        false
      } else {
        updated.add(projectId)
        true
      }
      state.copy(
        bookmarkedProjectIds = updated,
        toastMessage = if (isAdded) "Saved to Briefing Packet" else "Removed from Briefing Packet"
      )
    }
  }

  fun selectInquiryType(type: InquiryType) {
    _uiState.update {
      it.copy(
        selectedInquiryType = type,
        inquiryMessage = type.suggestedPrompt
      )
    }
  }

  fun updateInquiryMessage(message: String) {
    _uiState.update { it.copy(inquiryMessage = message) }
  }

  fun clearToast() {
    _uiState.update { it.copy(toastMessage = null) }
  }

  fun showToast(message: String) {
    _uiState.update { it.copy(toastMessage = message) }
  }
}
