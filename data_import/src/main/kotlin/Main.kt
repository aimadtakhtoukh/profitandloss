package fr.profit

import fr.profit.database.LedgerOperations
import fr.profit.database.table.LedgerTable
import fr.profit.xml.serializeFromFile
import fr.profit.xml.toModel
import org.jetbrains.exposed.sql.SchemaUtils
import org.jetbrains.exposed.sql.transactions.transaction

fun main() {
    println("Loading file...")
    LedgerOperations.init()
    transaction { SchemaUtils.create(LedgerTable) }
    val wsData = serializeFromFile()
    val ledgers = wsData.resultData.ledgers.map { toModel(it) }
    LedgerOperations.saveAll(ledgers)
    println("File loading done.")
}