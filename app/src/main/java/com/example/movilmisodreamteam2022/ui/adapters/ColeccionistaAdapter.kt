package com.example.movilmisodreamteam2022.ui.adapters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.movilmisodreamteam2022.R
import com.example.movilmisodreamteam2022.databinding.ColeccionistaItemBinding
import com.example.movilmisodreamteam2022.models.Coleccionista


class ColeccionistaAdapter : RecyclerView.Adapter<ColeccionistaAdapter.ColeccionistaViewHolder>(){
    var coleccionistas : List<Coleccionista> = emptyList()
        @SuppressLint("NotifyDataSetChanged")
        set(value){
            field = value
            notifyDataSetChanged()
        }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ColeccionistaViewHolder {
        val withDataBinding : ColeccionistaItemBinding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            ColeccionistaViewHolder.LAYOUT,
            parent, false)
        return ColeccionistaViewHolder(withDataBinding)
    }

    override fun onBindViewHolder(holder: ColeccionistaViewHolder, position:Int) {
        holder.viewDataBinding.also{
            it.coleccionista = coleccionistas[position]
        }
    }

    override fun getItemCount(): Int {
        return coleccionistas.size
    }
    class ColeccionistaViewHolder(val viewDataBinding: ColeccionistaItemBinding):
        RecyclerView.ViewHolder(viewDataBinding.root){
        companion object {
            @LayoutRes
            val LAYOUT = R.layout.coleccionista_item
        }
        }
}