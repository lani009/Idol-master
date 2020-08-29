package com.wimi.idolmaster.di

import com.wimi.idolmaster.data.RemoteClient
import com.wimi.idolmaster.data.api.GitApi
import com.wimi.idolmaster.data.api.WimiApi
import com.wimi.idolmaster.data.datasource.GitDataSource
import com.wimi.idolmaster.data.datasource.GitRemoteDataSource
import com.wimi.idolmaster.data.datasource.WimiDataSource
import com.wimi.idolmaster.data.datasource.WimiRemoteDataSource
import com.wimi.idolmaster.data.repository.GitRepositoryImpl
import com.wimi.idolmaster.data.repository.WimiRepositoryImpl
import com.wimi.idolmaster.domain.repository.GitRepository
import com.wimi.idolmaster.domain.repository.WimiRepository
import com.wimi.idolmaster.domain.usecase.*
import com.wimi.idolmaster.ui.choose.ChooserViewModel
import com.wimi.idolmaster.ui.choose.concept.ConceptViewModel
import com.wimi.idolmaster.ui.choose.idol.IdolViewModel
import com.wimi.idolmaster.ui.home.HomeViewModel
import com.wimi.idolmaster.ui.login.LoginViewModel
import com.wimi.idolmaster.ui.main.MainViewModel
import com.wimi.idolmaster.ui.mypage.MyPageViewModel
import com.wimi.idolmaster.ui.mypage.myreviews.MyReviewsViewModel
import com.wimi.idolmaster.ui.plan.PlanViewModel
import com.wimi.idolmaster.ui.review.ReviewViewModel
import com.wimi.idolmaster.ui.review.dialog.WriteReviewViewModel
import com.wimi.idolmaster.ui.splash.SplashViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel { SplashViewModel(get()) }
    viewModel { LoginViewModel(get(), get()) }
    viewModel { ChooserViewModel(get()) }
    viewModel { IdolViewModel(get()) }
    viewModel { ConceptViewModel(get(), get()) }
    viewModel { MainViewModel(get()) }
    viewModel { HomeViewModel(get(), get()) }
    viewModel { MyPageViewModel(get()) }
    viewModel { ReviewViewModel(get(), get()) }
    viewModel { PlanViewModel(get()) }
    viewModel { WriteReviewViewModel(get(), get()) }
    viewModel { MyReviewsViewModel(get()) }
}

val remoteModule = module {
    single { RemoteClient }
}

val dataSourceModule = module {
    single { GitRemoteDataSource(get()) as GitDataSource}
    single { WimiRemoteDataSource(get()) as WimiDataSource}
}

val apiModule = module {
    single { gitApi }
    single { wimiApi }
}

val repositoryModule = module {
    single { GitRepositoryImpl(get()) as GitRepository }
    single { WimiRepositoryImpl(get()) as WimiRepository }
}

val useCaseModule = module {
    single { GetGistsPublicUseCase(get()) }
    single { GetTasteListUseCase(get()) }
    single { LoginUseCase(get()) }
    single { GetReviewUseCase(get()) }
    single { SaveReviewUseCase(get()) }
}

val retrofit = RemoteClient.createRetrofit(true)
private val gitApi = retrofit.create(GitApi::class.java)
private val wimiApi = retrofit.create(WimiApi::class.java)

val appModules = listOf(
    remoteModule,
    dataSourceModule,
    viewModelModule,
    apiModule,
    repositoryModule,
    useCaseModule
)