package not.working.code.uninstaller.di

import not.working.code.uninstaller.data.repository.InstalledAppRepository
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val RepositoryModule = module {

    factory<InstalledAppRepository> { InstalledAppRepository.Base(androidContext()) }

}