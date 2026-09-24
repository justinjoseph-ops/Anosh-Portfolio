package com.example.data

import com.example.R
import com.example.model.CompetencyItem
import com.example.model.FieldJournalEntry
import com.example.model.HospitalityOperations
import com.example.model.InquiryType
import com.example.model.NarrativeLayer
import com.example.model.PhotoExif
import com.example.model.ProfessionalCredential
import com.example.model.ProjectItem
import com.example.model.ProjectMetric
import com.example.model.ResearchInsight
import com.example.model.VisualStory

object PortfolioRepository {

  const val USER_NAME = "Anosh Joseph"
  const val USER_TITLE = "Tourism Professional · Visual Storyteller · Explorer"
  const val USER_EMAIL = "anoshjoseph80@gmail.com"
  const val NARRATIVE_MANIFESTO =
    "True exploration is not mere observation—it is a threefold synthesis. Tourism research provides the intellectual rigor to understand carrying capacity, ecology, and cultural heritage. Photography and videography craft the evocative visual narrative that connects people to place. Hospitality operations ground that vision in real-world service delivery, guest experience architecture, and sustainable community stewardship."

  val projects: List<ProjectItem> = listOf(
    ProjectItem(
      id = "wayanad",
      title = "Wayanad Highland Ecosystems",
      subtitle = "Agro-Tourism Resilience & Carrying Capacity",
      location = "Wayanad District, Kerala",
      region = "Western Ghats UNESCO Hotspot",
      coordinates = "11.6854° N, 76.1320° E",
      elevation = "950m – 2,100m ASL",
      heroDrawableResId = R.drawable.img_hero_wayanad,
      briefSummary = "An empirical tourism research and visual documentary framework analyzing agro-tourism carrying capacity, tribal community empowerment, and biodiversity buffer management across the mist-clad Wayanad highlands.",
      narrativeQuote = "Where highland mist meets indigenous wisdom, tourism must serve as an ecological shield rather than a consumption vector.",
      research = ResearchInsight(
        academicFocus = "Ecotourism Carrying Capacity & Indigenous Knowledge Integration",
        problemStatement = "Rapid unmanaged weekend tourist influxes along the Chembra peak corridor have strained fragile shola forest hydrology and concentrated revenues into external operators, bypassing indigenous tribal hamlets.",
        methodology = "Mixed-method field appraisal combining GPS transect mapping of soil compaction, semi-structured interviews with 48 local spice planters, and carrying-capacity modeling using Boullón's mathematical standard.",
        findings = listOf(
          "Physical carrying capacity exceeded by 210% during post-monsoon festive weekends.",
          "Indigenous Kurichya and Paniya communities retain less than 8% of direct tour expenditures without structured agro-tourism cooperatives.",
          "Agro-forestry spice buffer zones successfully absorb 40% of visitor congestion when integrated into curated farm-walk itineraries."
        ),
        strategicRecommendations = listOf(
          "Establish timed electronic entry permits linked to shola microclimate weather station thresholds.",
          "Scale tribal-led regenerative foraging and spice trail circuits with guaranteed 60% revenue sharing.",
          "Incentivize homestays adhering to zero single-use plastic and decentralized greywater reed-bed filtration."
        ),
        metrics = listOf(
          ProjectMetric("Carrying Capacity Cap", "1,200/day", "Strict ecological ceiling"),
          ProjectMetric("Community Retention", "+42%", "Projected model benefit"),
          ProjectMetric("Shola Flora Monitored", "14 Species", "Endemic bio-indicators")
        )
      ),
      visual = VisualStory(
        title = "The Emerald Canopy: Light Through Western Ghats Mist",
        narrative = "Documenting the untamed interplay between swirling morning cloud inversions and emerald tea terraces. The visual documentation focused on capturing both epic panoramic topography and intimate macro textures of endemic spices and tea pluckers at first dawn.",
        exif = PhotoExif(
          camera = "Sony Alpha 7 IV",
          lens = "FE 24-70mm f/2.8 GM II",
          shutter = "1/640s",
          aperture = "f/4.0",
          iso = "100",
          focalLength = "35mm",
          elevation = "1,150m ASL",
          coordinates = "11.6854° N, 76.1320° E"
        ),
        photoTags = listOf("Highland Mist", "Golden Hour", "Western Ghats", "Agro-Landscape", "Documentary")
      ),
      hospitality = HospitalityOperations(
        propertyRole = "Eco-Homestay & Highland Farm Stay Advisory",
        coreInterventions = listOf(
          "Formulated Standard Operating Procedures (SOPs) for 12 community-based estate cottages.",
          "Designed experiential harvest breakfasts featuring 100% estate-grown robusta coffee and heirloom spices.",
          "Conducted waste segregation and hygiene workshops for local estate hospitality staff."
        ),
        guestJourneyMilestones = listOf(
          "Pre-Arrival: Digital orientation on microclimate packing and tribal cultural etiquette.",
          "Arrival: Spiced ginger decoction welcome ceremony and biodiversity field guide briefing.",
          "Immersion: Guided plantation twilight walk with local naturalist and audio field recorder."
        ),
        sustainabilityPractices = listOf(
          "100% elimination of bottled drinking water via gravity ceramic filtration stations.",
          "Direct composting of dining organics back to pepper vine root bases.",
          "Low-lumen amber nocturnal pathway lighting to prevent insect disorientation."
        ),
        metrics = listOf(
          ProjectMetric("Local Food Sourcing", "94%", "Within 15km perimeter"),
          ProjectMetric("Staff Retention Rate", "96%", "Trained rural youths"),
          ProjectMetric("Guest Satisfaction", "4.9/5", "Across verified farm stays")
        )
      ),
      tags = listOf("Highland Ecology", "Tribal Tourism", "Carrying Capacity", "Western Ghats")
    ),

    ProjectItem(
      id = "thangalpara",
      title = "Thangalpara Geotourism & Pilgrim Heritage",
      subtitle = "Sacred Monolith Conservation & Flow Dynamics",
      location = "Vagamon, Idukki District, Kerala",
      region = "High Ranges of Travancore",
      coordinates = "9.6890° N, 76.9074° E",
      elevation = "1,200m ASL",
      heroDrawableResId = R.drawable.img_thangalpara,
      briefSummary = "An analytical investigation into the delicate intersection between Sufi pilgrimage sacred geography, geological conservation of a massive spherical granite monolith, and sustainable adventure tourism.",
      narrativeQuote = "A single ancient stone balancing against the sky: a lesson in how human devotion and geological time must coexist with environmental reverence.",
      research = ResearchInsight(
        academicFocus = "Pilgrim-Tourism Convergence & Geomorphological Protection",
        problemStatement = "The dramatic spherical granite monolith of Thangalpara attracts both spiritual pilgrims to Sheikh Fariduddin's Dargah and adventure trekkers, causing trailside littering and severe erosion of sensitive montane grasses.",
        methodology = "Observational visitor count transects during annual Urs gatherings, slope stability assessments, and structured stakeholder dialogues between shrine trustees, local forest officials, and adventure guides.",
        findings = listOf(
          "Over 65% of trail degradation occurs during concentrated 48-hour pilgrim festivals.",
          "Absence of zonation resulted in conflicting visitor expectations between meditative spiritual seekers and casual day hikers.",
          "Zero-waste deposit systems tested at base checkpoints demonstrated an 82% reduction in discarded PET plastic bottles."
        ),
        strategicRecommendations = listOf(
          "Implement sacred silence zones within 200 meters of the monolithic shrine.",
          "Construct porous elevated timber footpaths over the most vulnerable grassy saddles to stop erosion.",
          "Establish an inter-faith youth eco-warden squad supported by shrine donation offsets."
        ),
        metrics = listOf(
          ProjectMetric("Slope Recovery Target", "85%", "Vegetative turf protection"),
          ProjectMetric("Plastic Interception", "82%", "With checkpoint deposit"),
          ProjectMetric("Pilgrim Flow Smoothing", "3 Distinct", "Zonated arrival tracks")
        )
      ),
      visual = VisualStory(
        title = "The Whispering Monolith: Sacred Solitude in the Clouds",
        narrative = "Cinematic aerial and telephoto perspectives framing the solitary boulder perched dramatically above steep precipices. The visual narrative honors the spiritual reverence of the site while dramatizing the stark elemental power of the Sahyadri mountains.",
        exif = PhotoExif(
          camera = "Sony Alpha 7 IV + DJI Mavic 3",
          lens = "FE 70-200mm f/4 G OSS",
          shutter = "1/800s",
          aperture = "f/5.6",
          iso = "125",
          focalLength = "135mm",
          elevation = "1,200m ASL",
          coordinates = "9.6890° N, 76.9074° E"
        ),
        photoTags = listOf("Granite Monolith", "Pilgrimage", "Vagamon Ridge", "Monochrome & Mood", "Aerial View")
      ),
      hospitality = HospitalityOperations(
        propertyRole = "Pilgrim-Explorer Visitor Center & Base Camp Logistics",
        coreInterventions = listOf(
          "Developed visitor staging and orientation area at the foothill base.",
          "Curated modest, respectful refreshment stalls offering herbal infusions and traditional jaggery snacks.",
          "Instituted clear multilingual signage balancing spiritual decorum and outdoor safety."
        ),
        guestJourneyMilestones = listOf(
          "Base Staging: Shoe deposit, eco-briefing, and wooden walking stick distribution.",
          "Ascent: Marked resting shelters with interpretive geological and spiritual history placards.",
          "Summit Sanctuary: Quiet reflective area overlooking the panoramic tea valleys below."
        ),
        sustainabilityPractices = listOf(
          "Refillable copper water dispensing points eliminating packaged single-use beverages.",
          "Solar-powered trail beaconing for twilight pilgrim descent safety.",
          "Strict Leave-No-Trace protocol enforced by resident local youth guides."
        ),
        metrics = listOf(
          ProjectMetric("Trail Cleanliness Index", "98/100", "Post-intervention audit"),
          ProjectMetric("Visitor Satisfaction", "4.8/5", "Spiritual & trekker groups"),
          ProjectMetric("Eco-Warden Employment", "18 Youths", "Full-season stipends")
        )
      ),
      tags = listOf("Geotourism", "Pilgrim Heritage", "Vagamon", "Granite Monolith")
    ),

    ProjectItem(
      id = "goa",
      title = "Goa Hinterland & Riverine Heritage",
      subtitle = "Decentralizing Coastal Crowds to River Estuaries",
      location = "Divar Island, Chorão & Netravali, Goa",
      region = "Mandovi-Zuari Estuarine Basin",
      coordinates = "15.5255° N, 73.9142° E",
      elevation = "Sea level to 120m ASL",
      heroDrawableResId = R.drawable.img_goa_heritage,
      briefSummary = "A counter-narrative to commercial beach party tourism, investigating the preservation of Indo-Portuguese vernacular architecture, traditional Khazan agro-aquacultural dykes, and serene riverine community life.",
      narrativeQuote = "Beyond the crowded surf lies Goa's true soul: slow river tides, whitewashed baroque chapels, and centuries of architectural syncretism.",
      research = ResearchInsight(
        academicFocus = "Hinterland Tourism Diversification & Vernacular Heritage Preservation",
        problemStatement = "Mass coastal saturation in North Goa has provoked severe infrastructure strain and socio-cultural alienation, while historic island villages in the Mandovi river face demographic decline and unmaintained ancestral homes.",
        methodology = "Architectural heritage surveying of 28 heritage manors, oral history ethnography with traditional canoe fishermen, and riverine carrying capacity assessment for non-motorized kayak trails.",
        findings = listOf(
          "91% of domestic visitors never venture further than 5km from coastal beach strips.",
          "Over 35 historic Portuguese-era homes in island villages risk structural demolition due to lack of adaptive-reuse incentives.",
          "Slow-tourism river loops generated 3.4x higher per-capita spending directly within resident village bakeries and ferry cooperatives."
        ),
        strategicRecommendations = listOf(
          "Introduce heritage conservation tax credits for ancestral manor homeowners hosting boutique cultural travelers.",
          "Design designated non-motorized mangrove kayak corridors with certified village boatmen guides.",
          "Curate 'Slow Goa' harvest and bread-making culinary walks during the tropical monsoon offseason."
        ),
        metrics = listOf(
          ProjectMetric("Hinterland Visitor Shift", "+35%", "Target decentralization"),
          ProjectMetric("Heritage Homes Cataloged", "28 Manors", "Architectural archive"),
          ProjectMetric("Estuary Bird Species", "124 Species", "Dr. Salim Ali Sanctuary")
        )
      ),
      visual = VisualStory(
        title = "Beyond the Tide: The Faded Ochre of Portuguese Goa",
        narrative = "Documentary photography capturing quiet dawn ferry crossings, timeworn oyster-shell window panes (carepas), and elderly villagers conversing on balcões. The lighting balances tropical afternoon warmth with evocative twilight river reflections.",
        exif = PhotoExif(
          camera = "Sony Alpha 7 IV",
          lens = "FE 35mm f/1.4 GM",
          shutter = "1/500s",
          aperture = "f/2.0",
          iso = "160",
          focalLength = "35mm",
          elevation = "12m ASL",
          coordinates = "15.5255° N, 73.9142° E"
        ),
        photoTags = listOf("Indo-Portuguese", "Riverine Ecology", "Divar Island", "Balcão Culture", "Golden Hour")
      ),
      hospitality = HospitalityOperations(
        propertyRole = "Heritage Homestay Circuit & Riverine Itinerary Design",
        coreInterventions = listOf(
          "Developed operational frameworks for converting heritage ancestral properties into boutique bed & breakfasts.",
          "Trained local hosts in international guest hospitality etiquette while preserving authentic Goan warmth.",
          "Curated hyper-local dining menus celebrating Goan-Catholic and Saraswat culinary traditions."
        ),
        guestJourneyMilestones = listOf(
          "Arrival: Traditional ferry crossing across the Mandovi river and welcoming by resident host.",
          "Exploration: Cycling through Latin quarters, bread-making workshop with the village poder (baker).",
          "Dusk: Sunset river canoe drift listening to traditional fado and Konkani mandó songs."
        ),
        sustainabilityPractices = listOf(
          "Electric bicycle rentals for all island transit, minimizing noise and carbon footprint.",
          "Rainwater harvesting integrated into renovated heritage manor cisterns.",
          "Direct procurement from local island organic vegetable growers and artisanal bakers."
        ),
        metrics = listOf(
          ProjectMetric("Community Direct Spend", "78%", "Retained on island"),
          ProjectMetric("Carbon Offset", "-65%", "vs conventional cab tours"),
          ProjectMetric("Guest Revisit Rate", "38%", "Heritage circuit travelers")
        )
      ),
      tags = listOf("Hinterland Goa", "Vernacular Architecture", "Riverine Tourism", "Culinary Heritage")
    ),

    ProjectItem(
      id = "viraj",
      title = "Hotel Viraj Retreat",
      subtitle = "Hospitality Operations & Guest Experience Architecture",
      location = "Eco-Sanctuary, Western Ghats Foothills",
      region = "Boutique Hospitality & Wellness",
      coordinates = "11.5120° N, 76.0145° E",
      elevation = "820m ASL",
      heroDrawableResId = R.drawable.img_viraj_retreat,
      briefSummary = "A comprehensive operational blueprint and guest experience case study illustrating the practical application of luxury hospitality management, service blueprinting, and sustainable resort practices at Hotel Viraj Retreat.",
      narrativeQuote = "Luxury in modern hospitality is not gold-leaf excess—it is thoughtful intentionality, authentic human warmth, and seamless environmental harmony.",
      research = ResearchInsight(
        academicFocus = "Sustainable Resort Operations & Guest Experience Blueprinting",
        problemStatement = "Boutique luxury resorts frequently struggle to reconcile discerning high-net-worth guest expectations with authentic ecological sustainability and local supply chain integration.",
        methodology = "Comprehensive operational audit comparing service delivery against Global Sustainable Tourism Council (GSTC) criteria, Guest Journey Mapping across 14 touchpoints, and staff workflow efficiency studies.",
        findings = listOf(
          "Pre-arrival communication personalization correlates with a 44% decrease in check-in friction and higher spa/activity uptake.",
          "Localizing 80%+ of resort pantry sourcing reduced procurement transport emissions by 4.2 metric tons per quarter.",
          "Interactive guest participation in on-site native tree planting increased positive Net Promoter Score (NPS) from 71 to 89."
        ),
        strategicRecommendations = listOf(
          "Institutionalize a 'Green Butler' certification program for all front-of-house operational team members.",
          "Implement guest room energy smart sensors tuned to tropical diurnal temperature oscillations.",
          "Publish a transparent quarterly Sustainability Scorecard on the resort app for guests and stakeholders."
        ),
        metrics = listOf(
          ProjectMetric("Net Promoter Score", "89 NPS", "Post-operational audit"),
          ProjectMetric("Pantry Localization", "86%", "Within 50km radius"),
          ProjectMetric("Plastic-Free Status", "100%", "Certified guest areas")
        )
      ),
      visual = VisualStory(
        title = "Sanctuary of Light: Architectural Harmony in the Tropics",
        narrative = "Hospitality and architectural photography capturing the resort's seamless transition between exterior tropical flora and warm amber interior illumination. Focus on sustainable teak craftsmanship, tranquil water bodies reflecting dusk skies, and guest serenity.",
        exif = PhotoExif(
          camera = "Sony Alpha 7 IV",
          lens = "FE 16-35mm f/2.8 GM",
          shutter = "1/30s (Tripod)",
          aperture = "f/8.0",
          iso = "100",
          focalLength = "24mm",
          elevation = "820m ASL",
          coordinates = "11.5120° N, 76.0145° E"
        ),
        photoTags = listOf("Boutique Resort", "Architectural Veranda", "Twilight Illumination", "Luxury Eco-Stay", "Hospitality")
      ),
      hospitality = HospitalityOperations(
        propertyRole = "Operations Management, Guest Experience & Sustainability Audit",
        coreInterventions = listOf(
          "Architected complete front-of-house service blueprint from pre-booking inquiry to post-departure follow-up.",
          "Overhauled front-desk Standard Operating Procedures (SOPs) reducing average guest check-in duration from 7.5 to 2.2 minutes.",
          "Engineered a zero single-use plastic policy across all 24 luxury suites, villas, and fine dining outlets.",
          "Trained 32 resort staff members in cultural stewardship, storytelling, and emergency first-aid protocols."
        ),
        guestJourneyMilestones = listOf(
          "Touchpoint 1 (Pre-Arrival): Bespoke dietary and experiential preference questionnaire.",
          "Touchpoint 2 (Welcome): Hand-poured spiced lemongrass herbal tea, chilled sandalwood towels, private villa check-in.",
          "Touchpoint 3 (Stay Experience): Unobtrusive housekeeping, curated fireside storytelling, night stargazing walks.",
          "Touchpoint 4 (Departure): Personalized handwritten departure note with wild seed-paper souvenir."
        ),
        sustainabilityPractices = listOf(
          "Closed-loop rainwater harvesting with 250,000-liter subterranean cistern.",
          "In-house glass bottling facility for UV-sterilized mountain spring drinking water.",
          "Solar thermal water heating systems servicing 100% of guest bathrooms and commercial laundry.",
          "Active sponsorship of the neighboring tribal primary school library and sports program."
        ),
        metrics = listOf(
          ProjectMetric("RevPAR Growth", "+28%", "Year-over-year performance"),
          ProjectMetric("Staff Retention", "94%", "Industry-leading stability"),
          ProjectMetric("Energy Efficiency", "+32%", "Post-audit smart systems")
        )
      ),
      tags = listOf("Hotel Viraj Retreat", "Hospitality Operations", "Guest Experience", "Resort Management")
    )
  )

