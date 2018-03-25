package xyz.leequix.msceschedulevkbot.repository

import org.springframework.data.mongodb.repository.MongoRepository
import xyz.leequix.msceschedulevkbot.model.User
import java.util.*

interface UserRepository : MongoRepository<User, String> {
    fun findByVkontakteId(vkontakteId: Int): Optional<User>
}