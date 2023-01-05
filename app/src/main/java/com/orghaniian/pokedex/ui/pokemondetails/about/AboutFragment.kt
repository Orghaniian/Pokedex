package com.orghaniian.pokedex.ui.pokemondetails.about

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.orghaniian.pokedex.databinding.PokemonDetailsAboutBinding
import com.orghaniian.pokedex.ui.pokemondetails.PokemonDetailsUiState
import com.orghaniian.pokedex.ui.pokemondetails.PokemonDetailsViewModel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import java.text.DecimalFormat

class AboutFragment : Fragment() {
    private var _binding: PokemonDetailsAboutBinding? = null

    private val viewModel: PokemonDetailsViewModel by lazy { ViewModelProvider(requireParentFragment())[PokemonDetailsViewModel::class.java] }

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = PokemonDetailsAboutBinding.inflate(inflater, container, false)



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

    private fun PokemonDetailsAboutBinding.bindUIState(uiState: PokemonDetailsUiState) {
        heightValue.text = "${uiState.height * 100} cm"
        weightValue.text = "${uiState.weight} kg"
        val df = DecimalFormat("##.#%")
        genderValue.text = if (uiState.genderRate > 0)
                        "${df.format(uiState.genderRate)} ${df.format(1 - uiState.genderRate)}"
                    else "none"
    }
}
