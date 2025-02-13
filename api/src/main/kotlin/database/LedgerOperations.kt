package fr.profit.database

import com.zaxxer.hikari.HikariConfig
import com.zaxxer.hikari.HikariDataSource
import fr.profit.database.table.LedgerTable
import fr.profit.json.Ledger
import fr.profit.json.ProfitAndLossPerMonth
import org.jetbrains.exposed.sql.Database
import org.jetbrains.exposed.sql.ResultRow
import org.jetbrains.exposed.sql.SortOrder
import org.jetbrains.exposed.sql.selectAll
import org.jetbrains.exposed.sql.transactions.transaction
import java.math.BigDecimal
import java.time.LocalDate
import java.util.*

object LedgerOperations {

    fun init() {
        val jdbcHost = System.getenv("DB_HOST") ?: "localhost"
        val jdbcPort = System.getenv("DB_PORT") ?: "5432"
        val jdbcDatabase = System.getenv("DB_DATABASE") ?: "profit"
        val jdbcURL = System.getenv("DB_URL") ?: "jdbc:postgresql://$jdbcHost:$jdbcPort/$jdbcDatabase"
        val jdbcUser = System.getenv("DB_USER") ?: "profit"
        val jdbcPassword = System.getenv("DB_PASSWORD") ?: "profit"
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

    fun getAllSortedByDate() : List<Ledger> {
       return transaction {
           LedgerTable.selectAll()
               .orderBy(LedgerTable.date to SortOrder.DESC)
               .map { it.toLedger() }
       }
    }

    fun getBalance() : BigDecimal {
        return transaction {
            LedgerTable.select(LedgerTable.balance)
                .map { it[LedgerTable.balance] }
                .reduce { acc, value -> acc.add(value) }
        }
    }

    fun profitAndLossPerMonth() : List<ProfitAndLossPerMonth> {
        return transaction { LedgerTable.selectAll().map { it.toLedger() } }
            .groupBy { LocalDate.of(it.date.year, it.date.month, 1) }
            .map { ProfitAndLossPerMonth(
                month = it.key,
                balance = it.value.map { it.balance }.reduce {acc, value -> acc.add(value)},
                credit = it.value.map { it.credit }.reduce {acc, value -> acc.add(value)},
                debit = it.value.map { it.debit }.reduce {acc, value -> acc.add(value)},
            ) }
            .sortedByDescending { it.month }
    }

    private fun ResultRow.toLedger(): Ledger {
        return Ledger(
            balance = this[LedgerTable.balance],
            category = this[LedgerTable.category],
            collectif = this[LedgerTable.collectif],
            comment = this[LedgerTable.comment],
            credit = this[LedgerTable.credit],
            currency = Currency.getInstance(this[LedgerTable.currency]),
            currencyAmount = this[LedgerTable.currencyAmount],
            date = this[LedgerTable.date],
            debit = this[LedgerTable.debit],
            description = this[LedgerTable.description],
            header = this[LedgerTable.header],
            internalID = this[LedgerTable.internalID],
            journalIndex = this[LedgerTable.journalIndex],
            lastModifyDate = this[LedgerTable.lastModifyDate],
            letter = this[LedgerTable.letter],
            letterID = this[LedgerTable.letterID],
            letteredInBetween = this[LedgerTable.letteredInBetween],
            name = this[LedgerTable.name],
            number = this[LedgerTable.number],
            paymentMethod = this[LedgerTable.paymentMethod],
            paymentMethodDescription = this[LedgerTable.paymentMethodDescription],
            periodEnd = this[LedgerTable.periodEnd],
            periodStart = this[LedgerTable.periodStart],
            piece = this[LedgerTable.piece],
            qtyUnit = this[LedgerTable.qtyUnit],
            quantity = this[LedgerTable.quantity],
            ref = this[LedgerTable.ref],
            term = this[LedgerTable.term],
            voucherID = this[LedgerTable.voucherID],
            voucherRef = this[LedgerTable.voucherRef]
        )
    }



}