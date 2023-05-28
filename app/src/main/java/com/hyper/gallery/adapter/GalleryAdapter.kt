package com.hyper.gallery.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.hyper.gallery.databinding.ListItemBinding
import com.hyper.gallery.models.Photo


class GalleryAdapter(private val context: Context)
    : RecyclerView.Adapter<GalleryAdapter.ViewHolder>() {

    class ViewHolder(binding: ListItemBinding) :
        RecyclerView.ViewHolder(binding.root){
            val image = binding.image
        }

    private var list: List<Photo>? = null
    fun setList(list : List<Photo>){
        this.list = list
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            ListItemBinding.inflate(
                LayoutInflater.from(parent.context),parent,false
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val model  = list?.get(position)
        Glide.with(context).load(model!!.url_s).into(holder.image)
        holder.itemView.setOnClickListener{
            if(onClickListener != null ){
                onClickListener!!.onClick(position,model)
            }
        }
    }

    override fun getItemCount(): Int {

        return if(list == null) 0
        else{
            list?.size!!
        }
    }

    private var onClickListener : OnClickListener? = null

    fun setOnClickListener(onClickListener: OnClickListener) {
        this.onClickListener = onClickListener
    }
    interface OnClickListener {
        fun onClick(position: Int, model : Photo)
    }
}