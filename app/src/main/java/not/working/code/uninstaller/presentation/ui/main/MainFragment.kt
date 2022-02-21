package not.working.code.uninstaller.presentation.ui.main

import android.os.Bundle
import android.view.MenuItem
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import by.kirich1409.viewbindingdelegate.viewBinding
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.LoadAdError
import com.google.android.gms.ads.MobileAds
import com.google.android.gms.ads.interstitial.InterstitialAd
import com.google.android.gms.ads.interstitial.InterstitialAdLoadCallback
import not.working.code.uninstaller.R
import not.working.code.uninstaller.databinding.FragmentMainBinding
import not.working.code.uninstaller.presentation.base.BaseFragment
import not.working.code.uninstaller.presentation.fragment_toolbar.FragmentToolbar
import not.working.code.uninstaller.utils.DeleteAppContract
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainFragment : BaseFragment(R.layout.fragment_main) {

    private val binding: FragmentMainBinding by viewBinding()
    private val viewModel: MainViewModel by viewModel()

    private val deleteLauncher = registerForActivityResult(DeleteAppContract()) {
        viewModel.loadInstalledApp()
        showAD()
    }

    private lateinit var adapter: MainAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        MobileAds.initialize(view.context)
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
            if(isLoading) {
                binding.mainRecycler.visibility = View.GONE
                binding.mainProgress.visibility = View.VISIBLE
            } else {
                binding.mainRecycler.visibility = View.VISIBLE
                binding.mainProgress.visibility = View.GONE
            }
        }
        viewModel.installedApp.observe(viewLifecycleOwner) { apps ->
            adapter.refresh(apps)
        }
    }

    private fun showAD() {
        val ADRequest = AdRequest.Builder().build()
        InterstitialAd.load(activity,"ca-app-pub-4878763915270030/4963426766", ADRequest, object : InterstitialAdLoadCallback() {
            override fun onAdFailedToLoad(adError: LoadAdError) {
            }

            override fun onAdLoaded(interstitialAd: InterstitialAd) {
                interstitialAd.show(activity)
            }
        })
    }

    override fun builder(): FragmentToolbar {
        return  FragmentToolbar.Builder()
            .withId(binding.mainToolbar.id)
            .withTitle(getString(R.string.app_name))
            .withMenu(R.menu.menu_main_toolbar)
            .withMenuItems(
                mutableListOf(
                    R.id.menu_main_toolbar_delete
                ),
                mutableListOf(MenuItem.OnMenuItemClickListener {
                    viewModel.deleteApp(deleteLauncher)
                    true
                })
            )
            .build()
    }

}