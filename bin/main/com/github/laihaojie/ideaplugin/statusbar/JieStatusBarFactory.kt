package com.github.laihaojie.ideaplugin.statusbar

import TimeWidget
import com.github.laihaojie.ideaplugin.JieBundle
import com.intellij.openapi.project.Project
import com.intellij.openapi.wm.StatusBar
import com.intellij.openapi.wm.StatusBarWidget
import com.intellij.openapi.wm.StatusBarWidgetFactory

class JieStatusBarFactory : StatusBarWidgetFactory {
    override fun getId(): String = JieBundle.message("statusBarFFactoryId")

    override fun getDisplayName(): String = "Time Widget"

    override fun isAvailable(project: Project): Boolean = true

    override fun createWidget(project: Project): StatusBarWidget = TimeWidget()

    override fun disposeWidget(widget: StatusBarWidget) {
        widget.dispose()
    }

    override fun canBeEnabledOn(statusBar: StatusBar): Boolean = true
}
