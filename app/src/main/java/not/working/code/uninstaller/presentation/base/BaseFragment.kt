package not.working.code.uninstaller.presentation.base

import android.os.Bundle
import android.view.View
import androidx.annotation.LayoutRes
import androidx.fragment.app.Fragment
import not.working.code.uninstaller.presentation.fragment_toolbar.FragmentToolbar
import not.working.code.uninstaller.presentation.fragment_toolbar.ToolbarManager

abstract class BaseFragment(
    @LayoutRes private val layout: Int
) : Fragment(layout) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        ToolbarManager(builder(), view).prepareToolbar()
    }

    protected abstract fun builder(): FragmentToolbar
}