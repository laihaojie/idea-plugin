package com.github.laihaojie.ideaplugin.utils

import java.io.File

fun checkStatus(rootPath:File): String? {
    runCmd("git fetch",rootPath)

    val res = runCmd("git status",rootPath)

    val currentBranchRegex = "On branch (.*)".toRegex()
    val isPullRegex = "Your branch is behind '.*' by (\\d+) commits?".toRegex()
    val isPushRegex = "Your branch is ahead of '.*' by (\\d+) commits?".toRegex()

    val currentBranch = currentBranchRegex.find(res)?.groupValues?.get(1) ?: ""

    val pullMatch = isPullRegex.find(res)
    val pushMatch = isPushRegex.find(res)
    val pullCountNum = pullMatch?.groupValues?.get(1)?.toInt() ?: 0
    val pushCountNum = pushMatch?.groupValues?.get(1)?.toInt() ?: 0

    if (pullMatch != null && pullCountNum > 0) {
        return "当前分支 $currentBranch 距离远程分支 $currentBranch 有 $pullCountNum 个拉取, 请先执行 git pull"
    }

    if (pushMatch != null && pushCountNum > 0) {
        return "当前分支 $currentBranch 距离远程分支 $currentBranch 有 $pushCountNum 个提交, 请先执行 git push"
    }

    return null
}