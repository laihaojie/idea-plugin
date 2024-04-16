import com.github.laihaojie.ideaplugin.JieBundle
import com.intellij.openapi.wm.StatusBar
import com.intellij.openapi.wm.StatusBarWidget
import com.intellij.util.Consumer
import java.awt.Component
import java.awt.event.MouseEvent
import javax.swing.Icon

class TimeWidget : StatusBarWidget, StatusBarWidget.TextPresentation {

    private var currentText = "Jie"

    override fun ID(): String = JieBundle.message("statusBarId")

    override fun getPresentation(): StatusBarWidget.WidgetPresentation {
        return this
    }

    override fun install(statusBar: StatusBar) {
        // 安装widget时需要的代码
    }

    override fun dispose() {
        // 清理资源
    }

    override fun getTooltipText(): String = "Jie"

    override fun getClickConsumer(): Consumer<MouseEvent>? = null

    override fun getText(): String = currentText

    override fun getAlignment(): Float = Component.CENTER_ALIGNMENT

    fun getIcon(): Icon? = null

    fun updateText(text: String) {
        currentText = text
    }
}

