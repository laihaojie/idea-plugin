package com.github.laihaojie.ideaplugin.utils

import java.io.File

fun openInBrowser(url: String, rootPath: File) {
    val cmd = if (System.getProperty("os.name").lowercase().contains("win")) {
        listOf("cmd", "/c", "start", url)
    } else {
        listOf("xdg-open", url)
    }
    runCmd(cmd, rootPath)
}