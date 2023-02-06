package com.orghaniian.data.remote.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

 @Serializable
 internal data class PokemonSpecies(
     @SerialName("name") val name: String,
     @SerialName("id") val id: Int,
     @SerialName("base_happiness") val baseHappiness: Int,
     @SerialName("capture_rate") val captureRate: Int,
     @SerialName("color") val color: ColorResource,
     @SerialName("egg_groups") val eggGroups: List<NamedAPIResource>,
     @SerialName("evolution_chain") val evolutionChain: APIResource,
     @SerialName("evolves_from_species") val evolvesFromSpecies: NamedAPIResource?,
     @SerialName("flavor_text_entries") val flavorTextEntries: List<FlavorText>,
     @SerialName("form_descriptions") val formDescriptions: List<Description>,
     @SerialName("forms_switchable") val formsSwitchable: Boolean,
     @SerialName("names") val names: List<Name>,
     @SerialName("gender_rate") val genderRate: Int
    ) {
     @Serializable
     internal data class FlavorText(
        @SerialName("flavor_text") val flavorText: String,
        @SerialName("language") val language: NamedAPIResource,
        @SerialName("version") val version: NamedAPIResource
    )

     @Serializable
     internal data class Description(
        @SerialName("description") val description: String,
        @SerialName("language") val language: NamedAPIResource
    )

     @Serializable
     internal data class Name (
        @SerialName("name") val name: String,
        @SerialName("language") val language: LanguageResource
    )
}



