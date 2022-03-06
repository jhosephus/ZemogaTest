package com.example.zamogatest.feature_posts.data.local.entity

data class AddressEntity(
    val street: String,
    val suite: String,
    val city: String,
    val zipcode: String,
    val geo: GeoEntity
)
