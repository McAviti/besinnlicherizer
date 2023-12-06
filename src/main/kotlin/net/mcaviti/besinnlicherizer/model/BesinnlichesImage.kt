package net.mcaviti.besinnlicherizer.model

import com.google.gson.Gson
import net.mcaviti.besinnlicherizer.UPLOAD_FOLDER
import java.io.File
import java.io.IOException
import java.nio.file.FileSystems
import java.nio.file.Files
import java.nio.file.Path
import java.nio.file.Paths
import java.nio.file.attribute.BasicFileAttributes
import java.util.UUID;
import java.util.function.BiPredicate
import kotlin.io.path.readText

class BesinnlichesImage
private constructor(val id: String, var srcFilename: String, var prompt: String, var besinnlichFilename: String) {
    companion object {
        fun newEntry(srcFilename: String = "", prompt: String="", besinnlichFilename: String="") = BesinnlichesImage(UUID.randomUUID().toString(), srcFilename, prompt, besinnlichFilename)
    }


    fun save() {
        val mDir = File("${UPLOAD_FOLDER}/${id}");
        if (!mDir.exists()) {
            mDir.mkdirs()
        }
        val mFile = File(mDir, "besinnlichesImage.json")
        mFile.writeText(Gson().toJson(this))
    }
}

val besinnlicheImages = ArrayList<BesinnlichesImage>()

fun initBesinnlicheImages() {
    val gson = Gson()
    Files.find(
        Paths.get(UPLOAD_FOLDER),
        2,
        BiPredicate { p, bfa -> bfa.isRegularFile() && p.endsWith("besinnlichesImage.json") }).forEach {
        besinnlicheImages.add(gson.fromJson(it.readText(), BesinnlichesImage::class.java))
    }
    println("Initialized")
}

