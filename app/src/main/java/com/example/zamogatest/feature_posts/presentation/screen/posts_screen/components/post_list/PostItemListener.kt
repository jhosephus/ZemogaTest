package com.example.zamogatest.feature_posts.presentation.screen.posts_screen.components.post_list

import com.example.zamogatest.feature_posts.domain.model.Post

interface PostItemListener {

    fun onItemClick(post: Post)

}