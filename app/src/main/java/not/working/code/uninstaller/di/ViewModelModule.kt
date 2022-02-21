package not.working.code.uninstaller.di

import not.working.code.uninstaller.presentation.ui.main.MainViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val ViewModelModule = module {

    viewModel { MainViewModel(get()) }

}