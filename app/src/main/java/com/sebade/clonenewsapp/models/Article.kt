package com.sebade.clonenewsapp.models

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "articles")
data class Article(
//    @PrimaryKey(autoGenerate = true)
    @PrimaryKey(autoGenerate = true)
    var id : Int? = null,
    val author: Any,
    val content: String,
    val description: String,
    val publishedAt: String,
    val source: Source,
    val title: String,
    val url: String,
    val urlToImage: String
)