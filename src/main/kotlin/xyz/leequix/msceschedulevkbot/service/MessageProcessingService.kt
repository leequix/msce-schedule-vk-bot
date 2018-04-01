package xyz.leequix.msceschedulevkbot.service

import com.petersamokhin.bots.sdk.clients.Group
import com.petersamokhin.bots.sdk.objects.Message
import org.springframework.beans.factory.BeanFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.core.env.Environment
import org.springframework.core.env.get
import org.springframework.stereotype.Service
import xyz.leequix.msceschedulevkbot.menu.Menu
import xyz.leequix.msceschedulevkbot.model.User
import java.util.regex.Pattern

@Service
class MessageProcessingService {
    @Autowired
    lateinit var beanFactory: BeanFactory

    fun onGetMessage(user: User, message: Message) = when(user.state["status"]) {
        "IDLE" ->
            if (message.text == "menu") {
                val menu = beanFactory.getBean(Menu::class.java, "MainMenu")
                menu.show(user)
            } else {}
        Menu.MENU_USER_STATUS ->
            if(Pattern.compile("\\d+").matcher(message.text).matches()) {
                val menu = beanFactory.getBean(Menu::class.java, user.state["menu"])
                menu.onSelected(message.text.toInt())
            } else {}
        else -> {}
    }
}