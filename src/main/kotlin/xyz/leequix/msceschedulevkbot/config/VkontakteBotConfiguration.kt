package xyz.leequix.msceschedulevkbot.config

import com.petersamokhin.bots.sdk.clients.Group
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import xyz.leequix.msceschedulevkbot.service.MessageProcessingService
import xyz.leequix.msceschedulevkbot.service.UserService

@Configuration
class VkontakteBotConfiguration {
    @Value("\${vk.group.id}")
    private val groupId: Int = 0

    @Value("\${vk.group.token}")
    private val groupToken: String = ""

    @Autowired
    private lateinit var userService: UserService

    @Autowired
    private lateinit var messageProcessingService: MessageProcessingService

    @Bean
    fun getGroup(): Group {
        val group = Group(groupId, groupToken)

        group.onMessage {
            val user = userService.getOrCreateUser(it.authorId())
            messageProcessingService.onGetMessage(user, it)
        }

        return group
    }
}