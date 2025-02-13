package fr.profit.model

import java.math.BigDecimal
import java.time.LocalDate
import java.util.*

data class Ledger(
    val balance: BigDecimal,
    val category: String,
    val collectif: String?,
    val comment: String?,
    val credit: BigDecimal,
    val currency: Currency,
    val currencyAmount: BigDecimal?,
    val date: LocalDate,
    val debit: BigDecimal,
    val description: String,
    val header: UUID,
    val internalID: UUID,
    val journalIndex: String,
    val lastModifyDate: LocalDate?,
    val letter: Boolean,
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
