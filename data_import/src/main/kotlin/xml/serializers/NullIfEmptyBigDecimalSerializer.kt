package fr.canary.xml.serializers

import kotlinx.serialization.KSerializer
import kotlinx.serialization.descriptors.PrimitiveKind
import kotlinx.serialization.descriptors.PrimitiveSerialDescriptor
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder
import java.math.BigDecimal

object NullIfEmptyBigDecimalSerializer : KSerializer<BigDecimal?> {
    override val descriptor: SerialDescriptor =
        PrimitiveSerialDescriptor("BigDecimal", PrimitiveKind.STRING)

    override fun serialize(encoder: Encoder, value: BigDecimal?) {
        encoder.encodeString(value?.toPlainString().orEmpty())
    }

    override fun deserialize(decoder: Decoder): BigDecimal? {
        return when(val value = decoder.decodeString()) {
            "" -> null
            else -> BigDecimal(value)
        }
    }
}