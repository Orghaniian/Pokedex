package com.orghaniian.domain

import java.util.*
import javax.inject.Inject

class FormatNameUseCase @Inject constructor() {

    operator fun invoke(name: String): String {
        return name.replaceFirstChar { it.titlecase(Locale.getDefault()) }
    }
}
