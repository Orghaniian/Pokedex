package com.orghaniian.pokedex.domain

/**
 * Use case to format the order of a pokemon to "#XXX" where X is the order
 */
class FormatOrderUseCase {

    operator fun invoke(order: Int): String {
        return "#${order.toString().padStart(3, '0')}"
    }
}