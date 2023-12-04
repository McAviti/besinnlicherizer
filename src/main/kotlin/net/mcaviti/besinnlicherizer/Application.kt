package net.mcaviti.besinnlicherizer

import io.ktor.server.application.*
import io.ktor.server.engine.*
import io.ktor.server.netty.*
import net.mcaviti.besinnlicherizer.model.initBesinnlicheImages
import net.mcaviti.besinnlicherizer.plugins.*

fun main() {
    initBesinnlicheImages()
    embeddedServer(Netty, port = 8080, host = "0.0.0.0", module = Application::module)
        .start(wait = true)
}

fun Application.module() {
    configureSecurity()
    configureHTTP()
    configureMonitoring()
    configureSerialization()
    configureTemplating()
    configureRouting()
}
