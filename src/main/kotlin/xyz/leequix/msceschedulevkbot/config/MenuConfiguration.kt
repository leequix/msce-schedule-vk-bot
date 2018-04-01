package xyz.leequix.msceschedulevkbot.config

import com.petersamokhin.bots.sdk.clients.Group
import org.springframework.beans.factory.BeanFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Lazy
import org.springframework.context.annotation.Scope
import xyz.leequix.msceschedulevkbot.menu.MainMenu
import xyz.leequix.msceschedulevkbot.menu.Menu
import xyz.leequix.msceschedulevkbot.menu.NullMenu
import xyz.leequix.msceschedulevkbot.service.MenuService
import xyz.leequix.msceschedulevkbot.service.UserService

@Configuration
class MenuConfiguration {
    @Autowired
    private lateinit var group: Group

    @Autowired
    private lateinit var userService: UserService

    @Autowired
    private lateinit var menuService: MenuService

    @Bean
    @Scope("prototype")
    @Lazy(true)
    fun getMenuById(id: String): Menu {
        val menu = when (id) {
            MainMenu.MENU_ID -> MainMenu()
            else -> NullMenu()
        }
        menu.group = group
        menu.userService = userService
        menu.menuService = menuService

        return menu
    }
}