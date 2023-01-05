package com.orghaniian.pokedex.ui.pokemonlist

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
import androidx.recyclerview.widget.GridLayoutManager
import com.orghaniian.pokedex.databinding.FragmentPokemonListBinding
import com.orghaniian.pokedex.domain.FormatNameUseCase
import com.orghaniian.pokedex.domain.FormatOrderUseCase
import com.orghaniian.pokedex.ui.utils.GridSpacingItemDecoration
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
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

    private val pokemonListAdapter: PokemonListAdapter by lazy {
        PokemonListAdapter(formatOrderUseCase, formatNameUseCase)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentPokemonListBinding.inflate(inflater, container, false)

        binding.bindAdapter(pokemonListAdapter)

        (view as? ViewGroup)?.isTransitionGroup = true
        postponeEnterTransition()

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.pagingData.collectLatest { pagingData ->
                    (view.parent as? ViewGroup)?.doOnPreDraw {
                        startPostponedEnterTransition()
                    }
                    pokemonListAdapter.submitData(pagingData)
                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}

private fun FragmentPokemonListBinding.bindAdapter(pokemonListAdapter: PokemonListAdapter) = with(pokemonList) {
    adapter = pokemonListAdapter
    layoutManager = GridLayoutManager(context, 2)
    addItemDecoration(GridSpacingItemDecoration(context, 2, 8))
}
