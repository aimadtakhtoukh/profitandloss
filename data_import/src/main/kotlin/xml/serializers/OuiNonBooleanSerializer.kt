package fr.canary.xml.serializers

import kotlinx.serialization.KSerializer
import kotlinx.serialization.descriptors.PrimitiveKind
import kotlinx.serialization.descriptors.PrimitiveSerialDescriptor
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder

object OuiNonBooleanSerializer : KSerializer<Boolean> {
    override val descriptor: SerialDescriptor =
        PrimitiveSerialDescriptor("OuiNonBoolean", PrimitiveKind.BOOLEAN)

    override fun serialize(encoder: Encoder, value: Boolean) {
        val encoded = when(value) {
            true -> "Oui"
            false -> "Non"
        }
        encoder.encodeString(encoded)
    }

    override fun deserialize(decoder: Decoder): Boolean {
        return when(decoder.decodeString()) {
            "Oui" -> true
            "Non" -> false
            else -> false
        }
    }

}