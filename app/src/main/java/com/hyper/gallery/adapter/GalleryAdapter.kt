package com.hyper.gallery.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.hyper.gallery.databinding.ListItemBinding
import com.hyper.gallery.models.Photo


class GalleryAdapter(private val context: Context) : PagingDataAdapter<Photo, GalleryAdapter.GalleryViewHolder> (COMPARATOR){
    class GalleryViewHolder(binding: ListItemBinding) :
        RecyclerView.ViewHolder(binding.root){
        val image = binding.image
    }


    override fun onBindViewHolder(holder: GalleryViewHolder, position: Int) {
        val model  = getItem(position)
        Glide.with(context).load(model?.url_s).into(holder.image)
        holder.itemView.setOnClickListener{
            if(onClickListener != null ){
                if (model != null) {
                    onClickListener!!.onClick(position,model)
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GalleryViewHolder {
        return GalleryViewHolder(
            ListItemBinding.inflate(
                LayoutInflater.from(parent.context),parent,false
            )
        )
    }


    private var onClickListener : OnClickListener? = null

    fun setOnClickListener(onClickListener: OnClickListener) {
        this.onClickListener = onClickListener
    }
    interface OnClickListener {
        fun onClick(position: Int, model : Photo)
    }


    companion object {
        private val COMPARATOR = object : DiffUtil.ItemCallback<Photo>() {
            override fun areItemsTheSame(oldItem: Photo, newItem: Photo): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: Photo, newItem: Photo): Boolean {
                return oldItem == newItem
            }
        }
    }


}