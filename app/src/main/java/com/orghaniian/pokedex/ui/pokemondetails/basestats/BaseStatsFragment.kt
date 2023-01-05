package com.orghaniian.pokedex.ui.pokemondetails.basestats

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.orghaniian.pokedex.databinding.PokemonDetailsBaseStatsBinding

class BaseStatsFragment : Fragment() {
    private var _binding: PokemonDetailsBaseStatsBinding? = null


    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = PokemonDetailsBaseStatsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
