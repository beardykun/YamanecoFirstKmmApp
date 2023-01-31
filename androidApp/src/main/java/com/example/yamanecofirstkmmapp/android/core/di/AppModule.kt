package com.example.yamanecofirstkmmapp.android.core.di

import com.example.yamanecofirstkmmapp.core.firebase.data.FirebaseAuthentication
import com.example.yamanecofirstkmmapp.core.firebase.data.FirebaseFireStore
import com.example.yamanecofirstkmmapp.home.domain.GetUserUseCase
import com.example.yamanecofirstkmmapp.home.domain.LogoutUserUseCase
import com.example.yamanecofirstkmmapp.login.domain.LoginUserUseCase
import com.example.yamanecofirstkmmapp.register.domain.RegisterUserUseCase
import com.example.yamanecofirstkmmapp.start.domain.StartAppUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.ktor.client.*
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideFirebaseAuthentication(): FirebaseAuthentication {
        return FirebaseAuthentication()
    }

    @Provides
    @Singleton
    fun provideFirebaseFireStore(): FirebaseFireStore {
        return FirebaseFireStore()
    }

    @Provides
    @Singleton
    fun provideRegisterUserUseCase(
        firebaseAuthentication: FirebaseAuthentication,
        fireStore: FirebaseFireStore
    ): RegisterUserUseCase =
        RegisterUserUseCase(firebaseAuthentication, fireStore)

    @Provides
    @Singleton
    fun provideLoginUserUseCase(
        firebaseAuthentication: FirebaseAuthentication
    ): LoginUserUseCase =
        LoginUserUseCase(firebaseAuthentication)

    @Provides
    @Singleton
    fun provideStartAppUseCase(
        firebaseAuthentication: FirebaseAuthentication
    ): StartAppUseCase =
        StartAppUseCase(firebaseAuthentication)

    @Provides
    @Singleton
    fun provideLogoutUserUseCase(
        firebaseAuthentication: FirebaseAuthentication
    ): LogoutUserUseCase =
        LogoutUserUseCase(firebaseAuthentication)

    @Provides
    @Singleton
    fun provideGetUserUseCase(
        fireStore: FirebaseFireStore,
        firebaseAuthentication: FirebaseAuthentication
    ): GetUserUseCase =
        GetUserUseCase(fireStore, firebaseAuthentication)
}