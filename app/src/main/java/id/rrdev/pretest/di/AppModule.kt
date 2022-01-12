package id.rrdev.pretest.di

import id.rrdev.pretest.ui.login.AuthViewModel
import id.rrdev.pretest.ui.product.ProductViewModel
import id.rrdev.pretest.ui.report.ReportViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module


val viewModelModule = module {
    viewModel { AuthViewModel(get()) }
    viewModel { ProductViewModel(get()) }
    viewModel { ReportViewModel(get()) }
}