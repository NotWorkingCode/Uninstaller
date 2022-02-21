package not.working.code.uninstaller.data.repository

import android.content.Context
import android.content.pm.ApplicationInfo
import android.content.pm.PackageInfo
import android.content.pm.PackageManager

interface InstalledAppRepository {
    fun getInstalledApp(): List<ApplicationInfo>

    class Base(
        private val context: Context
    ) : InstalledAppRepository {
        override fun getInstalledApp(): List<ApplicationInfo> {
            return context.packageManager.getInstalledApplications(PackageManager.GET_META_DATA)
        }
    }
}