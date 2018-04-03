package xyz.leequix.msceschedulevkbot.command

import com.petersamokhin.bots.sdk.objects.Message
import xyz.leequix.msceschedulevkbot.model.User

class UnknownCommand : Command() {
    override fun onExecuted(user: User) {
        Message()
                .title(UNKNOWN_COMMAND_TITLE)
                .text(UNKNOWN_COMMAND_MESSAGE)
                .from(group)
                .to(user.vkontakteId)
                .send()
    }

    companion object {
        private const val UNKNOWN_COMMAND_TITLE = "Ошибка"
        private const val UNKNOWN_COMMAND_MESSAGE = "Данной команды не существует"
    }
}