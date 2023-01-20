package com.orghaniian.pokedex.ui.pokemonlist

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.paging.compose.collectAsLazyPagingItems
import com.orghaniian.domain.FormatNameUseCase
import com.orghaniian.domain.FormatOrderUseCase
import com.orghaniian.pokedex.R
import com.orghaniian.pokedex.databinding.FragmentPokemonListBinding
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
@AndroidEntryPoint
class PokemonListFragment : Fragment() {

    @Inject
    lateinit var formatOrderUseCase: FormatOrderUseCase
    @Inject
    lateinit var formatNameUseCase: FormatNameUseCase

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

        binding.composeView.setContent {
            MaterialTheme {
                val pokemons = viewModel.pagingData.collectAsLazyPagingItems()

                PokemonGrid(
                    pokemons,
                    onPokemonClick = { pokemon ->
                        val action = PokemonListFragmentDirections
                            .actionPokemonListFragmentToPokemonDetailsFragment(
                                pokemon.order, formatNameUseCase(pokemon.name)
                            )
                        findNavController().navigate(action)
                    },
                    modifier = Modifier.padding(horizontal = dimensionResource(R.dimen.screen_padding))
                )
            }
        }

        (view as? ViewGroup)?.isTransitionGroup = true
        postponeEnterTransition()

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
