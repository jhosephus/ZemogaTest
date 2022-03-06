package com.example.zamogatest.feature_posts.data.remote.dto

data class AddressDto(
    val street: String,
    val suite: String,
    val city: String,
    val zipcode: String,
    val geo: GeoDto
)
