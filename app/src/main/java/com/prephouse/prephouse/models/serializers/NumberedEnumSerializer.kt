package com.prephouse.prephouse.models.serializers

import com.prephouse.prephouse.utils.NumberedEnum
import kotlinx.serialization.KSerializer
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.descriptors.serialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder
import kotlin.reflect.KClass

open class NumberedEnumSerializer<E>(
    private val kClass: KClass<E>
) : KSerializer<E> where E : Enum<E>, E : NumberedEnum {
    override val descriptor: SerialDescriptor = serialDescriptor<Int>()

    override fun serialize(encoder: Encoder, value: E) {
        encoder.encodeInt(value.value)
    }

    override fun deserialize(decoder: Decoder): E {
        val res = decoder.decodeInt().let { value ->
            kClass.java.enumConstants?.firstOrNull { it.value == value }
                ?: throw IllegalStateException("Cannot find enum member with value = $value")
        }
        return res
    }
}
