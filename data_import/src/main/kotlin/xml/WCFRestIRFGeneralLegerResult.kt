package fr.profit.xml

import fr.profit.model.Ledger
import fr.profit.xml.serializers.*
import kotlinx.serialization.Serializable
import nl.adaptivity.xmlutil.serialization.XML
import nl.adaptivity.xmlutil.serialization.XmlElement
import nl.adaptivity.xmlutil.serialization.XmlSerialName
import java.math.BigDecimal
import java.time.LocalDate
import java.util.*

@Serializable
@XmlSerialName("message")
data class Message(
    @XmlSerialName(value = "nil", namespace = "i")
    val isEmpty: String
)

@Serializable
@XmlSerialName("response")
data class Response(
    @XmlElement val result: String,
    @XmlElement val datetime: String,
    @XmlElement val message: Message?,
    @XmlElement val duration: String
)

@Serializable
@XmlSerialName("wsGeneralLedger")
data class WsGeneralLedger(
    @XmlElement @Serializable(with = BigDecimalSerializer::class) val balance: BigDecimal,
    @XmlElement val category: String,
    @XmlElement @Serializable(with = NullIfEmptyStringSerializer::class) val collectif: String?,
    @XmlElement @Serializable(with = NullIfEmptyStringSerializer::class) val comment: String?,
    @XmlElement @Serializable(with = BigDecimalSerializer::class) val credit: BigDecimal,
    @XmlElement @Serializable(with = CurrencySerializer::class) val currency: Currency,
    @XmlElement @Serializable(with = NullIfEmptyBigDecimalSerializer::class) val currencyAmount: BigDecimal?,
    @XmlElement @Serializable(with = LocalDateSerializer::class) val date: LocalDate,
    @XmlElement @Serializable(with = BigDecimalSerializer::class) val debit: BigDecimal,
    @XmlElement val description: String,
    @XmlElement @Serializable(with = UUIDSerializer::class) val header: UUID,
    @XmlElement @Serializable(with = UUIDSerializer::class) val internalID: UUID,
    @XmlElement val journalIndex: String,
    @XmlElement @Serializable(with = NullIfEmptyLocalDateSerializer::class) val lastModifyDate: LocalDate?,
    @XmlElement @Serializable(with=OuiNonBooleanSerializer::class) val letter: Boolean,
    @XmlElement @Serializable(with = NullIfEmptyUUIDSerializer::class) val letterID: UUID?,
    @XmlElement @Serializable(with=OuiNonBooleanSerializer::class) val letteredInBetween: Boolean,
    @XmlElement val name: String,
    @XmlElement val number: String,
    @XmlElement @Serializable(with = NullIfEmptyStringSerializer::class) val paymentMethod: String?,
    @XmlElement @Serializable(with = NullIfEmptyStringSerializer::class) val paymentMethodDescription: String?,
    @XmlElement @Serializable(with = NullIfEmptyStringSerializer::class) val periodEnd: String?,
    @XmlElement @Serializable(with = NullIfEmptyStringSerializer::class) val periodStart: String?,
    @XmlElement val piece: String,
    @XmlElement @Serializable(with = NullIfEmptyStringSerializer::class) val qtyUnit: String?,
    @XmlElement @Serializable(with = NullIfEmptyIntSerializer::class) val quantity: Int?,
    @XmlElement val ref: String,
    @XmlElement @Serializable(with = NullIfEmptyStringSerializer::class) val term: String?,
    @XmlElement @Serializable(with = NullIfEmptyStringSerializer::class) val voucherID: String?,
    @XmlElement @Serializable(with = NullIfEmptyStringSerializer::class) val voucherRef: String?
)

@Serializable
@XmlSerialName("data")
data class Data(
    @XmlElement val ledgers : List<WsGeneralLedger>
)

@Serializable
@XmlSerialName("WCFRestIRFGeneralLegerResult")
data class WCFRestIRFGeneralLegerResult(
    @XmlElement val resultInfo: Response,
    @XmlElement val resultData: Data
)

fun serializeFromFile() : WCFRestIRFGeneralLegerResult {
    val fileStream = object {}.javaClass.classLoader.getResourceAsStream("brignolles-gl.xml")
    val fileContent = fileStream?.bufferedReader()?.use { it.readText() } ?: ""
    return XML.decodeFromString(WCFRestIRFGeneralLegerResult.serializer(), fileContent)
}

fun toModel(wsLedger: WsGeneralLedger) : Ledger {
    return Ledger(
        balance = wsLedger.balance,
        category = wsLedger.category,
        collectif = wsLedger.collectif,
        comment = wsLedger.comment,
        credit = wsLedger.credit,
        currency = wsLedger.currency,
        currencyAmount = wsLedger.currencyAmount,
        date = wsLedger.date,
        debit = wsLedger.debit,
        description = wsLedger.description,
        header = wsLedger.header,
        internalID = wsLedger.internalID,
        journalIndex = wsLedger.journalIndex,
        lastModifyDate = wsLedger.lastModifyDate,
        letter = wsLedger.letter,
        letterID = wsLedger.letterID,
        letteredInBetween = wsLedger.letteredInBetween,
        name = wsLedger.name,
        number = wsLedger.number,
        paymentMethod = wsLedger.paymentMethod,
        paymentMethodDescription = wsLedger.paymentMethodDescription,
        periodEnd = wsLedger.periodEnd,
        periodStart = wsLedger.periodStart,
        piece = wsLedger.piece,
        qtyUnit = wsLedger.qtyUnit,
        quantity = wsLedger.quantity,
        ref = wsLedger.ref,
        term = wsLedger.term,
        voucherID = wsLedger.voucherID,
        voucherRef = wsLedger.voucherRef,
    )
}