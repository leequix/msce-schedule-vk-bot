package xyz.leequix.msceschedulevkbot.menu

import xyz.leequix.msceschedulevkbot.model.User

class MainMenu : Menu() {
    override val menuId = MENU_ID

    override fun getMenuElements() = listOf("Привет", "Пока", "Эммм")

    override fun getMenuHeader() = "Выбирай"

    override fun onSelected(user: User, elementIndex: Int) = when(elementIndex) {
        1 -> {

        }
        else -> sendMessageAboutUnknownElementIndex(user)
    }

    companion object {
        const val MENU_ID = "MainMenu"
    }
}