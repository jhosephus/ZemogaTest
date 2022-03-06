package com.example.zamogatest.feature_posts.presentation.screen.posts_screen.components.pager

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupWithNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.zamogatest.R
import com.example.zamogatest.databinding.FragmentPostsBinding
import com.example.zamogatest.databinding.FragmentSimpleListBinding
import com.example.zamogatest.feature_posts.domain.model.Post
import com.example.zamogatest.feature_posts.presentation.screen.posts_screen.BlankFragmentDirections
import com.example.zamogatest.feature_posts.presentation.screen.posts_screen.components.post_list.PostAdapter
import com.example.zamogatest.feature_posts.presentation.screen.posts_screen.components.post_list.PostItemListener
import com.example.zamogatest.feature_posts.presentation.viewmodel.PostEvent
import com.example.zamogatest.feature_posts.presentation.viewmodel.PostViewModel

class SimpleListFragment : Fragment() {
    private var param1: Boolean = false

    private var _binding : FragmentSimpleListBinding? = null
    private val binding get() = _binding!!
    private val postViewModel : PostViewModel by activityViewModels()
    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getBoolean("FavOnly")!!
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentSimpleListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        navController = Navigation.findNavController(view)

    }

    override fun onStart() {
        super.onStart()

        postViewModel.onEvent(PostEvent.LoadPostList)
        postViewModel.posts.observe(requireActivity(), Observer {
            //Log.d("MyTesting", it.toString())
            if (param1) {
                initRecyclerView(selectFavOnly(it))
            } else {
                initRecyclerView(it)
            }
        })

    }

    private fun selectFavOnly(posts : List<Post>): List<Post> {
        return posts.filter { it.isFavourite == 1 }
    }

    private fun initRecyclerView(posts : List<Post>) {
        binding.rvSimpleList.layoutManager = LinearLayoutManager(activity)
        binding.rvSimpleList.adapter = PostAdapter(posts, object : PostItemListener {
            override fun onItemClick(post: Post) {
                postViewModel.onEvent(PostEvent.ClickOnPost(post))
                val action = BlankFragmentDirections.actionPostsDetail()
                navController.navigate(action)
            }
        })
    }
}