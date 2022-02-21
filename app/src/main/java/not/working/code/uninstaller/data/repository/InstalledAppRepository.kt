package not.working.code.uninstaller.data.repository

import android.content.Context
import android.content.pm.ApplicationInfo
import android.content.pm.PackageManager
import not.working.code.uninstaller.model.AppInfo

interface InstalledAppRepository {
    fun getInstalledApp(): List<AppInfo>

    class Base(
        private val context: Context
    ) : InstalledAppRepository {
        override fun getInstalledApp(): List<AppInfo> {
            val apps = ArrayList<ApplicationInfo>()
            context.packageManager.getInstalledApplications(PackageManager.GET_META_DATA).forEach { app ->
                if (
                    (app.flags and ApplicationInfo.FLAG_UPDATED_SYSTEM_APP != 1)
                    and
                    (app.flags and ApplicationInfo.FLAG_SYSTEM != 1)
                ) apps.add(app)
            }
            return apps.map {
                AppInfo(
                    name = it.loadLabel(context.packageManager).toString(),
                    packageName = it.packageName,
                    icon = context.packageManager.getApplicationIcon(it)
                )
            }
        }
    }
}