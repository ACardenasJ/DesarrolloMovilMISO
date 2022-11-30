package com.example.movilmisodreamteam2022.ui.adapters

import android.annotation.SuppressLint
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.core.content.ContextCompat
import androidx.core.content.ContextCompat.startActivity
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.movilmisodreamteam2022.R
import com.example.movilmisodreamteam2022.databinding.ArtistItemBinding
import com.example.movilmisodreamteam2022.models.Banda
import com.example.movilmisodreamteam2022.ui.ArtistaFragmentDirections
import com.example.movilmisodreamteam2022.ui.DetalleArtistaActivity

class ArtistaAdapter : RecyclerView.Adapter<ArtistaAdapter.ArtistaViewHolder>(){
    var bandas :List<Banda> = emptyList()
        @SuppressLint("NotifyDataSetChanged")
        set(value) {
            field = value
            notifyDataSetChanged()
        }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ArtistaViewHolder {
        val withDataBinding: ArtistItemBinding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            ArtistaViewHolder.LAYOUT,
            parent,
            false)
        return ArtistaViewHolder(withDataBinding)
    }

    override fun onBindViewHolder(holder: ArtistaViewHolder, position: Int) {
        holder.viewDataBinding.also {
            it.artist = bandas[position]
        }
        holder.viewDataBinding.root.setOnClickListener { v ->
            val action = ArtistaFragmentDirections.actionArtistFragmentToMenuFragment()
            // Navigate using that action
           // holder.viewDataBinding.root.findNavController().navigate(action)
            val intento = Intent(v.context, DetalleArtistaActivity::class.java)
            intento.putExtra("id-Artista",  bandas[position].id.toString())
            v.context.startActivity(intento)
        }
    }

    override fun getItemCount(): Int {
        return bandas.size
    }
    class ArtistaViewHolder(val viewDataBinding: ArtistItemBinding) :
        RecyclerView.ViewHolder(viewDataBinding.root) {
        companion object {
            @LayoutRes
            val LAYOUT = R.layout.artist_item
        }
    }

}