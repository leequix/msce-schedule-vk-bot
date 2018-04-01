package xyz.leequix.msceschedulevkbot.command

import xyz.leequix.msceschedulevkbot.menu.MainMenu
import xyz.leequix.msceschedulevkbot.model.User


class OpenMainMenuCommand : Command() {
    override fun onExecuted(user: User) {
        val menu = menuService.getMenuById(MainMenu.MENU_ID)
        menu.show(user)
    }

    companion object {
        const val COMMAND_NAME = "main_menu"
    }
}