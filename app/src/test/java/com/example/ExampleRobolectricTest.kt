package com.example

import android.content.Context
import androidx.test.core.app.ApplicationProvider
import com.example.data.PortfolioRepository
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Assert.assertTrue
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config

@RunWith(RobolectricTestRunner::class)
@Config(sdk = [34])
class ExampleRobolectricTest {

  @Test
  fun `read string from context`() {
    val context = ApplicationProvider.getApplicationContext<Context>()
    val appName = context.getString(R.string.app_name)
    assertEquals("Anosh Joseph", appName)
  }

  @Test
  fun `verify core projects in repository`() {
    val projects = PortfolioRepository.projects
    assertEquals(4, projects.size)

    val projectIds = projects.map { it.id }.toSet()
    assertTrue(projectIds.contains("wayanad"))
    assertTrue(projectIds.contains("thangalpara"))
    assertTrue(projectIds.contains("goa"))
    assertTrue(projectIds.contains("viraj"))

    val viraj = projects.first { it.id == "viraj" }
    assertEquals("Hotel Viraj Retreat", viraj.title)
    assertTrue(viraj.hospitality.coreInterventions.isNotEmpty())
    assertEquals(PortfolioRepository.USER_EMAIL, "anoshjoseph80@gmail.com")
  }
}

