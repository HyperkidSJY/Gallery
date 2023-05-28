package com.hyper.gallery.models

import java.io.Serializable


data class Photos(
    val page : Int,
    val pages : Int,
    val perpage : Int,
    val total : Int,
    val photo : List<Photo>
) : Serializable
