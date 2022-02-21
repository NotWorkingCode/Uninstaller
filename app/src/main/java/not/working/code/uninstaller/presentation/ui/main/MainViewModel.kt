package not.working.code.uninstaller.presentation.ui.main

import androidx.activity.result.ActivityResultLauncher
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.LoadAdError
import com.google.android.gms.ads.interstitial.InterstitialAd
import com.google.android.gms.ads.interstitial.InterstitialAdLoadCallback
import com.google.android.material.snackbar.Snackbar
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import not.working.code.uninstaller.domain.DeleteAppUseCase
import not.working.code.uninstaller.domain.GetInstalledAppUseCase
import not.working.code.uninstaller.model.AppInfo
import not.working.code.uninstaller.utils.mutation

class MainViewModel(
    private val getInstalledAppUseCase: GetInstalledAppUseCase,
    private val deleteAppUseCase: DeleteAppUseCase
) : ViewModel() {

    private val _installedApp: MutableLiveData<List<AppInfo>> = MutableLiveData()
    private val _isLoading: MutableLiveData<Boolean> = MutableLiveData()

    val installedApp: LiveData<List<AppInfo>> = _installedApp
    val isLoading: LiveData<Boolean> = _isLoading

    fun loadInstalledApp() {
        _isLoading.value = true
        viewModelScope.launch(Dispatchers.IO) {
            val apps = getInstalledAppUseCase()
            withContext(Dispatchers.Main) {
                _installedApp.value = apps
                _isLoading.value = false
            }
        }
    }

    fun selectItem(position: Int) {
        _installedApp.mutation {
            it.value?.get(position)?.let { appInfo ->
                appInfo.isSelected = !appInfo.isSelected
            }
        }
    }

    fun deleteApp(deleteLauncher: ActivityResultLauncher<String?>) {
        val apps = getSelectedApps()
        if(apps.isNotEmpty())
            viewModelScope.launch {
                deleteAppUseCase(getSelectedApps(), deleteLauncher)
            }
    }

    private fun getSelectedApps(): List<AppInfo> {
        val apps = ArrayList<AppInfo>()
        installedApp.value?.forEach {
            if (it.isSelected)
                apps.add(it)
        }
        return apps
    }


}