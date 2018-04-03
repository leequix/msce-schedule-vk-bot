package xyz.leequix.msceschedulevkbot.config

import com.coxautodev.graphql.client.GraphQLClient
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class GraphQLClientConfiguration {
    @Value("\${graphql.endpoint}")
    private lateinit var graphQLEndpoint: String

    @Bean
    fun getClient() = GraphQLClient(graphQLEndpoint)
}