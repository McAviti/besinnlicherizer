package net.mcaviti.besinnlicherizer

import java.io.File
import java.lang.ProcessBuilder.Redirect
import java.util.concurrent.TimeUnit

const val PYTHON_BIN="C:/src/besinnlicherizer/venv/Scripts/python.exe"
const val ANALYZE_SCRIPT="C:/src/besinnlicherizer/src/main/python/ai_vision_analyze.py"
const val GENERATE_SCRIPT="C:/src/besinnlicherizer/src/main/python/openai_img_gen.py"
fun runAnalysis(directory: String, srcfile: String) {
    val process = ProcessBuilder(PYTHON_BIN, ANALYZE_SCRIPT, srcfile)
        .directory(File(directory))
        .redirectOutput(Redirect.INHERIT)
        .redirectError(Redirect.INHERIT)
        .start()
    if (!process.waitFor(60, TimeUnit.SECONDS)) {
        process.destroy()
        throw RuntimeException("execution timed out.")
    }
    println("process runAnalysis ended. ${process.exitValue()}");
    if (process.exitValue() != 0) {
        throw RuntimeException("execution failed with code ${process.exitValue()}")
    }
}

fun runGenerator(directory: String) {
    val process = ProcessBuilder(PYTHON_BIN, GENERATE_SCRIPT)
        .directory(File(directory))
        .redirectOutput(Redirect.INHERIT)
        .redirectError(Redirect.INHERIT)
        .start()
    if (!process.waitFor(60, TimeUnit.SECONDS)) {
        process.destroy()
        throw RuntimeException("execution timed out.")
    }
    println("process runGenerator ended. ${process.exitValue()}");
    if (process.exitValue() != 0) {
        throw RuntimeException("execution failed with code ${process.exitValue()}")
    }
}
