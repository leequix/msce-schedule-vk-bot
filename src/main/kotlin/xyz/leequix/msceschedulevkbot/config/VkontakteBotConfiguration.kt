package xyz.leequix.msceschedulevkbot.config

import com.petersamokhin.bots.sdk.clients.Group
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class VkontakteBotConfiguration {
    @Value("\${vk.group.id}")
    private val groupId: Int = 0

    @Value("\${vk.group.token}")
    private val groupToken: String = ""

    @Bean
    fun getGroup(): Group {
        val group = Group(groupId, groupToken)

        group.onMessage {
            println("${it.authorId()}: ${it.text}")
        }

        return group
    }
}