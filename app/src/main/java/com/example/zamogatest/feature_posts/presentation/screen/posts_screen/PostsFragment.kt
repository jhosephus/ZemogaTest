package com.example.zamogatest.feature_posts.presentation.screen.posts_screen

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupWithNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.viewpager.widget.ViewPager
import com.example.zamogatest.R
import com.example.zamogatest.databinding.FragmentPostsBinding
import com.example.zamogatest.feature_posts.domain.model.Post
import com.example.zamogatest.feature_posts.presentation.screen.posts_screen.components.pager.PostsListPagerAdapter
import com.example.zamogatest.feature_posts.presentation.screen.posts_screen.components.post_list.PostAdapter
import com.example.zamogatest.feature_posts.presentation.screen.posts_screen.components.post_list.PostItemListener
import com.example.zamogatest.feature_posts.presentation.viewmodel.PostEvent
import com.example.zamogatest.feature_posts.presentation.viewmodel.PostViewModel


class BlankFragment : Fragment() {

    private var _binding : FragmentPostsBinding? = null
    private val binding get() = _binding!!

    private val postViewModel : PostViewModel by activityViewModels()
    private lateinit var navController: NavController


    private lateinit var postsListPagerAdapter: PostsListPagerAdapter
    private lateinit var viewPager: ViewPager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        _binding = FragmentPostsBinding.inflate(inflater, container, false)
        binding.btDeleteAll.setOnClickListener {
            postViewModel.onEvent(PostEvent.DeleteAllPosts)
        }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        navController = Navigation.findNavController(view)
        binding.defaultToolbar.inflateMenu(R.menu.menu_posts)
        binding.defaultToolbar.setOnMenuItemClickListener { item ->
            Log.d("MyTesting", "ItemSelection")
            when (item.itemId) {
                R.id.mi_refresh -> {
                    postViewModel.onEvent(PostEvent.RefreshPostList)
                    true
                }
                else -> true
            }
        }
        val appBarConfiguration = AppBarConfiguration(navController.graph)
        binding.defaultToolbar
            .setupWithNavController(navController, appBarConfiguration)


        // ViewPager
        postsListPagerAdapter = PostsListPagerAdapter(childFragmentManager)
        binding.pager.adapter = postsListPagerAdapter
        binding.tabLayout.setupWithViewPager(binding.pager)

    }

    override fun onStart() {
        super.onStart()

        postViewModel.onEvent(PostEvent.LoadPostList)
        postViewModel.posts.observe(requireActivity(), Observer {
            //Log.d("MyTesting", it.toString())
            //initRecyclerView(it)
        })

    }

    /*private fun initRecyclerView(posts : List<Post>) {
        binding.rvPosts.layoutManager = LinearLayoutManager(activity)
        binding.rvPosts.adapter = PostAdapter(posts, object : PostItemListener {
            override fun onItemClick(post: Post) {
                postViewModel.onEvent(PostEvent.ClickOnPost(post))
                val action = BlankFragmentDirections.actionPostsDetail()
                navController.navigate(action)
            }
        })
    }*/


}