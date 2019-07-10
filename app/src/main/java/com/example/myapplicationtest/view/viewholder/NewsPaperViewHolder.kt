package com.example.myapplicationtest.view.viewholder

import androidx.recyclerview.widget.RecyclerView
import com.example.myapplicationtest.data.NewsSource
import com.example.myapplicationtest.databinding.NewsSourceListItemBinding

class NewsPaperViewHolder(
    private val binding: NewsSourceListItemBinding
): RecyclerView.ViewHolder(binding.root){

    init {

    }


    fun bind(newsPaperSource: NewsSource){
        binding.newsItemSource = newsPaperSource
        binding.executePendingBindings()
    }

}