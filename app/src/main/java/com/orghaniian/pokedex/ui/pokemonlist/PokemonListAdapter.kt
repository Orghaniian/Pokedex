package com.orghaniian.pokedex.ui.pokemonlist

import android.content.Context
import android.graphics.drawable.LayerDrawable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.widget.LinearLayoutCompat
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.orghaniian.pokedex.R
import com.orghaniian.pokedex.domain.FormatNameUseCase
import com.orghaniian.pokedex.domain.FormatOrderUseCase
import com.orghaniian.pokedex.ui.utils.getColorResource
import com.orghaniian.pokedex.ui.utils.getIconDrawable

class PokemonListAdapter(
    private val formatOrderUseCase: FormatOrderUseCase,
    private val formatNameUseCase: FormatNameUseCase
): ListAdapter<PokemonListItemUiState, PokemonListAdapter.ViewHolder>(PokemonListItemUiStateDiffCallback) {

    class ViewHolder(
        private val view: View,
        private val context: Context,
        private val formatOrderUseCase: FormatOrderUseCase,
        private val formatNameUseCase: FormatNameUseCase
    ): RecyclerView.ViewHolder(view) {
        private val nameTextView: TextView = view.findViewById(R.id.name)
        private val orderTextView: TextView = view.findViewById(R.id.order)
        private val typeLinearLayoutCompat: LinearLayoutCompat = view.findViewById(R.id.types)
        private val spriteImageView: ImageView = view.findViewById(R.id.sprite)

        fun bind(item: PokemonListItemUiState) {
            (view.background as LayerDrawable).run {
                findDrawableByLayerId(R.id.card)
                    .setTint(ContextCompat.getColor(context, item.color.getColorResource()))
                findDrawableByLayerId(R.id.pokeball)
                    .setTint(0x4CFFFFFF)
            }

            nameTextView.text = formatNameUseCase(item.name)
            orderTextView.text = formatOrderUseCase(item.order)

            item.types.forEach { type ->
                typeLinearLayoutCompat.addView(View.inflate(context, R.layout.type_chip, null).apply {
                    findViewById<TextView>(R.id.name).text = type.name
                    findViewById<ImageView>(R.id.icon).apply{
                        setImageDrawable(type.getIconDrawable(context))
                        contentDescription = context
                            .getString(R.string.pokemon_type_image_content_description, type.name)
                    }
                })
            }


            // add an invisible if the pokemon has only one type chip to keep height consistent across the screen
            if(item.types.size == 1) typeLinearLayoutCompat
                .addView(
                    View.inflate(context, R.layout.type_chip, null)
                        .apply { visibility = View.INVISIBLE }
                )

            spriteImageView.contentDescription = context
                .getString(R.string.pokemon_image_content_description, item.name)

            Glide.with(itemView)
                .load(item.spriteUrl)
                .into(spriteImageView)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder = LayoutInflater
            .from(parent.context)
            .inflate(R.layout.pokemon_list_item, parent, false)
            .let { ViewHolder(it, parent.context, formatOrderUseCase, formatNameUseCase) }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    object PokemonListItemUiStateDiffCallback : DiffUtil.ItemCallback<PokemonListItemUiState>() {
        override fun areItemsTheSame(oldItem: PokemonListItemUiState, newItem: PokemonListItemUiState): Boolean {
            return oldItem.order == newItem.order
        }

        override fun areContentsTheSame(oldItem: PokemonListItemUiState, newItem: PokemonListItemUiState): Boolean {
            return oldItem == newItem
        }
    }
}
