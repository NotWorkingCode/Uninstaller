package not.working.code.uninstaller.presentation.ui.main

import by.kirich1409.viewbindingdelegate.viewBinding
import not.working.code.uninstaller.R
import not.working.code.uninstaller.databinding.FragmentMainBinding
import not.working.code.uninstaller.presentation.base.BaseFragment
import not.working.code.uninstaller.presentation.fragment_toolbar.FragmentToolbar

class MainFragment : BaseFragment(R.layout.fragment_main) {

    private val binding: FragmentMainBinding by viewBinding()

    override fun builder(): FragmentToolbar {
        return  FragmentToolbar.Builder()
            .withId(binding.mainToolbar.id)
            .withTitle(getString(R.string.app_name))
            .build()
    }

}