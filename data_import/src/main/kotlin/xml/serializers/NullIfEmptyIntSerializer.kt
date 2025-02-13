package fr.profit.xml.serializers

import kotlinx.serialization.KSerializer
import kotlinx.serialization.descriptors.PrimitiveKind
import kotlinx.serialization.descriptors.PrimitiveSerialDescriptor
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder

object NullIfEmptyIntSerializer : KSerializer<Int?> {
    override val descriptor: SerialDescriptor =
        PrimitiveSerialDescriptor("NullableInteger", PrimitiveKind.INT)

    override fun serialize(encoder: Encoder, value: Int?) {
        val encoded = when(value) {
            null -> ""
            else -> value.toString()
        }
        encoder.encodeString(encoded)
    }

    override fun deserialize(decoder: Decoder): Int? {
        return decoder.decodeString().toIntOrNull()
    }
}
