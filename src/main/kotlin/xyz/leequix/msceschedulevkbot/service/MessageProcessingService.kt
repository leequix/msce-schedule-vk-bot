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

    fun onGetMessage(user: User, message: Message) = when(user.state[UserState.STATUS.state]) {
        UserStatus.IDLE.name ->
            if (message.text == "menu") {
                val menu = menuService.getMenuById(MainMenu.MENU_ID)
                menu.show(user)
            } else {}
        UserStatus.MENU.name ->
            if(Pattern.compile("\\d+").matcher(message.text).matches()) {
                val menu = menuService.getMenuById(user.state[UserState.MENU.state]!!)
                menu.onSelected(user, message.text.toInt())
            } else {}
        else -> {}
    }
}