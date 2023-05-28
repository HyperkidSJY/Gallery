package com.hyper.gallery

import android.content.Intent
import android.content.res.Configuration
import android.os.Bundle
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.hyper.gallery.adapter.GalleryAdapter
import com.hyper.gallery.databinding.ActivityMainBinding
import com.hyper.gallery.models.Photo
import com.hyper.gallery.viewmodels.MainViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    lateinit var mainViewModel: MainViewModel
    private lateinit var binding: ActivityMainBinding
    lateinit var galleryAdapter: GalleryAdapter
    private lateinit var toggle: ActionBarDrawerToggle

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setUpViewModel()
        setUpRecyclerView()

        setSupportActionBar(binding.toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        toggle = ActionBarDrawerToggle(
            this,
            binding.drawerLayout,
            binding.toolbar,
            R.string.navigation_drawer_open,
            R.string.navigation_drawer_close
        )
        binding.drawerLayout.addDrawerListener(toggle)

        binding.navView.setNavigationItemSelectedListener { menuItem ->

            when (menuItem.itemId) {
                R.id.nav_home -> {
                    // Handle home item click
                    Toast.makeText(applicationContext, "Home clicked", Toast.LENGTH_SHORT).show()
                    binding.drawerLayout.closeDrawer(GravityCompat.START)
                    return@setNavigationItemSelectedListener true
                    true
                }

                R.id.nav_profile -> {
                    // Handle profile item click
                    Toast.makeText(applicationContext, "Profile clicked", Toast.LENGTH_SHORT).show()
                    binding.drawerLayout.closeDrawer(GravityCompat.START)
                    true
                }

                else -> false
            }
            true
        }

    }

    override fun onPostCreate(savedInstanceState: Bundle?) {
        super.onPostCreate(savedInstanceState)
        toggle.syncState()
    }

    override fun onConfigurationChanged(newConfig: Configuration) {
        super.onConfigurationChanged(newConfig)
        toggle.onConfigurationChanged(newConfig)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            android.R.id.home -> {
                binding.drawerLayout.openDrawer(GravityCompat.START)
                true
            }

            else -> super.onOptionsItemSelected(item)
        }
    }


    private fun setUpRecyclerView(){
        binding.rvGallery.layoutManager = GridLayoutManager(this,2)
        galleryAdapter = GalleryAdapter(this)
        binding.rvGallery.adapter = galleryAdapter

        galleryAdapter.setOnClickListener(object : GalleryAdapter.OnClickListener{
            override fun onClick(position: Int, model: Photo) {
                val intent = Intent(this@MainActivity,ImageDetail::class.java)
                intent.putExtra(EXTRA_IMAGE_DETAILS,model)
                startActivity(intent)
            }

        })

    }

    private fun setUpViewModel(){
        mainViewModel = ViewModelProvider(this).get(MainViewModel::class.java)

        mainViewModel.photoLiveData.observe(this, Observer {
            if(it != null){
                galleryAdapter.setList(it)
                galleryAdapter.notifyDataSetChanged()
            }else{
              Toast.makeText(this,"Error in getting Photos",Toast.LENGTH_SHORT).show()
            }
        })
    }
    companion object{
        var EXTRA_IMAGE_DETAILS = "extra_image_details"
    }

}