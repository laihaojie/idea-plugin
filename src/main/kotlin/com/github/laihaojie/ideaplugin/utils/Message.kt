package com.github.laihaojie.ideaplugin.utils

import com.github.laihaojie.ideaplugin.JieBundle
import com.intellij.openapi.application.ApplicationManager
import com.intellij.openapi.ui.Messages

fun showDialog(text: String, title: String? = null) {
    ApplicationManager.getApplication().invokeLater {
        // 现在这部分代码在 EDT 执行
        Messages.showMessageDialog(text, title ?: JieBundle.message("name"), Messages.getInformationIcon())
    }
}