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

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

class MenuFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
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
/*
        binding.buttonArtistM.setOnClickListener {
            val context = view.context
            //val intent = Intent(context, ArtistActivity::class.java)
            val intent = Intent(context, CrearArtista::class.java)
            context.startActivity(intent)
        }*/

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