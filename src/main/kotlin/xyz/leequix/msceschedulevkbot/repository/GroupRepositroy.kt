package xyz.leequix.msceschedulevkbot.repository

import org.springframework.data.mongodb.repository.MongoRepository
import xyz.leequix.msceschedulevkbot.model.Group
import java.util.*

interface GroupRepositroy : MongoRepository<Group, String> {
    fun existsByNumber(number: String): Boolean
    fun findByNumber(number: String): Optional<Group>
}