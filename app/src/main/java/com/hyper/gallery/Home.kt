package com.hyper.gallery

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.paging.LoadState
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.hyper.gallery.MainActivity.Companion.EXTRA_IMAGE_DETAILS
import com.hyper.gallery.adapter.GalleryAdapter
import com.hyper.gallery.adapter.LoaderAdapter
import com.hyper.gallery.databinding.FragmentHomeBinding
import com.hyper.gallery.models.Photo
import com.hyper.gallery.viewmodels.HomeViewModel


class Home : Fragment() {

    private var _binding : FragmentHomeBinding? = null
    private val binding get() = _binding!!

    lateinit var homeViewModel : HomeViewModel
    lateinit var galleryAdapter: GalleryAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater,container,false)

        setUpRecyclerView()

        return binding.root
    }



    private fun setUpRecyclerView(){
        binding.rvGallery.layoutManager = LinearLayoutManager(requireContext())
        galleryAdapter = GalleryAdapter(requireContext())
        homeViewModel = ViewModelProvider(requireActivity()).get(HomeViewModel::class.java)
        binding.rvGallery.adapter = galleryAdapter.withLoadStateHeaderAndFooter(
            header = LoaderAdapter(),
            footer = LoaderAdapter(),
        )

        homeViewModel.list.observe(viewLifecycleOwner, Observer{
            galleryAdapter.submitData(lifecycle, it)
        })

        galleryAdapter.setOnClickListener(object : GalleryAdapter.OnClickListener{
            override fun onClick(position: Int, model: Photo) {
                val intent = Intent(requireContext(),ImageDetail::class.java)
                intent.putExtra(EXTRA_IMAGE_DETAILS,model)
                startActivity(intent)
            }
        })

    }
}