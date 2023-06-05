package com.hyper.gallery.utils

import android.content.Context
import android.util.AttributeSet
import androidx.appcompat.widget.SearchView
import kotlinx.coroutines.*

class CustomSearchView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null
) : SearchView(context, attrs) {

    private var debounceJob: Job? = null
    private val coroutineScope = CoroutineScope(Dispatchers.Main)

    private var onQueryTextListener: OnQueryTextListener? = null

    init {
        setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String): Boolean {
                return true
            }

            override fun onQueryTextChange(newText: String): Boolean {
                debounceJob?.cancel()
                debounceJob = coroutineScope.launch {
                    delay(300) // Debounce delay in milliseconds
                    newText.let { onQueryTextListener?.onQueryTextChange(it) }
                }
                return true
            }
        })
    }

    fun setOnQueryTextListener(listener: OnQueryTextListener?) {
        onQueryTextListener = listener
    }

    interface OnQueryTextListener {
        fun onQueryTextChange(newText: String)
    }
}