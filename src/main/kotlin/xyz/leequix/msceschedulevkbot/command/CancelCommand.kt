package xyz.leequix.msceschedulevkbot.command

import com.petersamokhin.bots.sdk.objects.Message
import xyz.leequix.msceschedulevkbot.constant.UserState
import xyz.leequix.msceschedulevkbot.constant.UserStatus
import xyz.leequix.msceschedulevkbot.model.User

class CancelCommand : Command() {
    override fun onExecuted(user: User) =
        if (user.state[UserState.STATUS.state]!! == UserStatus.IDLE.name) {
            Message()
                    .title("Ошибка")
                    .text("Вы не выполняете никаких действия")
                    .from(group)
                    .to(user.vkontakteId)
                    .send()
        } else {
            Message()
                    .title("Успех")
                    .text("Действие отменено")
                    .from(group)
                    .to(user.vkontakteId)
                    .send()

            userService.setIdle(user)
        }

    companion object {
        const val COMMAND_NAME = "cancel"
    }
}