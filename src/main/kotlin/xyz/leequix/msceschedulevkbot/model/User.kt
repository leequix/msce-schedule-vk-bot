package xyz.leequix.msceschedulevkbot.model

import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document

@Document
data class User(val vkontakteId: Int, val group: Group?, val state: MutableMap<String, String>) {
    @Id
    lateinit var id: String
}