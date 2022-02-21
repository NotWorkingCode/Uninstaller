package not.working.code.uninstaller

import android.app.Application
import not.working.code.uninstaller.di.RepositoryModule
import not.working.code.uninstaller.di.UseCaseModule
import not.working.code.uninstaller.di.ViewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class App : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(applicationContext)
            modules(
                RepositoryModule,
                UseCaseModule,
                ViewModelModule
            )
        }
    }

}