  val fieldJournal: List<FieldJournalEntry> = listOf(
    FieldJournalEntry(
      id = "fj-01",
      date = "October 14",
      location = "Chembra Foothills, Wayanad",
      altitude = "1,150m ASL",
      weather = "Dense morning fog, 19°C, 92% humidity",
      title = "The Edge of the Canopy: Field Notes on Carrying Capacity",
      notes = "Met with Mani, a Kurichya elder whose family has tended pepper vines on these slopes for four generations. As the clouds shifted over the ridge, he showed me how wild cardamom roots anchor the topsoil during relentless monsoon deluges. When mass tourism buses arrive on weekends, the vibration and trampling compaction threaten this exact root network. Research cannot exist in a vacuum; it must listen to the soil.",
      layer = NarrativeLayer.INTELLECTUAL
    ),
    FieldJournalEntry(
      id = "fj-02",
      date = "November 03",
      location = "Thangalpara Summit, Vagamon",
      altitude = "1,200m ASL",
      weather = "Gusting mountain wind, 21°C, clear horizon",
      title = "Monolithic Balance: Documenting Sacred Geometry",
      notes = "Waited at the summit until 5:45 PM for the shadows to lengthen. The spherical boulder casts an almost mystical oval across the rolling grassland. A group of pilgrims from Malappuram arrived barefoot, whispering prayers in the breeze. Captured the frame with a 70-200mm lens to compress the depth between the sacred stone and the distant Sahyadri ridges. Photography here is not spectacle; it is quiet reverence.",
      layer = NarrativeLayer.VISUAL
    ),
    FieldJournalEntry(
      id = "fj-03",
      date = "December 18",
      location = "Divar Island Ferry Point, Goa",
      altitude = "4m ASL",
      weather = "Mild tropical breeze, 27°C, golden dusk",
      title = "Beyond the Coast: The Slow Rhythms of Riverine Goa",
      notes = "Rode the government ferry alongside bicycles laden with fresh hot pao from the local bakery. The contrast between this peaceful Mandovi estuary and the raucous coastal strip just 10km away is staggering. Spent the afternoon interviewing Maria, who maintains her great-grandfather's 1892 manor house. If we can link these heritage homes into small, high-value guest circuits, the architecture survives.",
      layer = NarrativeLayer.INTELLECTUAL
    ),
    FieldJournalEntry(
      id = "fj-04",
      date = "January 22",
      location = "Hotel Viraj Retreat, Veranda",
      altitude = "820m ASL",
      weather = "Cool evening mist, 17°C, fragrant pine scent",
      title = "The Anatomy of a Perfect Welcome: Operations in Action",
      notes = "Spent the morning shadowing the front office team. Redesigned the arrival flow today: instead of having guests stand at a formal mahogany counter filling paperwork after a five-hour mountain drive, we now escort them immediately to the open-air veranda lounge with hot lemongrass tea. The check-in tablet is brought to them. The relief on their faces proved that hospitality is, at its core, empathy engineered into workflow.",
      layer = NarrativeLayer.PROFESSIONAL
    )
  )

