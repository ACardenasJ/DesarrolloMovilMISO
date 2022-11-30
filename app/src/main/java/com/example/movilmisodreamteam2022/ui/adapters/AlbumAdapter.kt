package com.example.movilmisodreamteam2022.ui.adapters


import android.annotation.SuppressLint
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.core.content.ContextCompat.startActivity
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.movilmisodreamteam2022.R
import com.example.movilmisodreamteam2022.databinding.AlbumItemBinding
import com.example.movilmisodreamteam2022.models.Album
import com.example.movilmisodreamteam2022.ui.AlbumFragmentDirections


class AlbumAdapter : RecyclerView.Adapter<AlbumAdapter.AlbumViewHolder>(){
    var albums :List<Album> = emptyList()

        @SuppressLint("NotifyDataSetChanged")
        set(value) {
            field = value
            notifyDataSetChanged()
        }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AlbumViewHolder {
        val withDataBinding: AlbumItemBinding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            AlbumViewHolder.LAYOUT,
            parent,
            false)
        return AlbumViewHolder(withDataBinding)
    }

    override fun onBindViewHolder(holder: AlbumViewHolder, position: Int) {
        holder.viewDataBinding.also {
            it.album = albums[position]
        }
        holder.viewDataBinding.root.setOnClickListener {
            val action = AlbumFragmentDirections.actionAlbumFragmentToMenuFragment()
            // Navigate using that action
//            System.out.println("Realice el Click")
//            System.out.println("Realice el Click validar algo:  " +  albums[position].id)
            holder.viewDataBinding.root.findNavController().navigate(action)


        }
    }

    override fun getItemCount(): Int {
        return albums.size
    }
    class AlbumViewHolder(val viewDataBinding: AlbumItemBinding) :
        RecyclerView.ViewHolder(viewDataBinding.root) {
        companion object {
            @LayoutRes
            val LAYOUT = R.layout.album_item
        }
    }
}