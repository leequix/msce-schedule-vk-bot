package xyz.leequix.msceschedulevkbot.repository

import org.springframework.data.mongodb.repository.MongoRepository
import xyz.leequix.msceschedulevkbot.model.Group

interface GroupRepositroy : MongoRepository<Group, String> {
    fun existsByNumber(number: String): Boolean
}