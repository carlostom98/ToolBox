package com.poc.toolboxapp.presenter.navigation.custom

import android.net.Uri
import android.os.Bundle
import androidx.navigation.NavType
import com.poc.domain.entities.PhotosEntity
import kotlinx.serialization.json.Json

object CustomNavType {

    val PhotosType = object : NavType<PhotosEntity>(isNullableAllowed = false) {

        override fun get(bundle: Bundle, key: String): PhotosEntity? {
            return Json.decodeFromString(bundle.getString(key) ?: return null)
        }

        override fun parseValue(value: String): PhotosEntity {
            return Json.decodeFromString(Uri.decode(value))
        }

        override fun serializeAsValue(value: PhotosEntity): String {
            return Uri.encode(Json.encodeToString(value))
        }

        override fun put(bundle: Bundle, key: String, value: PhotosEntity) {
            bundle.putString(key, Json.encodeToString(value))
        }

    }
}