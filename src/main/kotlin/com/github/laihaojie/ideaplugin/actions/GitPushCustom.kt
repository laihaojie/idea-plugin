package com.github.laihaojie.ideaplugin.actions

import com.intellij.openapi.actionSystem.AnAction
import com.intellij.openapi.actionSystem.AnActionEvent
import com.intellij.openapi.ui.Messages

class GitPushCustomAction : AnAction() {

    override fun actionPerformed(e: AnActionEvent) {
        Messages.showMessageDialog("GitPushCustomAction", "My Plugin", Messages.getInformationIcon())
    }

    override fun update(e: AnActionEvent) {
        // Enable/disable the action based on the context
        e.presentation.isEnabledAndVisible = true
    }
}
