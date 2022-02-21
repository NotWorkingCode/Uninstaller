package not.working.code.uninstaller.domain

import not.working.code.uninstaller.data.repository.InstalledAppRepository
import not.working.code.uninstaller.model.AppInfo

class GetInstalledAppUseCase(
    private val repository: InstalledAppRepository
) {
    operator fun invoke(): List<AppInfo> {
        return repository.getInstalledApp()
    }
}