package com.github.laihaojie.ideaplugin.actions

import com.intellij.openapi.actionSystem.AnAction
import com.intellij.openapi.actionSystem.AnActionEvent
import com.intellij.openapi.ui.Messages
import com.intellij.openapi.ui.Messages.showInputDialog
import pushCode
import java.io.File

class GitPushCustomAction : AnAction() {

    override fun actionPerformed(e: AnActionEvent) {
        // 获取当前项目根路径
        val project = e.project
        val basePath = File(project?.basePath)

        // 弹出输入框
        val input = showInputDialog(project, "请输入提交信息", "提交代码", Messages.getQuestionIcon())
        if (input != null) {
            pushCode(project, basePath, input)
        }
    }

    override fun update(e: AnActionEvent) {
        // Enable/disable the action based on the context
        e.presentation.isEnabledAndVisible = true
    }
}
