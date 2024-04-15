package com.github.laihaojie.ideaplugin.utils

import java.io.File

fun runCmd(cmd: String?, rootPath: File?): String {
    if (cmd.isNullOrEmpty()) {
        throw IllegalArgumentException("Command must not be null or empty")
    }

    try {
        val process = ProcessBuilder(*cmd.split(" ").toTypedArray())
                .directory(rootPath)
                .redirectOutput(ProcessBuilder.Redirect.PIPE) // Redirect the output to a pipe
                .redirectError(ProcessBuilder.Redirect.PIPE)  // Redirect the errors to a pipe
                .start()

        // Wait for the command to complete
        process.waitFor()

        // Read the output from the input stream
        val output = process.inputStream.bufferedReader().use { it.readText() }
        val errors = process.errorStream.bufferedReader().use { it.readText() }

        if (process.exitValue() != 0) {
            throw RuntimeException("Command execution failed with errors: $errors")
        }

        return output.trim()
    } catch (e: Exception) {
        throw RuntimeException("Failed to execute command: ${e.message}", e)
    }
}