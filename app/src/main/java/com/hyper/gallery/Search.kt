package com.hyper.gallery

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.hyper.gallery.adapter.GalleryAdapter
import com.hyper.gallery.adapter.LoaderAdapter
import com.hyper.gallery.databinding.FragmentSearchBinding
import com.hyper.gallery.models.Photo
import com.hyper.gallery.utils.CustomSearchView
import com.hyper.gallery.viewmodels.HomeViewModel


class Search : Fragment() {


    private var _binding : FragmentSearchBinding? = null
    private val binding get() = _binding!!

    lateinit var galleryAdapter: GalleryAdapter
    lateinit var homeViewModel : HomeViewModel


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentSearchBinding.inflate(inflater,container,false)


        binding.searchBar.setOnQueryTextListener(object : CustomSearchView.OnQueryTextListener {

            override fun onQueryTextChange(newText: String) {
                setUpRecyclerView(newText)
            }

        })

        return binding.root
    }



    private fun setUpRecyclerView(query : String){
        binding.rvGallery.layoutManager = LinearLayoutManager(requireContext())
        galleryAdapter = GalleryAdapter(requireContext())
        homeViewModel = ViewModelProvider(requireActivity()).get(HomeViewModel::class.java)
        binding.rvGallery.adapter = galleryAdapter.withLoadStateHeaderAndFooter(
            header = LoaderAdapter(),
            footer = LoaderAdapter()
        )
        homeViewModel.searchPhotos(query)

        homeViewModel.searchList.observe(viewLifecycleOwner, Observer{
            galleryAdapter.submitData(lifecycle, it)
        })

        galleryAdapter.setOnClickListener(object : GalleryAdapter.OnClickListener{
            override fun onClick(position: Int, model: Photo) {
                val intent = Intent(requireContext(),ImageDetail::class.java)
                intent.putExtra(MainActivity.EXTRA_IMAGE_DETAILS,model)
                startActivity(intent)
            }
        })

    }


}