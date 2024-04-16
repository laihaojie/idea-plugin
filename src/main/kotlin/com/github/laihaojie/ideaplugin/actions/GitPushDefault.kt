package com.github.laihaojie.ideaplugin.actions

import com.intellij.openapi.actionSystem.AnAction
import com.intellij.openapi.actionSystem.AnActionEvent
import pushCode
import java.io.File


class GitPushDefaultAction : AnAction() {

    override fun actionPerformed(e: AnActionEvent) {
        // 获取当前项目根路径
        val project = e.project
        val basePath = File(project?.basePath)

        pushCode(project, basePath, "chore: update")
    }

    override fun update(e: AnActionEvent) {
        // Enable/disable the action based on the context
        e.presentation.isEnabledAndVisible = true
    }
}
