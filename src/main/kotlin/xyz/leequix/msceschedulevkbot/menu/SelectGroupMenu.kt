package xyz.leequix.msceschedulevkbot.menu

import com.petersamokhin.bots.sdk.objects.Message
import xyz.leequix.msceschedulevkbot.constant.UserState
import xyz.leequix.msceschedulevkbot.model.Group
import xyz.leequix.msceschedulevkbot.model.User
import xyz.leequix.msceschedulevkbot.util.GroupsToStateSerializer

class SelectGroupMenu : Menu() {
    override val menuId: String = MENU_ID

    override fun getMenuElements(user: User): List<String> {
        val groups = groupService.findAll()
        if (groups.isEmpty()) {
            Message()
                    .title("Ошибка")
                    .text("У нас еще нет ни одной группы")
                    .from(group)
                    .to(user.vkontakteId)
                    .send()
            userService.setIdle(user)
        } else {
            val menuElements = groups.map { it.number }

            user.state[UserState.MENU_ELEMENTS.state] =
                    GroupsToStateSerializer.serialize(menuElements)

            return menuElements
        }
        return emptyList()
    }

    override fun getMenuHeader() = "Выбор группы"

    override fun onSelected(user: User, elementIndex: Int) {
        val groups = GroupsToStateSerializer
                .deserialize(user.state[UserState.MENU_ELEMENTS.state]!!)
        if (elementIndex >= 1 && elementIndex <= groups.size) {
            val groupNumber = groups[elementIndex - 1]
            val subscriptionGroup = groupService.findByNumber(groupNumber)
            if (subscriptionGroup === null) {
                Message()
                        .title("Ошибка")
                        .text("Данной группы больше не существует")
                        .from(group)
                        .to(user.vkontakteId)
                        .send()
                userService.setIdle(user)
            } else {
                if(user.group == subscriptionGroup) {
                    Message()
                            .title("Ошибка")
                            .text("Вы и так подписаны на эту группу")
                            .from(group)
                            .to(user.vkontakteId)
                            .send()
                    userService.setIdle(user)
                } else {
                    user.group = subscriptionGroup
                    userService.save(user)
                    Message()
                            .title("Успех")
                            .text("Вы успешно подписались на группу $groupNumber")
                            .from(group)
                            .to(user.vkontakteId)
                            .send()
                    userService.setIdle(user)
                }
            }
        } else {
            userService.setIdle(user)
            sendMessageAboutUnknownElementIndex(user)
        }
    }

    companion object {
        const val MENU_ID = "SelectGroupMenu"
    }
}