package com.orghaniian.pokedex.domain

import org.junit.Assert.assertEquals
import org.junit.Test


internal class FormatNameUseCaseTest {
    private val formatNameUseCase = com.orghaniian.domain.FormatNameUseCase()

    @Test
    operator fun invoke() {
        val result = formatNameUseCase("bulbasaur")

        assertEquals("Bulbasaur", result)
    }
}
