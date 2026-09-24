package com.example.ui.screens

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.content.Intent
import android.net.Uri
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
import androidx.compose.material.icons.filled.Bookmark
import androidx.compose.material.icons.filled.ContentCopy
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Handshake
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.Send
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.data.PortfolioRepository
import com.example.model.InquiryType
import com.example.model.ProjectItem
import com.example.ui.theme.AmberGold
import com.example.ui.theme.ForestDeep
import com.example.ui.theme.ForestPine
import com.example.ui.theme.SlateCardBorder
import com.example.ui.theme.SlateTextLight
import com.example.ui.theme.SlateTextMuted

@Composable
fun CollaborationScreen(
  selectedInquiryType: InquiryType,
  inquiryMessage: String,
  bookmarkedProjects: List<ProjectItem>,
  onSelectInquiryType: (InquiryType) -> Unit,
  onMessageChange: (String) -> Unit,
  onShowToast: (String) -> Unit,
  onSelectProject: (String) -> Unit,
  modifier: Modifier = Modifier
) {
  val context = LocalContext.current
  val inquiryTypes = PortfolioRepository.inquiryTypes

  fun sendEmailIntent() {
    try {
      val intent = Intent(Intent.ACTION_SENDTO).apply {
        data = Uri.parse("mailto:")
        putExtra(Intent.EXTRA_EMAIL, arrayOf(PortfolioRepository.USER_EMAIL))
        putExtra(Intent.EXTRA_SUBJECT, selectedInquiryType.defaultSubject)
        putExtra(Intent.EXTRA_TEXT, inquiryMessage)
      }
      context.startActivity(Intent.createChooser(intent, "Send Email to Anosh Joseph"))
    } catch (e: Exception) {
      onShowToast("Unable to launch email app. Copying address instead.")
      copyEmailToClipboard(context, onShowToast)
    }
  }

  LazyColumn(
    modifier = modifier
      .fillMaxSize()
      .background(MaterialTheme.colorScheme.background)
      .testTag("collaboration_screen"),
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
                .background(AmberGold.copy(alpha = 0.2f)),
              contentAlignment = Alignment.Center
            ) {
              Icon(
                imageVector = Icons.Default.Handshake,
                contentDescription = null,
                tint = AmberGold,
                modifier = Modifier.size(20.dp)
              )
            }
            Spacer(modifier = Modifier.width(12.dp))
            Column {
              Text(
                text = "COMMISSION & COLLABORATE",
                style = MaterialTheme.typography.labelMedium.copy(
                  color = AmberGold,
                  fontWeight = FontWeight.Bold,
                  letterSpacing = 1.sp
                )
              )
              Text(
                text = "Partner with Anosh Joseph",
                style = MaterialTheme.typography.headlineSmall.copy(
                  color = Color.White,
                  fontWeight = FontWeight.Bold
                )
              )
            }
          }

          Spacer(modifier = Modifier.height(14.dp))

          Text(
            text = "Open to research commissions, destination master-planning, boutique hospitality advisory, high-end aerial cinematography, and academic lectures.",
            style = MaterialTheme.typography.bodyMedium.copy(
              color = SlateTextLight,
              fontFamily = FontFamily.Serif,
              lineHeight = 22.sp
            )
          )
        }
      }
    }

    // Direct Contact Card
    item {
      Box(modifier = Modifier.padding(horizontal = 18.dp)) {
        Surface(
          shape = RoundedCornerShape(18.dp),
          color = MaterialTheme.colorScheme.surface,
          border = androidx.compose.foundation.BorderStroke(1.dp, MaterialTheme.colorScheme.outline),
          modifier = Modifier.fillMaxWidth()
        ) {
          Column(modifier = Modifier.padding(18.dp)) {
            Row(
              modifier = Modifier.fillMaxWidth(),
              horizontalArrangement = Arrangement.SpaceBetween,
              verticalAlignment = Alignment.CenterVertically
            ) {
              Column {
                Text(
                  text = "DIRECT CONTACT",
                  style = MaterialTheme.typography.labelSmall.copy(
                    color = SlateTextMuted,
                    fontWeight = FontWeight.Bold,
                    letterSpacing = 1.sp
                  )
                )
                Text(
                  text = PortfolioRepository.USER_EMAIL,
                  style = MaterialTheme.typography.titleMedium.copy(
                    fontWeight = FontWeight.Bold,
                    color = ForestPine
                  )
                )
              }

              IconButton(
                onClick = { copyEmailToClipboard(context, onShowToast) },
                modifier = Modifier
                  .size(40.dp)
                  .clip(CircleShape)
                  .background(ForestPine.copy(alpha = 0.1f))
                  .testTag("copy_email_button")
              ) {
                Icon(
                  imageVector = Icons.Default.ContentCopy,
                  contentDescription = "Copy Email",
                  tint = ForestPine,
                  modifier = Modifier.size(18.dp)
                )
              }
            }

            Spacer(modifier = Modifier.height(10.dp))

            Row(verticalAlignment = Alignment.CenterVertically) {
              Icon(
                imageVector = Icons.Default.LocationOn,
                contentDescription = null,
                tint = AmberGold,
                modifier = Modifier.size(14.dp)
              )
              Spacer(modifier = Modifier.width(6.dp))
              Text(
                text = "Based in Kerala & Western Ghats • Available for field travel across India & abroad",
                style = MaterialTheme.typography.bodySmall.copy(
                  color = MaterialTheme.colorScheme.onSurfaceVariant
                )
              )
            }
          }
        }
      }
    }

    // Interactive Proposal Builder
    item {
      Column(modifier = Modifier.padding(horizontal = 18.dp)) {
        Text(
          text = "TAILORED INQUIRY BUILDER",
          style = MaterialTheme.typography.labelMedium.copy(
            color = SlateTextMuted,
            fontWeight = FontWeight.Bold,
            letterSpacing = 1.sp
          )
        )
        Spacer(modifier = Modifier.height(10.dp))

        // Inquiry Type Selector
        Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
          inquiryTypes.forEach { type ->
            val isSelected = type.id == selectedInquiryType.id
            Surface(
              modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(12.dp))
                .clickable { onSelectInquiryType(type) }
                .testTag("inquiry_type_${type.id}"),
              shape = RoundedCornerShape(12.dp),
              color = if (isSelected) ForestPine else MaterialTheme.colorScheme.surface,
              border = androidx.compose.foundation.BorderStroke(
                1.dp,
                if (isSelected) AmberGold else MaterialTheme.colorScheme.outline
              )
            ) {
              Column(modifier = Modifier.padding(14.dp)) {
                Text(
                  text = type.title,
                  style = MaterialTheme.typography.titleSmall.copy(
                    fontWeight = FontWeight.Bold,
                    color = if (isSelected) Color.White else MaterialTheme.colorScheme.onSurface
                  )
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                  text = type.description,
                  style = MaterialTheme.typography.bodySmall.copy(
                    color = if (isSelected) SlateTextLight else MaterialTheme.colorScheme.onSurfaceVariant,
                    lineHeight = 16.sp
                  )
                )
              }
            }
          }
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Message input area
        Text(
          text = "Custom Proposal Draft",
          style = MaterialTheme.typography.titleSmall.copy(
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colorScheme.onSurface
          )
        )
        Spacer(modifier = Modifier.height(6.dp))

        OutlinedTextField(
          value = inquiryMessage,
          onValueChange = onMessageChange,
          modifier = Modifier
            .fillMaxWidth()
            .height(180.dp)
            .testTag("inquiry_message_textfield"),
          colors = OutlinedTextFieldDefaults.colors(
            focusedBorderColor = ForestPine,
            unfocusedBorderColor = MaterialTheme.colorScheme.outline,
            focusedContainerColor = MaterialTheme.colorScheme.surface,
            unfocusedContainerColor = MaterialTheme.colorScheme.surface
          ),
          shape = RoundedCornerShape(12.dp),
          textStyle = MaterialTheme.typography.bodyMedium.copy(
            fontFamily = FontFamily.SansSerif,
            lineHeight = 20.sp
          )
        )

        Spacer(modifier = Modifier.height(14.dp))

        // Send Button
        Button(
          onClick = { sendEmailIntent() },
          modifier = Modifier
            .fillMaxWidth()
            .height(50.dp)
            .testTag("send_email_button"),
          colors = ButtonDefaults.buttonColors(
            containerColor = ForestPine,
            contentColor = Color.White
          ),
          shape = RoundedCornerShape(12.dp)
        ) {
          Icon(imageVector = Icons.Default.Send, contentDescription = null, modifier = Modifier.size(18.dp))
          Spacer(modifier = Modifier.width(8.dp))
          Text(
            text = "Launch Email to Anosh Joseph",
            style = MaterialTheme.typography.labelLarge.copy(fontWeight = FontWeight.Bold)
          )
        }
      }
    }

    // Briefing Packet (Bookmarked Projects)
    if (bookmarkedProjects.isNotEmpty()) {
      item {
        Column(modifier = Modifier.padding(horizontal = 18.dp)) {
          Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier.fillMaxWidth()
          ) {
            Text(
              text = "SAVED IN BRIEFING PACKET (${bookmarkedProjects.size})",
              style = MaterialTheme.typography.labelMedium.copy(
                color = SlateTextMuted,
                fontWeight = FontWeight.Bold,
                letterSpacing = 1.sp
              )
            )
          }
          Spacer(modifier = Modifier.height(10.dp))
        }
      }

      items(bookmarkedProjects) { proj ->
        Box(modifier = Modifier.padding(horizontal = 18.dp)) {
          Surface(
            shape = RoundedCornerShape(14.dp),
            color = MaterialTheme.colorScheme.surface,
            border = androidx.compose.foundation.BorderStroke(1.dp, MaterialTheme.colorScheme.outline),
            modifier = Modifier
              .fillMaxWidth()
              .clip(RoundedCornerShape(14.dp))
              .clickable { onSelectProject(proj.id) }
              .testTag("briefing_item_${proj.id}")
          ) {
            Row(
              modifier = Modifier.padding(14.dp),
              verticalAlignment = Alignment.CenterVertically,
              horizontalArrangement = Arrangement.SpaceBetween
            ) {
              Column(modifier = Modifier.weight(1f)) {
                Text(
                  text = proj.title,
                  style = MaterialTheme.typography.titleSmall.copy(fontWeight = FontWeight.Bold)
                )
                Text(
                  text = "${proj.location} • ${proj.elevation}",
                  style = MaterialTheme.typography.labelSmall.copy(color = ForestPine)
                )
              }
              Icon(
                imageVector = Icons.Default.Bookmark,
                contentDescription = null,
                tint = AmberGold,
                modifier = Modifier.size(20.dp)
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

private fun copyEmailToClipboard(context: Context, onShowToast: (String) -> Unit) {
  val clipboard = context.getSystemService(Context.CLIPBOARD_SERVICE) as? ClipboardManager
  val clip = ClipData.newPlainText("Anosh Joseph Email", PortfolioRepository.USER_EMAIL)
  clipboard?.setPrimaryClip(clip)
  onShowToast("Email copied: ${PortfolioRepository.USER_EMAIL}")
}
