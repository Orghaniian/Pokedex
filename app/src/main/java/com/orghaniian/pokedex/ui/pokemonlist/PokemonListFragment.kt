package com.orghaniian.pokedex.ui.pokemonlist

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import com.orghaniian.pokedex.databinding.FragmentPokemonListBinding
import com.orghaniian.pokedex.domain.FormatNameUseCase
import com.orghaniian.pokedex.domain.FormatOrderUseCase
import com.orghaniian.pokedex.ui.utils.GridSpacingItemDecoration
import kotlinx.coroutines.launch

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class PokemonListFragment : Fragment() {

    private val formatOrderUseCase = FormatOrderUseCase() // TODO inject
    private val formatNameUseCase = FormatNameUseCase() // TODO inject

    private val viewModel: PokemonListViewModel by viewModels()
    private var _binding: FragmentPokemonListBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentPokemonListBinding.inflate(inflater, container, false)

        val pokemonListAdapter = PokemonListAdapter(formatOrderUseCase, formatNameUseCase)

        with(binding.pokemonList) {
            adapter = pokemonListAdapter
            layoutManager = GridLayoutManager(context, 2)
            setHasFixedSize(true)
            addItemDecoration(GridSpacingItemDecoration(context, 2, 8))
        }

        lifecycleScope.launch {
            viewModel.fetchPokemons()

            viewModel.uiState.collect { uiState ->
                pokemonListAdapter.submitList(uiState.pokemons)
            }
        }

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
