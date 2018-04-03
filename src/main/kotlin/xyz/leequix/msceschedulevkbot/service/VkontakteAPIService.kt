package xyz.leequix.msceschedulevkbot.service

import com.mashape.unirest.http.Unirest
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Service
import java.util.*

@Service
class VkontakteAPIService {
    @Value("\${vk.group.token}")
    private lateinit var accessToken: String

    fun sendMessage(userIds: List<Int>, message: String) {
        Unirest.post("$VK_API_URL/messages.send")
                .field("access_token", accessToken)
                .field("v", "5.74")
                .field("random_id", System.currentTimeMillis())
                .field("user_ids", userIds.joinToString(","))
                .field("message", message.replace("\n", "<br>")).asString()
    }

    companion object {
        private val random = Random()
        private const val VK_API_URL = "https://api.vk.com/method"
    }
}