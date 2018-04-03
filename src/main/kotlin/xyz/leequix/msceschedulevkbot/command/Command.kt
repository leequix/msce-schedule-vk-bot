package xyz.leequix.msceschedulevkbot.command

import com.petersamokhin.bots.sdk.clients.Group
import xyz.leequix.msceschedulevkbot.model.User
import xyz.leequix.msceschedulevkbot.service.MenuService
import xyz.leequix.msceschedulevkbot.service.UserService

abstract class Command {
    lateinit var group: Group
    lateinit var userService: UserService
    lateinit var menuService: MenuService

    abstract fun onExecuted(user: User)
}