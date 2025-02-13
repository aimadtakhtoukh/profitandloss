package fr.canary.xml.serializers

import kotlinx.serialization.KSerializer
import kotlinx.serialization.descriptors.PrimitiveKind
import kotlinx.serialization.descriptors.PrimitiveSerialDescriptor
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder
import java.util.UUID

object NullIfEmptyUUIDSerializer : KSerializer<UUID?> {
    override val descriptor: SerialDescriptor =
        PrimitiveSerialDescriptor("UUID", PrimitiveKind.STRING)

    override fun serialize(encoder: Encoder, value: UUID?) {
        val encoded = when(value) {
            null -> ""
            else -> "{$value.toString()}"
        }
        encoder.encodeString(encoded)
    }

    override fun deserialize(decoder: Decoder): UUID? {
        val value = decoder.decodeString().filter { it != '{' && it != '}'}
        return when(value) {
            "" -> null
            else -> UUID.fromString(value)
        }
    }
}
