package xyz.leequix.msceschedulevkbot.menu

class MainMenu : Menu() {
    override val menuId = MENU_ID

    override fun getMenuElements() = listOf("Привет", "Пока", "Эммм")

    override fun getMenuHeader() = "Выбирай"

    override fun onSelected(elementIndex: Int) {
        println("Hello")
    }

    companion object {
        const val MENU_ID = "MainMenu"
    }
}