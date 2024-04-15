package com.github.laihaojie.ideaplugin.actions

import com.github.laihaojie.ideaplugin.utils.checkStatus
import com.intellij.openapi.actionSystem.AnAction
import com.intellij.openapi.actionSystem.AnActionEvent
import com.intellij.openapi.ui.Messages
import java.io.File


class GitPushDefaultAction : AnAction() {

    override fun actionPerformed(e: AnActionEvent) {
        // 获取当前项目根路径
        val project = e.project
        val basePath = File(project?.basePath)


      val res =  checkStatus(basePath)



        Messages.showMessageDialog("GitPushDefaultAction$res", "My Plugin", Messages.getInformationIcon())
    }

    override fun update(e: AnActionEvent) {
        // Enable/disable the action based on the context
        e.presentation.isEnabledAndVisible = true
    }



}
