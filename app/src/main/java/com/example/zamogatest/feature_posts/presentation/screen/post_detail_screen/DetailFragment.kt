package com.example.zamogatest.feature_posts.presentation.screen.post_detail_screen

import android.os.Bundle
import android.util.Log
import android.view.*
import androidx.fragment.app.Fragment
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupWithNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.zamogatest.R
import com.example.zamogatest.databinding.FragmentDetailBinding
import com.example.zamogatest.databinding.FragmentPostsBinding
import com.example.zamogatest.feature_posts.domain.model.Comment
import com.example.zamogatest.feature_posts.domain.model.Post
import com.example.zamogatest.feature_posts.presentation.screen.post_detail_screen.components.comments_list.CommentAdapter
import com.example.zamogatest.feature_posts.presentation.screen.posts_screen.BlankFragmentDirections
import com.example.zamogatest.feature_posts.presentation.screen.posts_screen.components.post_list.PostAdapter
import com.example.zamogatest.feature_posts.presentation.screen.posts_screen.components.post_list.PostItemListener
import com.example.zamogatest.feature_posts.presentation.viewmodel.PostEvent
import com.example.zamogatest.feature_posts.presentation.viewmodel.PostViewModel

class DetailFragment : Fragment() {

    private var _binding : FragmentDetailBinding? = null
    private val binding get() = _binding!!

    private val postViewModel : PostViewModel by activityViewModels()
    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        setHasOptionsMenu(true);
        _binding = FragmentDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navController = Navigation.findNavController(view)
        val appBarConfiguration = AppBarConfiguration(navController.graph)
        binding.defaultToolbar.inflateMenu(R.menu.menu_detail)
        binding.defaultToolbar.setOnMenuItemClickListener { item ->
            Log.d("MyTesting", "ItemSelection")
            when (item.itemId) {
                R.id.mi_delete -> {
                    postViewModel.onEvent(PostEvent.DeletePost(postViewModel.selectedPost.value!!))
                    true
                }
                R.id.mi_favourite -> {
                    postViewModel.onEvent(PostEvent.ToggleFavouritePost(
                        postId = postViewModel.selectedPost.value!!.id
                    ))
                    true
                }
                else -> true
            }
        }
        binding.defaultToolbar.setupWithNavController(navController, appBarConfiguration)
    }

    override fun onStart() {
        super.onStart()

        postViewModel.selectedPost.value.let {
            Log.d("MyTesting", it.toString())
            binding.tvBody.text = it!!.body
            postViewModel.onEvent(PostEvent.LoadPostAuthor(userId = it?.userId!!))
            postViewModel.onEvent(PostEvent.LoadPostComments(postId = it?.id!!))
        }

        postViewModel.selectedPostAuthor.observe(requireActivity(), Observer {
            //Log.d("MyTesting", it.toString())
            binding.tvUserName.text = it!!.name
            binding.tvUserEmail.text = it!!.email
            binding.tvUserPhone.text = it!!.phone
            binding.tvUserWebsite.text = it!!.website
        })
        postViewModel.selectedPostComments.observe(requireActivity(), Observer {
            //Log.d("MyTesting", it.toString())
            initRecyclerView(it!!)
        })

        postViewModel.deletionSuccess.observe(requireActivity(), Observer {
            //Log.d("MyTesting", it.toString())
            if (it) {
                navController.popBackStack()
            }
        })
    }

    private fun initRecyclerView(comments : List<Comment>) {
        binding.rvComments.layoutManager = LinearLayoutManager(activity)
        binding.rvComments.adapter = CommentAdapter(comments)
    }
}