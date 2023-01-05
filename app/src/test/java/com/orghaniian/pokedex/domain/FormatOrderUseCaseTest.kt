package com.orghaniian.pokedex.domain

import org.junit.Assert.assertEquals
import org.junit.Test

internal class FormatOrderUseCaseTest {
    private val formatOrderUseCase = com.orghaniian.domain.FormatOrderUseCase()

    @Test
    operator fun invoke() {
        val result = formatOrderUseCase(1)

        assertEquals("#001", result)
    }
}
