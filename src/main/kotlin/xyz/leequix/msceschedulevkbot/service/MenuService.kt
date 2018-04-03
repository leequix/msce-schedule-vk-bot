package xyz.leequix.msceschedulevkbot.service

import com.petersamokhin.bots.sdk.clients.Group
import org.springframework.beans.factory.BeanFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import xyz.leequix.msceschedulevkbot.menu.MainMenu
import xyz.leequix.msceschedulevkbot.menu.Menu
import xyz.leequix.msceschedulevkbot.menu.NullMenu

@Service
class MenuService {
    @Autowired
    private lateinit var beanFactory: BeanFactory

    fun getMenuById(id: String) = beanFactory.getBean(Menu::class.java, id)
}