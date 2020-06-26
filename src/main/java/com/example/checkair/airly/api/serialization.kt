package com.example.checkair.airly.api

import com.google.gson.JsonDeserializationContext
import com.google.gson.JsonDeserializer
import com.google.gson.JsonElement
import java.lang.reflect.Type
import java.time.ZonedDateTime

class ZonedDateTimeDeserializer : JsonDeserializer<ZonedDateTime> {
    override fun deserialize(json: JsonElement, typeOfT: Type, context: JsonDeserializationContext?): ZonedDateTime {
        return ZonedDateTime.parse(json.asString)
    }
}