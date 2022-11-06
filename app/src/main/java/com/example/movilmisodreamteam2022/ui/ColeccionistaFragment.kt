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
import com.example.movilmisodreamteam2022.databinding.FragmentColeccionistaBinding
import com.example.movilmisodreamteam2022.models.Banda
import com.example.movilmisodreamteam2022.models.Coleccionista
import com.example.movilmisodreamteam2022.ui.adapters.ArtistaAdapter
import com.example.movilmisodreamteam2022.ui.adapters.ColeccionistaAdapter
import com.example.movilmisodreamteam2022.viewmodels.ArtistaViewModel
import com.example.movilmisodreamteam2022.viewmodels.ColeccionistaViewModel
import com.google.android.material.floatingactionbutton.FloatingActionButton

class ColeccionistaFragment : Fragment() {

    private var _binding: FragmentColeccionistaBinding? = null
    private val binding get() = _binding!!
    private lateinit var recyclerView: RecyclerView
    private lateinit var viewModel: ColeccionistaViewModel
    private var viewModelAdapter: ColeccionistaAdapter? = null
    lateinit var addFAB: FloatingActionButton
    var rtimes: Int = 0

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentColeccionistaBinding.inflate(inflater, container, false)
        val view = binding.root
        viewModelAdapter = ColeccionistaAdapter()
        return view
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        recyclerView = binding.coleccionistasRv
        recyclerView.layoutManager = LinearLayoutManager(context)
        recyclerView.adapter = viewModelAdapter
        addFAB = view.findViewById(R.id.fab_add_coleccionista)
        addFAB.setOnClickListener {
            val intent = Intent(view.context, CrearColeccionistaActivity::class.java)
            startActivity(intent)
        }
    }



    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        val activity = requireNotNull(this.activity) {
            "You can only access the viewModel after onActivityCreated()"
        }
        activity.actionBar?.title = "COLECCIONISTAS"
        viewModel = ViewModelProvider(this, ColeccionistaViewModel.Factory(activity.application)).get(ColeccionistaViewModel::class.java)
        viewModel.coleccionistas.observe(viewLifecycleOwner, Observer<List<Coleccionista>> {
            it.apply {
                viewModelAdapter!!.coleccionistas = this
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