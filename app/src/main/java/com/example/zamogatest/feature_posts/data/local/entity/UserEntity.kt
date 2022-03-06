package com.example.zamogatest.feature_posts.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.zamogatest.feature_posts.domain.model.Address
import com.example.zamogatest.feature_posts.domain.model.Company
import com.example.zamogatest.feature_posts.domain.model.Geo
import com.example.zamogatest.feature_posts.domain.model.User

@Entity
data class UserEntity(
    @PrimaryKey val id: Int,
    val name: String,
    val username: String,
    val email: String,
    val address: AddressEntity,
    val phone: String,
    val website: String,
    val company: CompanyEntity
) {
    fun toUser(): User {
        return User(
            id = id,
            name = name,
            username = username,
            email = email,
            address = Address(address.street, address.suite, address.city, address.zipcode,
                Geo(address.geo.lat, address.geo.lng)),
            phone = phone,
            website = website,
            company = Company(company.name, company.catchPhrase, company.bs)
        )
    }
}
