package fr.canary.database

import com.zaxxer.hikari.HikariConfig
import com.zaxxer.hikari.HikariDataSource
import fr.canary.database.table.LedgerTable
import fr.canary.model.Ledger
import org.jetbrains.exposed.sql.Database
import org.jetbrains.exposed.sql.batchInsert
import org.jetbrains.exposed.sql.transactions.transaction

object LedgerOperations {

    fun init() {
        val jdbcHost = System.getenv("DB_HOST") ?: "localhost"
        val jdbcPort = System.getenv("DB_PORT") ?: "5432"
        val jdbcDatabase = System.getenv("DB_DATABASE") ?: "canary"
        val jdbcURL = System.getenv("DB_URL") ?: "jdbc:postgresql://$jdbcHost:$jdbcPort/$jdbcDatabase"
        val jdbcUser = System.getenv("DB_USER") ?: "canary"
        val jdbcPassword = System.getenv("DB_PASSWORD") ?: "canary"
        val hikariConfig = HikariConfig().apply {
            jdbcUrl = jdbcURL
            username = jdbcUser
            password = jdbcPassword
            driverClassName = "org.postgresql.Driver"
            maximumPoolSize = 10
        }
        val dataSource = HikariDataSource(hikariConfig)
        Database.connect(dataSource)
    }

    fun saveAll(ledgers: List<Ledger>) {
        transaction {
            LedgerTable.batchInsert(ledgers) {
                ledger ->
                    this[LedgerTable.balance] = ledger.balance
                    this[LedgerTable.category] = ledger.category
                    this[LedgerTable.collectif] = ledger.collectif
                    this[LedgerTable.comment] = ledger.comment
                    this[LedgerTable.credit] = ledger.credit
                    this[LedgerTable.currency] = ledger.currency.currencyCode
                    this[LedgerTable.currencyAmount] = ledger.currencyAmount
                    this[LedgerTable.date] = ledger.date
                    this[LedgerTable.debit] = ledger.debit
                    this[LedgerTable.description] = ledger.description
                    this[LedgerTable.header] = ledger.header
                    this[LedgerTable.internalID] = ledger.internalID
                    this[LedgerTable.journalIndex] = ledger.journalIndex
                    this[LedgerTable.lastModifyDate] = ledger.lastModifyDate
                    this[LedgerTable.letter] = ledger.letter
                    this[LedgerTable.letterID] = ledger.letterID
                    this[LedgerTable.letteredInBetween] = ledger.letteredInBetween
                    this[LedgerTable.name] = ledger.name
                    this[LedgerTable.number] = ledger.number
                    this[LedgerTable.paymentMethod] = ledger.paymentMethod
                    this[LedgerTable.paymentMethodDescription] = ledger.paymentMethodDescription
                    this[LedgerTable.periodEnd] = ledger.periodEnd
                    this[LedgerTable.periodStart] = ledger.periodStart
                    this[LedgerTable.piece] = ledger.piece
                    this[LedgerTable.qtyUnit] = ledger.qtyUnit
                    this[LedgerTable.quantity] = ledger.quantity
                    this[LedgerTable.ref] = ledger.ref
                    this[LedgerTable.term] = ledger.term
                    this[LedgerTable.voucherID] = ledger.voucherID
                    this[LedgerTable.voucherRef] = ledger.voucherRef
            }
        }
    }

}