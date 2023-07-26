package com.baymax104.bookmanager20compose.util

import androidx.core.text.isDigitsOnly
import androidx.room.TypeConverter
import kotlinx.serialization.KSerializer
import kotlinx.serialization.descriptors.PrimitiveKind
import kotlinx.serialization.descriptors.PrimitiveSerialDescriptor
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder
import kotlinx.serialization.json.Json
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

// ================================= Date String Convertor =========================================

val DateFormatter = SimpleDateFormat("yyyy-MM-dd", Locale.CHINA)

val DateDetailFormatter = SimpleDateFormat("yyyyMMdd_HHmmss", Locale.CHINA)

fun Date?.toDateString(detail: Boolean = false) =
    this?.let {
        val formatter = if (detail) DateDetailFormatter else DateFormatter
        formatter.format(this)
    } ?: ""

// ================================= JSON Convertor ================================================

val JsonCoder = Json {
    ignoreUnknownKeys = true
}

object PageSerializer : KSerializer<Int> {
    override val descriptor: SerialDescriptor
        get() = PrimitiveSerialDescriptor("page", PrimitiveKind.INT)

    override fun deserialize(decoder: Decoder): Int {
        val value = decoder.decodeString()
        return when {
            value.endsWith("页") -> value.substring(0..value.length - 2).toInt()
            else -> 0
        }
    }

    override fun serialize(encoder: Encoder, value: Int) {
        encoder.encodeInt(value)
    }
}


// ================================= Room Convertor ================================================
class RoomConverter {

    @TypeConverter
    fun convertDateToString(date: Date?): String? =
        date?.let { DateFormatter.format(it) }

    @TypeConverter
    fun convertStringToDate(dateString: String?): Date? =
        dateString?.let { DateFormatter.parse(it) }
}


