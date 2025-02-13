package fr.canary.database.table

import org.jetbrains.exposed.sql.Table
import org.jetbrains.exposed.sql.javatime.date

object LedgerTable : Table() {
    val id = integer("id").autoIncrement()
    val balance = decimal("balance", 10, 2)
    val category = varchar("category", 100)
    val collectif = varchar("collectif", 100).nullable()
    val comment = varchar("comment", 100).nullable()
    val credit = decimal("credit", 10, 2)
    val currency = varchar("currency", 10)
    val currencyAmount = decimal("currencyAmount", 10, 2).nullable()
    val date = date("date")
    val debit = decimal("debit", 10 ,2)
    val description = varchar("description", 100)
    val header = uuid("header")
    val internalID = uuid("internalID")
    val journalIndex = varchar("journalIndex", 20)
    val lastModifyDate = date("lastModifyDate").nullable()
    val letter = bool("letter")
    val letterID = uuid("letterID").nullable()
    val letteredInBetween = bool("letteredInBetween")
    val name = varchar("name", 100)
    val number = varchar("number", 100)
    val paymentMethod = varchar("paymentMethod", 100).nullable()
    val paymentMethodDescription = varchar("paymentMethodDescription", 100).nullable()
    val periodEnd = varchar("periodEnd", 100).nullable()
    val periodStart = varchar("periodStart", 100).nullable()
    val piece = varchar("piece", 100)
    val qtyUnit = varchar("qtyUnit", 50).nullable()
    val quantity = integer("quantity").nullable()
    val ref = varchar("ref", 100)
    val term = varchar("term", 100).nullable()
    val voucherID = varchar("voucherID", 100).nullable()
    val voucherRef = varchar("voucherRef", 100).nullable()


    override val primaryKey = PrimaryKey(id)
}