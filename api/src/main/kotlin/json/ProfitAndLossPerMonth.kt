package fr.profit.json

import fr.profit.json.serializers.BigDecimalSerializer
import fr.profit.json.serializers.LocalDateSerializer
import kotlinx.serialization.Serializable
import java.math.BigDecimal
import java.time.LocalDate

@Serializable
data class ProfitAndLossPerMonth(
    @Serializable(with = LocalDateSerializer::class)
    val month: LocalDate,
    @Serializable(with = BigDecimalSerializer::class)
    val balance: BigDecimal,
    @Serializable(with = BigDecimalSerializer::class)
    val credit: BigDecimal,
    @Serializable(with = BigDecimalSerializer::class)
    val debit: BigDecimal,
)
