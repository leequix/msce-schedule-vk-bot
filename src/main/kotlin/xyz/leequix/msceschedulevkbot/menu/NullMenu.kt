package xyz.leequix.msceschedulevkbot.menu

class NullMenu : Menu() {
    override val menuId = ""

    override fun getMenuElements() = emptyList<String>()

    override fun getMenuHeader() = ""

    override fun onSelected(elementIndex: Int) {
    }
}