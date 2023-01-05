package com.orghaniian.pokedex.data.remote.model

import com.google.gson.annotations.SerializedName

data class PokemonSpecies(
    @SerializedName("name") val name: String,
    @SerializedName("id") val id: Int,
    @SerializedName("base_happiness") val baseHappiness: Int,
    @SerializedName("capture_rate") val captureRate: Int,
    @SerializedName("color") val color: ColorResource,
    @SerializedName("egg_groups") val eggGroups: List<NamedAPIResource>,
    @SerializedName("evolution_chain") val evolutionChain: APIResource,
    @SerializedName("evolves_from_species") val evolvesFromSpecies: NamedAPIResource?,
    @SerializedName("flavor_text_entries") val flavorTextEntries: List<FlavorText>,
    @SerializedName("form_descriptions") val formDescriptions: List<Description>,
    @SerializedName("forms_switchable") val formsSwitchable: Boolean,
    @SerializedName("names") val names: List<Name>,
    @SerializedName("gender_rate") val genderRate: Int
    ) {
    data class FlavorText(
        @SerializedName("flavor_text") val flavorText: String,
        @SerializedName("language") val language: NamedAPIResource,
        @SerializedName("version") val version: NamedAPIResource
    )

    data class Description(
        @SerializedName("description") val description: String,
        @SerializedName("language") val language: NamedAPIResource
    )

    data class Name (
        @SerializedName("name") val name: String,
        @SerializedName("language") val language: LanguageResource
    )
}



