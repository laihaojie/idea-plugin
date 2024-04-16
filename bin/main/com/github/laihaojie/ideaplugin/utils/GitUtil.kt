import com.github.laihaojie.ideaplugin.JieBundle
import com.github.laihaojie.ideaplugin.utils.runCmd
import com.github.laihaojie.ideaplugin.utils.showDialog
import com.github.laihaojie.ideaplugin.utils.updateStatusBar
import com.intellij.openapi.project.Project
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import java.io.File
import javax.swing.Timer

fun checkStatusAsync(rootPath: File, callback: (String?) -> Unit) {
    GlobalScope.launch {

        runCmd(listOf("git", "fetch"), rootPath)

        val res = runCmd(listOf("git", "status"), rootPath)

        val currentBranchRegex = "On branch (.*)".toRegex()
        val isPullRegex = "Your branch is behind '.*' by (\\d+) commits?".toRegex()
        val isPushRegex = "Your branch is ahead of '.*' by (\\d+) commits?".toRegex()

        val currentBranch = currentBranchRegex.find(res)?.groupValues?.get(1) ?: ""
        val pullMatch = isPullRegex.find(res)
        val pushMatch = isPushRegex.find(res)
        val pullCountNum = pullMatch?.groupValues?.get(1)?.toInt() ?: 0
        val pushCountNum = pushMatch?.groupValues?.get(1)?.toInt() ?: 0

        val result = if (pullMatch != null && pullCountNum > 0) {
            "当前分支 $currentBranch 距离远程分支 $currentBranch 有 $pullCountNum 个拉取, 请先执行 git pull"
        } else if (pushMatch != null && pushCountNum > 0) {
            "当前分支 $currentBranch 距离远程分支 $currentBranch 有 $pushCountNum 个提交, 请先执行 git push"
        } else {
            null
        }

        callback(result)
    }
}

fun pushCode(project: Project?, basePath: File, message: String) {
    val statusMessages = listOf("正在提交...", "正在提交.. ", "正在提交.  ")
    var currentIndex = 0

    val timer = Timer(200) {
        updateStatusBar(project, JieBundle.message("name") + "： " + statusMessages[currentIndex])
        currentIndex = (currentIndex + 1) % statusMessages.size
    }
    timer.start()

    checkStatusAsync(basePath) { res ->
        timer.stop()

        if (res != null) {
            updateStatusBar(project, JieBundle.message("name"))
            showDialog(res)
            // return@checkStatusAsync
        } else {

            try {
                runCmd(listOf("git", "add", "."), basePath)
                runCmd(listOf("git", "commit", "-m", message), basePath)
                runCmd(listOf("git", "push"), basePath)

                updateStatusBar(project, JieBundle.message("name") + "：" + "push成功")
            } catch (e: Exception) {
                showDialog(e.message ?: "")
                updateStatusBar(project, JieBundle.message("name") + "：" + "提交失败")
            }
        }
    }
}