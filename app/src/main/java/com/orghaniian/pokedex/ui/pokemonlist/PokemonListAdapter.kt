package com.orghaniian.pokedex.ui.pokemonlist

import android.content.Context
import android.graphics.drawable.LayerDrawable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.navigation.findNavController
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.orghaniian.pokedex.R
import com.orghaniian.pokedex.data.model.Type
import com.orghaniian.pokedex.domain.FormatNameUseCase
import com.orghaniian.pokedex.domain.FormatOrderUseCase
import com.orghaniian.pokedex.ui.utils.getColorResource
import com.orghaniian.pokedex.ui.utils.getIconDrawable

class PokemonListAdapter(
    private val formatOrderUseCase: FormatOrderUseCase,
    private val formatNameUseCase: FormatNameUseCase
): PagingDataAdapter<PokemonListItemUiState, PokemonListAdapter.ViewHolder>(PokemonListItemUiStateDiffCallback) {

    class ViewHolder(
        private val view: View,
        private val context: Context,
        private val formatOrderUseCase: FormatOrderUseCase,
        private val formatNameUseCase: FormatNameUseCase
    ): RecyclerView.ViewHolder(view) {
        private val nameTextView: TextView = view.findViewById(R.id.name)
        private val orderTextView: TextView = view.findViewById(R.id.order)
        private val spriteImageView: ImageView = view.findViewById(R.id.sprite)
        private val typeChip1: LinearLayout = view.findViewById(R.id.type1)
        private val typeChip2: LinearLayout = view.findViewById(R.id.type2)

        fun bind(item: PokemonListItemUiState?) {
            if (item == null) return

            (view.background as LayerDrawable).run {
                findDrawableByLayerId(R.id.card)
                    .setTint(ContextCompat.getColor(context, item.color.getColorResource()))
                findDrawableByLayerId(R.id.pokeball)
                    .setTint(0x4CFFFFFF)
            }

            nameTextView.text = formatNameUseCase(item.name)
            orderTextView.text = formatOrderUseCase(item.order)

            typeChip1.bindType(item.types.getOrNull(0))
            typeChip2.bindType(item.types.getOrNull(1))

            spriteImageView.contentDescription = context
                .getString(R.string.pokemon_image_content_description, item.name)

            Glide.with(itemView)
                .load(item.spriteUrl)
                .into(spriteImageView)

            itemView.setOnClickListener {
                val action = PokemonListFragmentDirections
                    .actionPokemonListFragmentToPokemonDetailsFragment(
                        item.order, formatNameUseCase(item.name)
                    )
                it.findNavController().navigate(action)
            }
        }

        private fun LinearLayout.bindType(type: Type?) {
            if (type != null ) {
                findViewById<TextView>(R.id.name).text = type.name
                findViewById<ImageView>(R.id.icon).apply {
                    setImageDrawable(type.getIconDrawable(context))
                    contentDescription = context
                        .getString(R.string.pokemon_type_image_content_description, type.name)
                }
            } else {
                visibility = View.INVISIBLE
            }
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
