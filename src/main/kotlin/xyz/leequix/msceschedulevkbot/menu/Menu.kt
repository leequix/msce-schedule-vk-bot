package xyz.leequix.msceschedulevkbot.menu

import com.petersamokhin.bots.sdk.clients.Group
import com.petersamokhin.bots.sdk.objects.Message
import org.springframework.beans.factory.BeanFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component
import xyz.leequix.msceschedulevkbot.constant.UserState
import xyz.leequix.msceschedulevkbot.constant.UserStatus
import xyz.leequix.msceschedulevkbot.model.User
import xyz.leequix.msceschedulevkbot.service.MenuService
import xyz.leequix.msceschedulevkbot.service.UserService

abstract class Menu {
    lateinit var group: Group
    lateinit var userService: UserService
    lateinit var menuService: MenuService

    abstract val menuId: String
    protected abstract fun getMenuElements(): List<String>
    protected abstract fun getMenuHeader(): String
    abstract fun onSelected(user:User, elementIndex: Int)

    private fun generateMenuString(): String {
        var menuString = String()
        val menuElements = getMenuElements()

        for(elementIndex in 1 .. menuElements.size) {
            menuString += "$elementIndex. ${menuElements[elementIndex - 1]}\n"
        }

        return menuString
    }

    fun show(user: User) {
        user.state[UserState.STATUS.state] = UserStatus.MENU.name
        user.state[UserState.MENU.state] = menuId
        userService.save(user)

        Message()
                .text(generateMenuString())
                .from(group)
                .to(user.vkontakteId)
                .title(getMenuHeader())
                .send()
    }

    protected fun sendMessageAboutUnknownElementIndex(user: User) {
        Message()
                .text(MENU_UNKNOWN_ELEMENT_INDEX_MESSAGE)
                .from(group)
                .to(user.vkontakteId)
                .title(getMenuHeader())
                .send()
    }

    companion object {
        const val MENU_UNKNOWN_ELEMENT_INDEX_MESSAGE = "Нет такого варианта"
    }
}