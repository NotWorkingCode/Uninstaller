package not.working.code.uninstaller.presentation.fragment_toolbar

import android.view.MenuItem
import androidx.annotation.IdRes
import androidx.annotation.MenuRes


class FragmentToolbar(
    @IdRes val resId: Int,
    val title: String?,
    @MenuRes val menuId: Int,
    val menuItems: MutableList<Int>,
    val menuClicks: MutableList<MenuItem.OnMenuItemClickListener?>
) {
    companion object {
        val NO_TOOLBAR = -1
    }

    class Builder {
        private var resId: Int = -1
        private var menuId: Int = -1
        private var title: String = ""
        private var menuItems: MutableList<Int> = mutableListOf()
        private var menuClicks: MutableList<MenuItem.OnMenuItemClickListener?> = mutableListOf()

        fun withId(@IdRes resId: Int) = apply { this.resId = resId }

        fun withTitle(title: String) = apply { this.title = title }

        fun withMenu(@MenuRes menuId: Int) = apply { this.menuId = menuId }

        fun withMenuItems(menuItems: MutableList<Int>, menuClicks: MutableList<MenuItem.OnMenuItemClickListener?>) = apply {
                this.menuItems.addAll(menuItems)
                this.menuClicks.addAll(menuClicks)
        }
        fun build() = FragmentToolbar(resId, title, menuId, menuItems, menuClicks)
    }
}