package not.working.code.uninstaller.di

import not.working.code.uninstaller.domain.DeleteAppUseCase
import not.working.code.uninstaller.domain.GetInstalledAppUseCase
import org.koin.dsl.module

val UseCaseModule = module {

    factory { GetInstalledAppUseCase(get()) }
    factory { DeleteAppUseCase() }

}