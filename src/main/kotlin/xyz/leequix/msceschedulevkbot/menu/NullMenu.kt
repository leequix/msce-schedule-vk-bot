package xyz.leequix.msceschedulevkbot.menu

import xyz.leequix.msceschedulevkbot.model.User

class NullMenu : Menu() {
    override val menuId = ""

    override fun getMenuElements(user: User) = emptyList<String>()

    override fun getMenuHeader() = ""

    override fun onSelected(user: User, elementIndex: Int) {
    }
}