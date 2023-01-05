package com.orghaniian.pokedex.ui.pokemondetails

import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.doOnPreDraw
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.bumptech.glide.Glide
import com.google.android.material.color.MaterialColors
import com.google.android.material.tabs.TabLayoutMediator
import com.google.android.material.transition.MaterialContainerTransform
import com.orghaniian.pokedex.R
import com.orghaniian.pokedex.databinding.FragmentPokemonDetailsBinding
import com.orghaniian.pokedex.domain.FormatNameUseCase
import com.orghaniian.pokedex.domain.FormatOrderUseCase
import com.orghaniian.pokedex.ui.pokemondetails.about.AboutFragment
import com.orghaniian.pokedex.ui.pokemondetails.basestats.BaseStatsFragment
import com.orghaniian.pokedex.ui.pokemondetails.evolution.EvolutionFragment
import com.orghaniian.pokedex.ui.pokemondetails.moves.MovesFragment
import com.orghaniian.pokedex.ui.utils.bindType
import com.orghaniian.pokedex.ui.utils.getColorResource
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class PokemonDetailsFragment : Fragment() {

    @Inject
    lateinit var formatOrderUseCase: FormatOrderUseCase
    @Inject
    lateinit var formatNameUseCase: FormatNameUseCase

    private val viewModel: PokemonDetailsViewModel by viewModels()
    private var _binding: FragmentPokemonDetailsBinding? = null


    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        sharedElementEnterTransition = MaterialContainerTransform().apply {
            setAllContainerColors(
                MaterialColors.getColor(
                    requireContext(),
                    android.R.attr.colorBackground,
                    Color.TRANSPARENT
                )
            )
            scrimColor = Color.TRANSPARENT
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentPokemonDetailsBinding.inflate(inflater, container, false)

        binding.viewPager.adapter = PokemonDetailsStateAdapter(this@PokemonDetailsFragment)

        TabLayoutMediator(binding.tabBar, binding.viewPager) { tab, position ->
            tab.text = when(position) {
                0 -> getString(R.string.about_name)
                1 -> getString(R.string.base_stats_name)
                2 -> getString(R.string.evolution_name)
                3 -> getString(R.string.moves_name)
                else -> throw IndexOutOfBoundsException("There isn't a $position tab")
            }
        }.attach()

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        view.transitionName = "container"
        postponeEnterTransition()

        lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.uiState.collectLatest { uiState ->
                    (view.parent as? ViewGroup)?.doOnPreDraw {
                        startPostponedEnterTransition()
                    }
                    uiState?.let { binding.bindUIState(it) }
                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun FragmentPokemonDetailsBinding.bindUIState(uiState: PokemonDetailsUiState) {
        root.setBackgroundResource(uiState.color.getColorResource())

        name.text = formatNameUseCase(uiState.name)
        order.text = formatOrderUseCase(uiState.order)

        type1.root.bindType(uiState.types.getOrNull(0))
        type2.root.bindType(uiState.types.getOrNull(1))

        Glide.with(root)
            .load(uiState.spriteUrl)
            .into(sprite)

    }

    private inner class PokemonDetailsStateAdapter(fragment: Fragment) : FragmentStateAdapter(fragment) {

        override fun getItemCount(): Int = NUM_TABS

        override fun createFragment(position: Int): Fragment = when(position) {
            0 -> AboutFragment()
            1 -> BaseStatsFragment()
            2 -> EvolutionFragment()
            3 -> MovesFragment()
            else -> throw IndexOutOfBoundsException("There isn't a $position tab")
        }
    }

    companion object {
        private const val NUM_TABS = 4
    }
}

