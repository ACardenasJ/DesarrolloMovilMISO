package com.example.movilmisodreamteam2022.ui

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.movilmisodreamteam2022.R
import com.example.movilmisodreamteam2022.databinding.FragmentMenuBinding



class MenuFragment : Fragment() {

    private var _binding: FragmentMenuBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentMenuBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.buttonArtistM.setOnClickListener {
            findNavController().navigate(R.id.action_MenuFragment_to_ArtistFragment)
        }

        binding.buttonAlbumM.setOnClickListener {
            findNavController().navigate(R.id.action_MenuFragment_to_AlbumFragment)
        }

        binding.buttonColeccionistaM.setOnClickListener {
            findNavController().navigate(R.id.action_MenuFragment_to_ColeccionistaFragment)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


}