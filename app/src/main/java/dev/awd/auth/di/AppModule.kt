package dev.awd.auth.di

import dev.awd.auth.data.remote.AuthClient
import dev.awd.auth.data.repository.AuthRepoImpl
import dev.awd.auth.domain.repository.AuthRepository
import dev.awd.auth.domain.usecase.LoginUseCase
import dev.awd.auth.domain.usecase.LogoutUseCase
import dev.awd.auth.domain.usecase.RegisterUseCase
import dev.awd.auth.domain.validationusecases.ValidateEmailUseCase
import dev.awd.auth.domain.validationusecases.ValidatePasswordUseCase
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create

interface AppModule {
    val authClient: AuthClient
    val authRepository: AuthRepository
    val loginUseCase: LoginUseCase
    val registerUseCase: RegisterUseCase
    val logoutUseCase: LogoutUseCase
    val validateEmailUseCase: ValidateEmailUseCase
    val validatePasswordUseCase: ValidatePasswordUseCase
}


object AppModuleImpl : AppModule {

    private const val BASE_URL = "https://dummyjson.com/"

    override val authClient: AuthClient by lazy {

        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build().create()
    }
    override val authRepository: AuthRepository by lazy {
        AuthRepoImpl(authClient)
    }
    override val loginUseCase: LoginUseCase by lazy {
        LoginUseCase(authRepository)
    }
    override val registerUseCase: RegisterUseCase by lazy {
        RegisterUseCase(authRepository)
    }
    override val logoutUseCase: LogoutUseCase by lazy {
        LogoutUseCase(authRepository)
    }

    override val validateEmailUseCase: ValidateEmailUseCase by lazy {
        ValidateEmailUseCase()
    }
    override val validatePasswordUseCase: ValidatePasswordUseCase by lazy {
        ValidatePasswordUseCase()
    }
}