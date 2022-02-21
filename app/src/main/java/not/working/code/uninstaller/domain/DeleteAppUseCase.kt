package not.working.code.uninstaller.domain

import android.content.Context
import androidx.activity.result.ActivityResultLauncher
import not.working.code.uninstaller.model.AppInfo

class DeleteAppUseCase() {
    operator fun invoke(apps: List<AppInfo>, deleteLauncher: ActivityResultLauncher<String?>) {
        apps.forEach { app->
            deleteLauncher.launch(app.packageName)
        }
    }
}