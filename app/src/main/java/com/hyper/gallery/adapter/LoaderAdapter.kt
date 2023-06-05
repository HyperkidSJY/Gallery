package com.hyper.gallery.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.paging.LoadState
import androidx.paging.LoadStateAdapter
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.snackbar.Snackbar
import com.hyper.gallery.databinding.ListItemBinding

import com.hyper.gallery.databinding.LoaderItemBinding

class LoaderAdapter : LoadStateAdapter<LoaderAdapter.LoaderViewHolder>() {

    class LoaderViewHolder(binding : LoaderItemBinding) :
        RecyclerView.ViewHolder(binding.root){
        val progressBar = binding.progressBar
    }

    override fun onBindViewHolder(holder: LoaderViewHolder, loadState: LoadState) {
        if(loadState is LoadState.Loading){
            holder.progressBar.isVisible
        }
        if(loadState is LoadState.Error){
            showSnackBar(holder.itemView)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, loadState: LoadState): LoaderViewHolder {
        return LoaderViewHolder(
            LoaderItemBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        )
    }

    fun showSnackBar(view: View) {
        Snackbar.make(view, "RETRY", Snackbar.LENGTH_INDEFINITE).show()
    }

}