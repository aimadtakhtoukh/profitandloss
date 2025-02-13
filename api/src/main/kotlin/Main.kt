package fr.canary

import fr.canary.database.LedgerOperations
import io.ktor.http.*
import io.ktor.serialization.kotlinx.json.*
import io.ktor.server.application.*
import io.ktor.server.engine.*
import io.ktor.server.netty.*
import io.ktor.server.plugins.contentnegotiation.*
import io.ktor.server.plugins.cors.routing.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import kotlinx.serialization.json.Json

fun main() {
    val appPort = System.getenv("APP_PORT")?.toInt() ?: 8080
    embeddedServer(Netty, port = appPort) {
        install(CORS) {
            anyHost() //Risque de sécurité!
            allowHeader(HttpHeaders.ContentType)
        }

        install(ContentNegotiation) {
            json(Json {
                prettyPrint = true
                isLenient = true
                explicitNulls = false
            })
        }

        LedgerOperations.init()

        routing {
            route("/ledgers") {
                get {
                    call.respond(
                        LedgerOperations.getAllSortedByDate()
                    )
                }
            }

            route("/balance") {
                get {
                    call.respond(
                        LedgerOperations.getBalance().toDouble()
                    )
                }
            }

            route("/profitAndLoss") {
                get {
                    call.respond(
                        LedgerOperations.profitAndLossPerMonth()
                    )
                }
            }
        }
    }.start(wait = true)
}