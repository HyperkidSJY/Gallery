package com.hyper.gallery.models

import java.io.Serializable

data class GalleryResponse(
    val photos : Photos,
    val stat : String?
) : Serializable
