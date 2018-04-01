package xyz.leequix.msceschedulevkbot.service

import com.petersamokhin.bots.sdk.clients.Group
import com.petersamokhin.bots.sdk.objects.Message
import org.springframework.beans.factory.BeanFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.core.env.Environment
import org.springframework.core.env.get
import org.springframework.stereotype.Service
import xyz.leequix.msceschedulevkbot.constant.UserState
import xyz.leequix.msceschedulevkbot.constant.UserStatus
import xyz.leequix.msceschedulevkbot.menu.MainMenu
import xyz.leequix.msceschedulevkbot.menu.Menu
import xyz.leequix.msceschedulevkbot.model.User
import java.util.regex.Pattern

@Service
class MessageProcessingService {
    @Autowired
    lateinit var menuService: MenuService

    @Autowired
    lateinit var commandService: CommandService

    fun onGetMessage(user: User, message: Message) =
        if (Pattern.compile("^/\\w+$").matcher(message.text).matches()) {
            commandService.getCommandByName(message.text.substring(1))
                    .onExecuted(user)
        }
        else {
            when (user.state[UserState.STATUS.state]) {
                UserStatus.MENU.name ->
                    if (Pattern.compile("\\d+").matcher(message.text).matches()) {
                        val menu = menuService.getMenuById(user.state[UserState.MENU.state]!!)
                        menu.onSelected(user, message.text.toInt())
                    } else {
                    }
                else -> {
                }
            }
        }
}