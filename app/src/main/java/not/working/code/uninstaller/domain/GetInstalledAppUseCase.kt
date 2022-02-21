package not.working.code.uninstaller.domain

import android.content.pm.ApplicationInfo
import not.working.code.uninstaller.data.repository.InstalledAppRepository

class GetInstalledAppUseCase(
    private val repository: InstalledAppRepository
) {
    operator fun invoke(): List<ApplicationInfo> {
        return repository.getInstalledApp()
    }
}