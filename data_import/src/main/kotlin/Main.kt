package fr.canary

import fr.canary.database.LedgerOperations
import fr.canary.database.table.LedgerTable
import fr.canary.xml.serializeFromFile
import fr.canary.xml.toModel
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