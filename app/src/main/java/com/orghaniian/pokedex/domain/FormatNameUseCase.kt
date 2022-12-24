package com.orghaniian.pokedex.domain

import java.util.*

class FormatNameUseCase {

    operator fun invoke(name: String): String {
        return name.replaceFirstChar { it.titlecase(Locale.getDefault()) }
    }
}