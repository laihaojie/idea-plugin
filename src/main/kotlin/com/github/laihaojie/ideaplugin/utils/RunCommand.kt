package com.github.laihaojie.ideaplugin.utils

import java.io.File

fun runCmd(cmd: List<String>?, rootPath: File?): String {
    if (cmd.isNullOrEmpty()) {
        throw IllegalArgumentException("命令不能为空")
    }

    try {
        val process = ProcessBuilder(cmd)
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
            throw RuntimeException("命令执行出错: $errors")
        }

        return output.trim()
    } catch (e: Exception) {
        throw RuntimeException("执行命令失败: ${e.message}", e)
    }
}