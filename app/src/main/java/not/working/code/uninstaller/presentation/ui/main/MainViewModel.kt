package not.working.code.uninstaller.presentation.ui.main

import android.content.pm.ApplicationInfo
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import not.working.code.uninstaller.domain.GetInstalledAppUseCase
import not.working.code.uninstaller.model.AppInfo

class MainViewModel(
    private val getInstalledAppUseCase: GetInstalledAppUseCase
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

}