package com.example.zamogatest.feature_posts.presentation.screen.posts_screen.components.post_list

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.zamogatest.databinding.ItemPostBinding
import com.example.zamogatest.feature_posts.domain.model.Post

class PostViewHolder(
    itemView: View,
    private val postItemListener: PostItemListener
) : RecyclerView.ViewHolder(itemView) {

    val binding = ItemPostBinding.bind(itemView)

    fun render(post: Post) {
        if (post.isFavourite == 1){
            binding.ivFavIcon.visibility = View.VISIBLE
        }
        binding.tvTitle.text = post.title
        binding.root.setOnClickListener {
            postItemListener.onItemClick(post)
        }
    }

}