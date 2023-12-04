package net.mcaviti.besinnlicherizer

import java.io.File
import java.lang.ProcessBuilder.Redirect
import java.util.concurrent.TimeUnit

fun String.runCommand(params: String) {
    val process = ProcessBuilder(*split(params).toTypedArray())
        .directory(File("/tmp"))
        .redirectOutput(Redirect.INHERIT)
        .redirectError(Redirect.INHERIT)
        .start()
    if (!process.waitFor(60, TimeUnit.SECONDS)) {
        process.destroy()
        throw RuntimeException("execution timed out: $this")
    }
    if (process.exitValue() != 0) {
        throw RuntimeException("execution failed with code ${process.exitValue()}: $this")
    }
}
