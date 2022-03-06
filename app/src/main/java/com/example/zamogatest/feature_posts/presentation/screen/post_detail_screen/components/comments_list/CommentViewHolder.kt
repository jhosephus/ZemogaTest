package com.example.zamogatest.feature_posts.presentation.screen.post_detail_screen.components.comments_list

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.zamogatest.databinding.ItemCommentBinding
import com.example.zamogatest.feature_posts.domain.model.Comment
import com.example.zamogatest.feature_posts.domain.model.Post

class CommentViewHolder(
    itemView: View
) : RecyclerView.ViewHolder(itemView) {

    val binding = ItemCommentBinding.bind(itemView)

    fun render(comment: Comment) {
        binding.tvBody.text = comment.body
    }

}