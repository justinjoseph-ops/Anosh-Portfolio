package com.example

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.BackHandler
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.navigationBars
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Apartment
import androidx.compose.material.icons.filled.Book
import androidx.compose.material.icons.filled.CameraAlt
import androidx.compose.material.icons.filled.Explore
import androidx.compose.material.icons.filled.Handshake
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.data.PortfolioRepository
import com.example.ui.AppNavigationTab
import com.example.ui.PortfolioViewModel
import com.example.ui.components.JournalEntryDialog
import com.example.ui.components.PhotoStoryModal
import com.example.ui.screens.CollaborationScreen
import com.example.ui.screens.ExploreScreen
import com.example.ui.screens.JournalScreen
import com.example.ui.screens.ProfessionalScreen
import com.example.ui.screens.ProjectDetailScreen
import com.example.ui.screens.VisualGalleryScreen
import com.example.ui.theme.AmberGold
import com.example.ui.theme.ForestDeep
import com.example.ui.theme.ForestPine
import com.example.ui.theme.MyApplicationTheme
import com.example.ui.theme.SlateCardBorder
import com.example.ui.theme.SlateTextLight
import com.example.ui.theme.SlateTextMuted
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    enableEdgeToEdge()
    setContent {
      MyApplicationTheme {
        MainAppRoot()
      }
    }
  }
}

@Composable
fun MainAppRoot(
  viewModel: PortfolioViewModel = viewModel()
) {
  val uiState by viewModel.uiState.collectAsState()
  val snackbarHostState = remember { SnackbarHostState() }
  val scope = rememberCoroutineScope()

  // Handle toast notification messages
  LaunchedEffect(uiState.toastMessage) {
    uiState.toastMessage?.let { msg ->
      scope.launch {
        snackbarHostState.showSnackbar(msg)
      }
      viewModel.clearToast()
    }
  }

  // Intercept back button if in project detail view
  BackHandler(enabled = uiState.selectedProjectId != null) {
    viewModel.selectProject(null)
  }

  val selectedProject = uiState.selectedProjectId?.let { id ->
    PortfolioRepository.projects.firstOrNull { it.id == id }
  }

  val bookmarkedProjects = PortfolioRepository.projects.filter {
    uiState.bookmarkedProjectIds.contains(it.id)
  }

  Scaffold(
    modifier = Modifier.fillMaxSize(),
    snackbarHost = { SnackbarHost(snackbarHostState) },
    bottomBar = {
      // Hide bottom navigation if viewing full project detail
      if (selectedProject == null) {
        NavigationBar(
          modifier = Modifier
            .windowInsetsPadding(WindowInsets.navigationBars)
            .testTag("main_navigation_bar"),
          containerColor = ForestDeep,
          tonalElevation = 8.dp
        ) {
          AppNavigationTab.entries.forEach { tab ->
            val isSelected = uiState.currentTab == tab
            val icon = when (tab) {
              AppNavigationTab.EXPLORE -> Icons.Default.Explore
              AppNavigationTab.VISUAL -> Icons.Default.CameraAlt
              AppNavigationTab.PROFESSIONAL -> Icons.Default.Apartment
              AppNavigationTab.JOURNAL -> Icons.Default.Book
              AppNavigationTab.COLLABORATE -> Icons.Default.Handshake
            }

            NavigationBarItem(
              selected = isSelected,
              onClick = { viewModel.selectTab(tab) },
              icon = {
                Icon(
                  imageVector = icon,
                  contentDescription = tab.label,
                  modifier = Modifier.size(20.dp)
                )
              },
              label = {
                Text(
                  text = tab.label,
                  style = MaterialTheme.typography.labelSmall.copy(
                    fontSize = 10.5.sp,
                    fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Normal
                  )
                )
              },
              colors = NavigationBarItemDefaults.colors(
                selectedIconColor = ForestDeep,
                selectedTextColor = AmberGold,
                indicatorColor = AmberGold,
                unselectedIconColor = SlateTextMuted,
                unselectedTextColor = SlateTextMuted
              ),
              modifier = Modifier.testTag("nav_item_${tab.name.lowercase()}")
            )
          }
        }
      }
    }
  ) { innerPadding ->
    Box(
      modifier = Modifier
        .fillMaxSize()
        .padding(innerPadding)
    ) {
      if (selectedProject != null) {
        ProjectDetailScreen(
          project = selectedProject,
          isBookmarked = uiState.bookmarkedProjectIds.contains(selectedProject.id),
          onBack = { viewModel.selectProject(null) },
          onToggleBookmark = { viewModel.toggleBookmark(selectedProject.id) },
          onOpenPhotoLightbox = { viewModel.openLightbox(selectedProject) },
          onShareInquiry = {
            viewModel.selectTab(AppNavigationTab.COLLABORATE)
            viewModel.selectProject(null)
          }
        )
      } else {
        when (uiState.currentTab) {
          AppNavigationTab.EXPLORE -> {
            ExploreScreen(
              activeFilter = uiState.activeLayerFilter,
              bookmarkedIds = uiState.bookmarkedProjectIds,
              onFilterChange = { viewModel.setLayerFilter(it) },
              onSelectProject = { viewModel.selectProject(it) },
              onToggleBookmark = { viewModel.toggleBookmark(it) },
              onOpenPhotoLightbox = { viewModel.openLightbox(it) },
              onNavigateToConnect = { viewModel.selectTab(AppNavigationTab.COLLABORATE) }
            )
          }
          AppNavigationTab.VISUAL -> {
            VisualGalleryScreen(
              onSelectProjectPhoto = { viewModel.openLightbox(it) }
            )
          }
          AppNavigationTab.PROFESSIONAL -> {
            ProfessionalScreen(
              onOpenVirajRetreat = { viewModel.selectProject("viraj") },
              onNavigateToConnect = { viewModel.selectTab(AppNavigationTab.COLLABORATE) }
            )
          }
          AppNavigationTab.JOURNAL -> {
            JournalScreen(
              onSelectEntry = { viewModel.openJournalModal(it) }
            )
          }
          AppNavigationTab.COLLABORATE -> {
            CollaborationScreen(
              selectedInquiryType = uiState.selectedInquiryType,
              inquiryMessage = uiState.inquiryMessage,
              bookmarkedProjects = bookmarkedProjects,
              onSelectInquiryType = { viewModel.selectInquiryType(it) },
              onMessageChange = { viewModel.updateInquiryMessage(it) },
              onShowToast = { viewModel.showToast(it) },
              onSelectProject = { viewModel.selectProject(it) }
            )
          }
        }
      }

      // Lightbox Dialog
      uiState.selectedLightboxProject?.let { project ->
        PhotoStoryModal(
          project = project,
          onDismiss = { viewModel.closeLightbox() },
          onExploreProject = {
            viewModel.closeLightbox()
            viewModel.selectProject(project.id)
          }
        )
      }

      // Journal Entry Dialog
      uiState.selectedJournalEntry?.let { entry ->
        JournalEntryDialog(
          entry = entry,
          onDismiss = { viewModel.closeJournalModal() }
        )
      }
    }
  }
}
