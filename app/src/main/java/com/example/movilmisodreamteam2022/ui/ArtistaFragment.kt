package com.example.movilmisodreamteam2022.ui

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.movilmisodreamteam2022.R
import com.example.movilmisodreamteam2022.databinding.FragmentArtistaBinding
import com.example.movilmisodreamteam2022.models.Banda
import com.example.movilmisodreamteam2022.ui.adapters.ArtistaAdapter
import com.example.movilmisodreamteam2022.viewmodels.ArtistaViewModel
import com.google.android.material.floatingactionbutton.FloatingActionButton


class ArtistaFragment : Fragment() {
    private var _binding: FragmentArtistaBinding? = null
    private val binding get() = _binding!!
    private lateinit var recyclerView: RecyclerView
    private lateinit var viewModel: ArtistaViewModel
    private var viewModelAdapter: ArtistaAdapter? = null
    lateinit var addFAB: FloatingActionButton
    var rtimes: Int = 0

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentArtistaBinding.inflate(inflater, container, false)
        val view = binding.root
        viewModelAdapter = ArtistaAdapter()
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        recyclerView = binding.artistasRv
        recyclerView.layoutManager = LinearLayoutManager(context)
        recyclerView.adapter = viewModelAdapter
        addFAB = view.findViewById(R.id.fab_add_artist)
        addFAB.setOnClickListener{
            val intent = Intent(view.context, CrearArtistaActivity::class.java)
            startActivity(intent)
        }
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        val activity = requireNotNull(this.activity) {
            "You can only access the viewModel after onActivityCreated()"
        }
        activity.actionBar?.title = "ARTISTAS"
        viewModel = ViewModelProvider(this, ArtistaViewModel.Factory(activity.application)).get(ArtistaViewModel::class.java)
        viewModel.artistas.observe(viewLifecycleOwner, Observer<List<Banda>> {
            it.apply {
                viewModelAdapter!!.bandas = this
            }
        })
        viewModel.eventNetworkError.observe(viewLifecycleOwner, Observer<Boolean> { isNetworkError ->
            if (isNetworkError) onNetworkError()
        })
    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onResume() {
        super.onResume()
        if(rtimes > 0) {
            viewModel.refreshDataFromNetwork()
        }
        rtimes++
    }

    private fun onNetworkError() {
        if(!viewModel.isNetworkErrorShown.value!!) {
            Toast.makeText(activity, "Network Error", Toast.LENGTH_LONG).show()
            viewModel.onNetworkErrorShown()
        }
    }
}