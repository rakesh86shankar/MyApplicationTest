package com.example.myapplicationtest.view

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer
import com.example.myapplicationtest.viewmodel.NewsPaperViewModel
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.myapplicationtest.Adapter.NewsPaperListAdapter
import com.example.myapplicationtest.R
import com.example.myapplicationtest.databinding.NewsPaperListBinding


class NewsPaperListFragment:Fragment(){
    private lateinit var viewModel: NewsPaperViewModel
    private lateinit var newsPaperListAdapter: NewsPaperListAdapter
    private lateinit var binding: NewsPaperListBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        //return inflater.inflate(R.layout.fragment_list, container, false)
        binding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_list, container, false
        )
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(NewsPaperViewModel::class.java)
        binding.viewmodel = viewModel
        viewModel.getNewsPaperSourcesList()
        setupViewModelObservers()
        binding.recyclerView.layoutManager = LinearLayoutManager(activity as Context)
        newsPaperListAdapter = NewsPaperListAdapter(mutableListOf())
        binding.recyclerView.adapter = newsPaperListAdapter

    }

    private fun setupViewModelObservers() {
        viewModel.newsPapersList.observe(this, Observer {
                if(it.size>0) {
                    Log.v("Size>>>>>>>", "" + it.size);
                    newsPaperListAdapter.setNewsSource(it.toList())
                    newsPaperListAdapter.notifyDataSetChanged()
                }else
                    Log.v("Error","Not able to fetch the resources")

            }
        )
    }

}
