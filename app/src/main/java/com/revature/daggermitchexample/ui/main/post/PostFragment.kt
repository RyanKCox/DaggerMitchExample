package com.revature.daggermitchexample.ui.main.post

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.revature.daggermitchexample.R
import com.revature.daggermitchexample.util.VerticalSpacingItemDecoration
import com.revature.daggermitchexample.viewmodels.ViewModelProviderFactory
import dagger.android.support.DaggerFragment
import javax.inject.Inject

class PostFragment :DaggerFragment(){

    private lateinit var viewModel:PostViewModel
    private lateinit var recyclerView: RecyclerView

    @Inject
    lateinit var providerFactory: ViewModelProviderFactory
    @Inject
    lateinit var postAdapter:PostsRecyclerAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_post,container,false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        recyclerView = view.findViewById(R.id.recycler_view)
        viewModel = ViewModelProvider(this,providerFactory)[PostViewModel::class.java]

        initRecyclerView()

        subscribeObservers()
    }

    private fun subscribeObservers(){
        viewModel.observePosts().removeObservers(viewLifecycleOwner)
        viewModel.observePosts().observe(viewLifecycleOwner){
            resource->
            when (resource){
                is PostResource.DisplayPosts->{
                    Log.d("PostFragment","SubscribeObserver DisplayPosts")
                    postAdapter.setPosts(resource.posts)
                }
                is PostResource.Error->{
                    Log.d("PostFragment","SubscribeObserver Error: "+resource.error)
                }
                is PostResource.Loading->{
                    Log.d("PostFragment","SubscribeObserver Loading")
                }
            }
        }
    }
    private fun initRecyclerView(){
        recyclerView.layoutManager = LinearLayoutManager(activity)
        val itemDecor = VerticalSpacingItemDecoration(15)
        recyclerView.addItemDecoration(itemDecor)
        recyclerView.adapter = postAdapter
    }
}