  val credentials: List<ProfessionalCredential> = listOf(
    ProfessionalCredential(
      title = "Bachelor of Tourism & Hospitality Management",
      organization = "Premier Tourism Institute",
      year = "Graduated with Honors",
      description = "Specialized in Destination Carrying Capacity Modeling, Sustainable Ecotourism Frameworks, and Resort Operations Architecture.",
      category = "Education"
    ),
    ProfessionalCredential(
      title = "Hospitality Operations & Guest Experience Lead",
      organization = "Hotel Viraj Retreat",
      year = "Key Operational Tenure",
      description = "Spearheaded front office modernization, standard operating procedure (SOP) authoring, staff sustainable hospitality training, and circular waste audits.",
      category = "Experience"
    ),
    ProfessionalCredential(
      title = "Global Sustainable Tourism Council (GSTC) Training",
      organization = "Sustainable Tourism Accreditation",
      year = "Certified",
      description = "Comprehensive mastery of destination criteria, environmental management systems, and community socio-economic benefit indicators.",
      category = "Certification"
    ),
    ProfessionalCredential(
      title = "Remote Pilot Aerial Cinematography License",
      organization = "Civil Aviation Directorate (DGCA)",
      year = "Certified Commercial Operator",
      description = "Licensed for commercial UAV cinematic mapping and high-altitude landscape documentary capture.",
      category = "Certification"
    ),
    ProfessionalCredential(
      title = "Wilderness First Responder (WFR)",
      organization = "Outdoor Emergency Care",
      year = "Certified",
      description = "Comprehensive wilderness emergency triage and backcountry expedition risk mitigation.",
      category = "Certification"
    )
  )

