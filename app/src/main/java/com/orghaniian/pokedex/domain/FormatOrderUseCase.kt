package com.orghaniian.pokedex.domain

import javax.inject.Inject

/**
 * Use case to format the order of a pokemon to "#XXX" where X is the order
 */
class FormatOrderUseCase @Inject constructor() {

    operator fun invoke(order: Int): String {
        return "#${order.toString().padStart(3, '0')}"
    }
}
