package not.working.code.uninstaller.presentation.ui.main

import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import by.kirich1409.viewbindingdelegate.viewBinding
import not.working.code.uninstaller.R
import not.working.code.uninstaller.databinding.FragmentMainBinding
import not.working.code.uninstaller.presentation.base.BaseFragment
import not.working.code.uninstaller.presentation.fragment_toolbar.FragmentToolbar
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainFragment : BaseFragment(R.layout.fragment_main) {

    private val binding: FragmentMainBinding by viewBinding()
    private val viewModel: MainViewModel by viewModel()

    private lateinit var adapter: MainAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        configureRecyclerView()
        observeViewModel()
        viewModel.loadInstalledApp()
    }

    private fun getConfigureAdapter(): MainAdapter {
        return MainAdapter { selectedItemPosition ->
            viewModel.selectItem(selectedItemPosition)
        }
    }

    private fun configureRecyclerView() {
        adapter = getConfigureAdapter()

        binding.mainRecycler.layoutManager = LinearLayoutManager(activity)
        binding.mainRecycler.adapter = adapter
    }

    private fun observeViewModel() {
        viewModel.isLoading.observe(viewLifecycleOwner) { isLoading ->
            binding.mainProgress.visibility =
                if(isLoading)
                    View.VISIBLE
                else
                    View.GONE
        }
        viewModel.installedApp.observe(viewLifecycleOwner) { apps ->
            adapter.refresh(apps)
        }
    }

    override fun builder(): FragmentToolbar {
        return  FragmentToolbar.Builder()
            .withId(binding.mainToolbar.id)
            .withTitle(getString(R.string.app_name))
            .build()
    }

}