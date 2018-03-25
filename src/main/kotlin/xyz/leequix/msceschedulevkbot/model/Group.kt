package xyz.leequix.msceschedulevkbot.model

import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document

@Document
data class Group(val number: String) {
    @Id
    lateinit var id: String
}
