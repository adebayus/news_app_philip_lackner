package com.sebade.clonenewsapp.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.sebade.clonenewsapp.R
import com.sebade.clonenewsapp.models.Article

class NewsAdapter : RecyclerView.Adapter<NewsAdapter.ArticleViewHolder>() {

    inner class ArticleViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView){
        var ivArticleImage : ImageView = itemView.findViewById(R.id.ivArticleImage)
        var tvSource : TextView = itemView.findViewById(R.id.tvSource)
        var tvTitle : TextView = itemView.findViewById(R.id.tvTitle)
        var tvDescription : TextView = itemView.findViewById(R.id.tvDescription)
        var tvPublishedAt : TextView = itemView.findViewById(R.id.tvPublishedAt)
    }

    private val differCallback = object : DiffUtil.ItemCallback<Article>(){
        override fun areItemsTheSame(oldItem: Article, newItem: Article): Boolean {
            return oldItem.url == newItem.url
        }

        override fun areContentsTheSame(oldItem: Article, newItem: Article): Boolean {
            return oldItem == newItem
        }
    }

    val differ = AsyncListDiffer(this, differCallback)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ArticleViewHolder {
        return ArticleViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_article_view, parent, false)
        )
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }

    override fun onBindViewHolder(holder: ArticleViewHolder, position: Int) {
        val article = differ.currentList[position]
        Glide.with(holder.itemView.context).load(article.urlToImage).into(holder.ivArticleImage)
        holder.tvSource.text = article.source.name
        holder.tvTitle.text = article.title
        holder.tvDescription.text = article.description
        holder.tvPublishedAt.text = article.publishedAt
        holder.itemView.setOnClickListener{
            onItemClickListener?.let { it1 -> it1(article) }
//            onItemClickListener?.let {it(article)}
        }
    }

    private var onItemClickListener : ((Article)-> Unit)? = null

    fun setOnItemClickListener(listener : (Article) -> Unit){
        onItemClickListener = listener
    }
}