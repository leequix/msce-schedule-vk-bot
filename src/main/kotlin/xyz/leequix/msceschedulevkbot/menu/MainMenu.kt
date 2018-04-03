package xyz.leequix.msceschedulevkbot.menu

import com.petersamokhin.bots.sdk.callbacks.callbackapi.ExecuteCallback
import com.petersamokhin.bots.sdk.objects.Message
import xyz.leequix.msceschedulevkbot.model.User

class MainMenu : Menu() {
    override val menuId = MENU_ID

    override fun getMenuElements(user: User) =
            listOf(
                    "Подписаться на группу",
                    "Отписаться от группы"
            )

    override fun getMenuHeader() = "Главное меню"

    override fun onSelected(user: User, elementIndex: Int) = when(elementIndex) {
        1 -> {
            val menu = menuService.getMenuById(SelectGroupMenu.MENU_ID)
            menu.show(user)
        }
        2 -> {
            if (user.group === null) {
                Message()
                        .title("Ошибка")
                        .text("Вы не подписаны ни на одну группу")
                        .from(group)
                        .to(user.vkontakteId)
                        .send()
            } else {
                Message()
                        .title("Успех")
                        .text("Вы отписались от группы ${user.group!!.number}")
                        .from(group)
                        .to(user.vkontakteId)
                        .send()
                user.group = null
                userService.save(user)
            }
            userService.setIdle(user)
        }
        else -> sendMessageAboutUnknownElementIndex(user)
    }

    companion object {
        const val MENU_ID = "MainMenu"
    }
}