package com.poc.postitapp.presenter.navigation.custom

import android.net.Uri
import android.os.Bundle
import androidx.navigation.NavType
import com.poc.persistence.domain.entities.PostItEntity
import kotlinx.serialization.json.Json

object CustomNavTypePostIt {

    val PostItType = object : NavType<PostItEntity>(isNullableAllowed = false) {

        override fun get(bundle: Bundle, key: String): PostItEntity? {
            return Json.decodeFromString(bundle.getString(key) ?: return null)
        }

        override fun parseValue(value: String): PostItEntity {
            return Json.decodeFromString(Uri.decode(value))
        }

        override fun serializeAsValue(value: PostItEntity): String {
            return Uri.encode(Json.encodeToString(value))
        }

        override fun put(bundle: Bundle, key: String, value: PostItEntity) {
            bundle.putString(key, Json.encodeToString(value))
        }

    }
}