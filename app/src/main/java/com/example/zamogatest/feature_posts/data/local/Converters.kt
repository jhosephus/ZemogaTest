package com.example.zamogatest.feature_posts.data.local

import androidx.room.ProvidedTypeConverter
import androidx.room.TypeConverter
import com.example.zamogatest.feature_posts.data.local.entity.AddressEntity
import com.example.zamogatest.feature_posts.data.local.entity.CompanyEntity
import com.example.zamogatest.feature_posts.data.local.entity.GeoEntity
import com.example.zamogatest.feature_posts.data.util.JsonParser

@ProvidedTypeConverter
class Converters(
    private val jsonParser: JsonParser
) {

    @TypeConverter
    fun fromAddressJson(json: String): AddressEntity {
        return jsonParser
            .fromJson<AddressEntity>(
                json,
                AddressEntity::class.java) ?:
                AddressEntity("", "", "", "", GeoEntity("", ""))
    }

    @TypeConverter
    fun fromJsonAddress(addressEntity: AddressEntity): String {
        return jsonParser
            .toJson<AddressEntity>(
                addressEntity,
                AddressEntity::class.java
            ) ?: ""
    }

    @TypeConverter
    fun fromCompanyJson(json: String): CompanyEntity {
        return jsonParser
            .fromJson<CompanyEntity>(
                json,
                CompanyEntity::class.java) ?:
        CompanyEntity("","","")
    }

    @TypeConverter
    fun fromJsonCompany(companyEntity: CompanyEntity): String {
        return jsonParser
            .toJson<CompanyEntity>(
                companyEntity,
                CompanyEntity::class.java
            ) ?: ""
    }

}