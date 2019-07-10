package com.example.myapplicationtest.Adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplicationtest.data.NewsSource
import com.example.myapplicationtest.databinding.NewsSourceListItemBinding
import com.example.myapplicationtest.view.viewholder.NewsPaperViewHolder

class NewsPaperListAdapter(
    private var newsList: List<NewsSource> = mutableListOf()
    ): RecyclerView.Adapter<NewsPaperViewHolder>(){


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsPaperViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return NewsPaperViewHolder(
            NewsSourceListItemBinding.inflate(
                layoutInflater, parent, false
            )
        )
    }

    override fun onBindViewHolder(holder: NewsPaperViewHolder, position: Int) {
        val newsSource = newsList[position]
        holder.bind(newsSource)
    }

    fun setNewsSource(newsSourceList: List<NewsSource>){
        newsList = newsSourceList
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int {
        return newsList.size
    }




}