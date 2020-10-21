package com.example.newsapp

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class newsListAdapter(private val listener: newsItemClicked):RecyclerView.Adapter<NewsViewHolder>(){

    private val items : ArrayList<News> = ArrayList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_news,parent,false)
        val viewHolder = NewsViewHolder(view)
        view.setOnClickListener{
            listener.onItemclicked(items[viewHolder.adapterPosition])
        }

        return NewsViewHolder(view)


    }

    override fun onBindViewHolder(holder: NewsViewHolder, position: Int) {

        val currentitem = items[position]
        holder.titleView.text = currentitem.title

    }

    override fun getItemCount(): Int {
        return items.size

    }

    fun updatedNews(updatedNews: ArrayList<News>){
        items.clear()
        items.addAll(updatedNews)

        notifyDataSetChanged()
    }
}

class NewsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
    val titleView: TextView = itemView.findViewById(R.id.title)

}


interface newsItemClicked{
    fun onItemclicked(item: News)

}

