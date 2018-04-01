package xyz.leequix.msceschedulevkbot.command

import com.petersamokhin.bots.sdk.objects.Message
import xyz.leequix.msceschedulevkbot.model.User

class HelpCommand : Command() {
    override fun onExecuted(user: User) {
        Message()
                .title("Помощь")
                .text(generateHelpString())
                .from(group)
                .to(user.vkontakteId)
                .send()
    }

    private fun generateHelpString(): String {
        var helpString = String()
        for(commandName in help.keys) {
            helpString += "/$commandName - ${help[commandName]}\n"
        }
        return helpString
    }

    companion object {
        const val COMMAND_NAME = "help"
        const val COMMAND_DESCRIPTION = "Выводит это сообщение"
        private val help =
                mapOf(
                    Pair(OpenMainMenuCommand.COMMAND_NAME, OpenMainMenuCommand.COMMAND_DESCRIPTION),
                    Pair(CancelCommand.COMMAND_NAME, CancelCommand.COMMAND_DESCRIPTION),
                    Pair(HelpCommand.COMMAND_NAME, HelpCommand.COMMAND_DESCRIPTION)
                )
    }
}