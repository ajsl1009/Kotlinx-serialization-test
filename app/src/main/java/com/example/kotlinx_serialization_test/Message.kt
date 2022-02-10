package com.example.kotlinx_serialization_test

import kotlinx.serialization.Serializable
import kotlinx.serialization.builtins.serializer
import kotlinx.serialization.json.JsonElement
import kotlinx.serialization.json.JsonPrimitive
import kotlinx.serialization.json.JsonTransformingSerializer

object JsonAsStringSerializer : JsonTransformingSerializer<String>(tSerializer = String.serializer()) {
    override fun transformDeserialize(element: JsonElement): JsonElement {
        return JsonPrimitive(value = element.toString())
    }
}

@Serializable
data class Message(
    val function: String,
    //value가 JSONObject인 경우 String으로 받기
    @Serializable(with = JsonAsStringSerializer::class)
    val params: String,
)