  val competencies: List<CompetencyItem> = listOf(
    CompetencyItem(
      name = "Tourism Carrying Capacity Modeling",
      category = "Intellectual Layer",
      proficiency = 0.95f,
      description = "Boullón mathematical carrying capacity calculations, spatial GIS transect assessment, and visitor flow smoothing."
    ),
    CompetencyItem(
      name = "Visual Storytelling & Direction",
      category = "Visual Layer",
      proficiency = 0.92f,
      description = "Editorial photography, cinematic drone piloting, color grading, and multimedia cultural documentation."
    ),
    CompetencyItem(
      name = "Guest Journey Architecture (SOPs)",
      category = "Professional Layer",
      proficiency = 0.96f,
      description = "Luxury boutique resort service blueprinting, front office operations, and guest satisfaction metrics (NPS)."
    ),
    CompetencyItem(
      name = "Sustainable Supply Chain Auditing",
      category = "Professional Layer",
      proficiency = 0.88f,
      description = "Zero-plastic compliance verification, local food mileage auditing, and circular water management."
    ),
    CompetencyItem(
      name = "Rapid Rural Appraisal & Stakeholder Mapping",
      category = "Intellectual Layer",
      proficiency = 0.90f,
      description = "Participatory community research, tribal stakeholder mediation, and indigenous benefit-sharing frameworks."
    ),
    CompetencyItem(
      name = "Field Expedition Leadership",
      category = "Visual & Explorer",
      proficiency = 0.94f,
      description = "High-altitude route reconnaissance, safety risk management, and environmental leave-no-trace protocols."
    )
  )

