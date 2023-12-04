package net.mcaviti.besinnlicherizer.plugins

import net.mcaviti.besinnlicherizer.model.*
import io.ktor.http.*
import io.ktor.http.content.*
import io.ktor.server.application.*
import io.ktor.server.freemarker.*
import io.ktor.server.http.content.*
import io.ktor.server.plugins.statuspages.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import io.ktor.server.util.*
import java.io.File

fun Application.configureRouting() {
    install(StatusPages) {
        exception<Throwable> { call, cause ->
            call.respondText(text = "500: $cause", status = HttpStatusCode.InternalServerError)
        }
    }
    routing {
        get("/") {
            call.respondRedirect("besinnliche_images")
        }
        route("besinnliche_images") {
            get {
                call.respond(FreeMarkerContent("index.ftl", mapOf("besinnliche_images" to besinnlicheImages)))
            }
            get("new") {
                call.respond(FreeMarkerContent("show.ftl", model = null))
            }
            post {

            }
            get("{id}") {
                val id = call.parameters.getOrFail("id")
                call.respond(FreeMarkerContent("show.ftl", mapOf("besinnliches_image" to besinnlicheImages.find { it.id == id })))

            }
            post("{id}/src_img") {
                val multipartData = call.receiveMultipart()
                multipartData.forEachPart { part ->
                    when (part) {
                        is PartData.FileItem -> {
                            val fileBytes = part.streamProvider().readBytes()
                            val id = call.parameters.getOrFail("id")
                            val mDir = File("${UPLOAD_FOLDER}/${id}");
                            if (!mDir.exists()) {
                                mDir.mkdirs()
                            }
                            val mFile = part.originalFileName?.let { it1 -> File(mDir, it1) }
                            if (mFile != null) {
                                mFile.writeBytes(fileBytes)
                                val besinnlichesImage = besinnlicheImages.find { it.id == id }
                                besinnlichesImage?.srcFilename = mFile.name
                                besinnlichesImage?.save()
                                call.respond(FreeMarkerContent("show.ftl", mapOf("besinnliches_image" to besinnlichesImage)))
                            }
                        }
                        else -> {
                            println("-->?")
                        }
                    }
                }
            }
            get("{id}/src_img") {
                val id = call.parameters.getOrFail("id")
                val besinnlichesImage = besinnlicheImages.find { it.id == id }
                val mFile = File("${UPLOAD_FOLDER}/${id}/${besinnlichesImage?.srcFilename}")
                call.respondFile(mFile)
            }
            get("{id}/generate") {
                // Show a page with fields for editing an article
            }
            post("{id}") {
                // Update or delete an article
            }
        }
        // Static plugin. Try to access `/static/index.html`
        static("/static") {
            resources("static")
        }
    }
}
