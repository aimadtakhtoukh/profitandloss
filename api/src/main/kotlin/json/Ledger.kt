package fr.profit.json

import fr.profit.json.serializers.BigDecimalSerializer
import fr.profit.json.serializers.CurrencySerializer
import fr.profit.json.serializers.LocalDateSerializer
import fr.profit.json.serializers.UUIDSerializer
import kotlinx.serialization.Serializable
import java.math.BigDecimal
import java.time.LocalDate
import java.util.*

@Serializable
data class Ledger(
    @Serializable(with = BigDecimalSerializer::class)
    val balance: BigDecimal,
    val category: String,
    val collectif: String?,
    val comment: String?,
    @Serializable(with = BigDecimalSerializer::class)
    val credit: BigDecimal,
    @Serializable(with = CurrencySerializer::class)
    val currency: Currency,
    @Serializable(with = BigDecimalSerializer::class)
    val currencyAmount: BigDecimal?,
    @Serializable(with = LocalDateSerializer::class)
    val date: LocalDate,
    @Serializable(with = BigDecimalSerializer::class)
    val debit: BigDecimal,
    val description: String,
    @Serializable(with = UUIDSerializer::class)
    val header: UUID,
    @Serializable(with = UUIDSerializer::class)
    val internalID: UUID,
    val journalIndex: String,
    @Serializable(with = LocalDateSerializer::class)
    val lastModifyDate: LocalDate?,
    val letter: Boolean,
    @Serializable(with = UUIDSerializer::class)
    val letterID: UUID?,
    val letteredInBetween: Boolean,
    val name: String,
    val number: String,
    val paymentMethod: String?,
    val paymentMethodDescription: String?,
    val periodEnd: String?,
    val periodStart: String?,
    val piece: String,
    val qtyUnit: String?,
    val quantity: Int?,
    val ref: String,
    val term: String?,
    val voucherID: String?,
    val voucherRef: String?,
)
