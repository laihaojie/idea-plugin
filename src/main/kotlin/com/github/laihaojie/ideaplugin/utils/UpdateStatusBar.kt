package com.github.laihaojie.ideaplugin.utils

import TimeWidget
import com.github.laihaojie.ideaplugin.JieBundle
import com.intellij.openapi.project.Project
import com.intellij.openapi.wm.WindowManager

fun updateStatusBar(project: Project?, text: String) {
    val statusBar = project?.let { WindowManager.getInstance().getStatusBar(it) }


    // 获取你的 Widget
    val widget = statusBar?.getWidget(JieBundle.message("statusBarId")) as? TimeWidget

    widget?.updateText(text)

    widget?.let { statusBar.updateWidget(it.ID()) }

}