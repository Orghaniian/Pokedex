package com.orghaniian.pokedex.ui.theme

import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.googlefonts.Font
import androidx.compose.ui.text.googlefonts.GoogleFont
import com.orghaniian.pokedex.R

private val provider = GoogleFont.Provider(
    providerAuthority = "com.google.android.gms.fonts",
    providerPackage = "com.google.android.gms",
    certificates = R.array.com_google_android_gms_fonts_certs
)

private val publicSansFont = GoogleFont(name = "Public Sans")

val publicSans = FontFamily(
    Font(fontProvider = provider, googleFont = publicSansFont, weight = FontWeight.Normal),
    Font(fontProvider = provider, googleFont = publicSansFont, weight = FontWeight.Medium),
    Font(fontProvider = provider, googleFont = publicSansFont, weight = FontWeight.SemiBold),
    Font(fontProvider = provider, googleFont = publicSansFont, weight = FontWeight.ExtraBold),
)