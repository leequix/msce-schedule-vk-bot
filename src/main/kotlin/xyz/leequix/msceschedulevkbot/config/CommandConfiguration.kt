package xyz.leequix.msceschedulevkbot.config

import com.petersamokhin.bots.sdk.clients.Group
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Lazy
import org.springframework.context.annotation.Scope
import xyz.leequix.msceschedulevkbot.command.*
import xyz.leequix.msceschedulevkbot.service.MenuService
import xyz.leequix.msceschedulevkbot.service.UserService

@Configuration
class CommandConfiguration {
    @Autowired
    private lateinit var group: Group

    @Autowired
    private lateinit var userService: UserService

    @Autowired
    private lateinit var menuService: MenuService

    @Bean
    @Scope("prototype")
    @Lazy(true)
    fun getCommandByName(name: String): Command {
        val command = when(name) {
            OpenMainMenuCommand.COMMAND_NAME -> OpenMainMenuCommand()
            CancelCommand.COMMAND_NAME -> CancelCommand()
            HelpCommand.COMMAND_NAME -> HelpCommand()
            else -> UnknownCommand()
        }

        command.group = group
        command.userService = userService
        command.menuService = menuService

        return command
    }
}