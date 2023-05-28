package com.hyper.gallery

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bumptech.glide.Glide
import com.hyper.gallery.databinding.ActivityImageDetailBinding
import com.hyper.gallery.models.Photo


class ImageDetail : AppCompatActivity() {
    private lateinit var binding : ActivityImageDetailBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityImageDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        var photo : Photo? = null

        if(intent.hasExtra(MainActivity.EXTRA_IMAGE_DETAILS)){
            photo = intent.getSerializableExtra(MainActivity.EXTRA_IMAGE_DETAILS) as Photo
        }
        if(photo != null){
            Glide.with(this@ImageDetail).load(photo.url_s).into(binding.imageView)
        }
    }
}