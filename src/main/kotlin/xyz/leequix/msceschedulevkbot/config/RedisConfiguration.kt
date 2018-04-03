package xyz.leequix.msceschedulevkbot.config

import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import redis.clients.jedis.Jedis

@Configuration
class RedisConfiguration {
    @Value("\${redis.host}")
    private var host: String = "localhost"

    @Value("\${redis.port}")
    private var port: Int = 6379

    @Bean
    fun getJedis() = Jedis(host, port)
}