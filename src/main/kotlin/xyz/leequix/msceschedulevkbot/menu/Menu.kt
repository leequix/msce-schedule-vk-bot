package xyz.leequix.msceschedulevkbot.menu

import com.petersamokhin.bots.sdk.clients.Group
import com.petersamokhin.bots.sdk.objects.Message
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component
import xyz.leequix.msceschedulevkbot.model.User
import xyz.leequix.msceschedulevkbot.service.UserService

abstract class Menu {
    lateinit var group: Group
    lateinit var userService: UserService

    abstract val menuId: String
    protected abstract fun getMenuElements(): List<String>
    protected abstract fun getMenuHeader(): String
    abstract fun onSelected(elementIndex: Int)

    private fun generateMenuString(): String {
        var menuString = String()
        val menuElements = getMenuElements()

        for(elementIndex in 1 .. menuElements.size) {
            menuString += "$elementIndex. ${menuElements[elementIndex - 1]}\n"
        }

        return menuString
    }

    fun show(user: User) {
        user.state["status"] = "MENU"
        user.state["menu"] = menuId
        userService.save(user)

        Message()
                .text(generateMenuString())
                .from(group)
                .to(user.vkontakteId)
                .title(getMenuHeader())
                .send()
    }
}