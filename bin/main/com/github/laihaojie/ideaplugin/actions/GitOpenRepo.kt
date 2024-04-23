package com.github.laihaojie.ideaplugin.actions

import com.github.laihaojie.ideaplugin.utils.openInBrowser
import com.github.laihaojie.ideaplugin.utils.runCmd
import com.intellij.openapi.actionSystem.AnAction
import com.intellij.openapi.actionSystem.AnActionEvent
import java.io.File

class GitOpenRepoAction : AnAction() {

    override fun actionPerformed(e: AnActionEvent) {
        // 获取当前项目根路径
        val project = e.project
        val basePath = File(project?.basePath)

        val res = runCmd(listOf("git", "remote", "-v"), basePath)
        val matchResult = Regex("origin\\s+(.*)\\s+\\(fetch\\)").find(res)
        val url = matchResult?.groups?.get(1)?.value

        if (url != null) {
            openInBrowser(url, basePath)
        }
    }

    override fun update(e: AnActionEvent) {
        // Enable/disable the action based on the context
        e.presentation.isEnabledAndVisible = true
    }
}
