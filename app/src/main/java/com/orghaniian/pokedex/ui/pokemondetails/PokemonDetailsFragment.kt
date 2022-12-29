package com.orghaniian.pokedex.ui.pokemondetails

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.orghaniian.pokedex.databinding.FragmentPokemonDetailsBinding
import com.orghaniian.pokedex.domain.FormatNameUseCase
import com.orghaniian.pokedex.domain.FormatOrderUseCase
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

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentPokemonDetailsBinding.inflate(inflater, container, false)

        lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.uiState.collectLatest { uiState ->
                    uiState?.let { binding.bindUIState(it) }
                }
            }
        }

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun FragmentPokemonDetailsBinding.bindUIState(uiState: PokemonDetailsUiState) {
        root.setBackgroundResource(uiState.color.getColorResource())
    }
}

