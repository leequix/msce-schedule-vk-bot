package xyz.leequix.msceschedulevkbot.service

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import xyz.leequix.msceschedulevkbot.constant.UserState
import xyz.leequix.msceschedulevkbot.constant.UserStatus
import xyz.leequix.msceschedulevkbot.model.User
import xyz.leequix.msceschedulevkbot.repository.UserRepository

@Service
class UserService {
    @Autowired
    private lateinit var userRepository: UserRepository

    fun getOrCreateUser(vkontakteId: Int) = userRepository
            .findByVkontakteId(vkontakteId).orElseGet {
        val userState = HashMap<String, String>()
        userState[UserState.STATUS.state] = UserStatus.IDLE.name

        val user = User(vkontakteId, null, userState)
        userRepository.save(user)

        return@orElseGet user
    }

    fun save(user: User) = userRepository.save(user)

    fun setIdle(user: User) {
        user.state[UserState.STATUS.state] = UserStatus.IDLE.name
        userRepository.save(user)
    }
}