  val inquiryTypes: List<InquiryType> = listOf(
    InquiryType(
      id = "tourism_strategy",
      title = "Tourism Board & Carrying Capacity Study",
      description = "Commission comprehensive carrying capacity assessments, sustainable visitor zoning, or regenerative ecotourism strategies.",
      defaultSubject = "Inquiry: Tourism Research & Carrying Capacity Consultation",
      suggestedPrompt = "Hi Anosh,\n\nWe are exploring a sustainable destination planning project for [Destination Name]. We would value your expertise in carrying capacity modeling, stakeholder research, and community-centric ecotourism frameworks.\n\nKey Focus Areas:\n- [e.g. Visitor flow management / Ecological baseline study]\n- Timeline: [Estimated Timeline]\n\nBest regards,\n[Your Name]\n[Organization]"
    ),
    InquiryType(
      id = "resort_consulting",
      title = "Hotel & Resort Operations Audit",
      description = "Optimize guest journey blueprints, front-of-house SOPs, eco-resort sustainability metrics, and staff training based on Viraj Retreat methodologies.",
      defaultSubject = "Inquiry: Hotel Operations & Guest Experience Consulting",
      suggestedPrompt = "Hi Anosh,\n\nI reviewed your operational work at Hotel Viraj Retreat and would like to discuss a consultation for our property, [Property/Resort Name].\n\nScope of Interest:\n- [e.g. Service blueprinting / SOP modernization / Eco-operations audit]\n- Property Profile: [Boutique Resort / Homestay / Eco-Lodge]\n\nLooking forward to speaking,\n[Your Name]\n[Title / Property]"
    ),
    InquiryType(
      id = "visual_documentary",
      title = "Visual Storytelling & Aerial Media",
      description = "Hire Anosh for high-end landscape photography, boutique resort architectural shoots, or cultural heritage documentary films.",
      defaultSubject = "Inquiry: Visual Storytelling & Aerial Cinematography",
      suggestedPrompt = "Hi Anosh,\n\nWe love your visual storytelling across Wayanad, Thangalpara, and Goa. We are looking for a visual storyteller for an upcoming campaign/project.\n\nProject Scope:\n- [e.g. Editorial photography / Aerial footage / Resort showcase]\n- Target Location: [Location]\n- Projected Date: [Date]\n\nWarm regards,\n[Your Name]\n[Brand/Publication]"
    ),
    InquiryType(
      id = "speaking_workshop",
      title = "Keynote, Lecture or Field Workshop",
      description = "Invite Anosh to deliver guest lectures, university masterclasses, or professional workshops on the intersection of tourism research, visual arts, and hospitality.",
      defaultSubject = "Invitation: Speaking Engagement / Masterclass with Anosh Joseph",
      suggestedPrompt = "Dear Anosh,\n\nWe would like to invite you to speak or conduct a masterclass on the topic of 'Synthesizing Research, Visual Storytelling, and Hospitality in Modern Tourism' for our [Event/Institution Name].\n\nEvent Details:\n- Date: [Proposed Date]\n- Audience: [Students / Industry Professionals]\n\nWarm regards,\n[Your Name]\n[Institution/Event]"
    )
  )
}
