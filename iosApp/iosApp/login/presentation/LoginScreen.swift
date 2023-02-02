//
//  LoginScreen.swift
//  iosApp
//
//  Created by Mikhail Pankratov on 2023/01/26.
//  Copyright © 2023 orgName. All rights reserved.
//

import SwiftUI
import shared
import SimpleToast

struct LoginScreen: View {
    
    @ObservedObject var viewModel: iOSLoginViewModel
    private let firebaseAuth: FirebaseAuthentication
    private let fireStore: FirebaseFireStore
    
    @State private var isShowingToast = false
    private let toastOptions = SimpleToastOptions(
        alignment: .bottom,
        hideAfter: 3,
        backdropColor: Color.black.opacity(0.2),
        animation: .default,
        modifierType: .slide
    )
    
    init(firebaseAuth: FirebaseAuthentication, fireStore: FirebaseFireStore) {
        viewModel = iOSLoginViewModel(firebaseAuth: firebaseAuth)
        self.firebaseAuth = firebaseAuth
        self.fireStore = fireStore
    }
    
    var body: some View {
        VStack(spacing: 15.0) {
            NavigationLink(
                destination: HomeScreen(
                    firebaseAuth: firebaseAuth,
                    fireStore: fireStore
                ),
                isActive:
                    Binding(get: { viewModel.state.userId != nil }, set: { value in
                    
                })
            ) {
                EmptyView()
            }.hidden()
            
            NavigationLink(
                destination: RegisterScreen(
                    firebaseAuth: firebaseAuth,
                    fireStore: fireStore
                ),
                isActive:
                    Binding(get: { viewModel.state.newRegistration }, set: { value in
                    
                })
            ) {
                EmptyView()
            }.hidden()
            
            CustomTextField(
                titleKey: StringRes.Companion().editEmail,
                secured: false,
                text: Binding(get: {viewModel.state.email}, set: { value in
                    viewModel.onEvent(event: LoginEvent.EditEmail(newEmail: value))
                })
            )
            CustomTextField(
                titleKey: StringRes.Companion().editPassword,
                secured: true,
                text: Binding(get: {viewModel.state.password}, set:{ value in
                    viewModel.onEvent(event: LoginEvent.EditPassword(newPassword: value))
                                })
            )
            CustomButton(
                label: StringRes.Companion().login,
                action: {
                    viewModel.onEvent(event: LoginEvent.Login())
                })
            CustomButton(
                label: StringRes.Companion().register_,
                action: {
                    viewModel.onEvent(event: LoginEvent.Register())
                })
        }
        .padding(.all)
        .simpleToast(isPresented: Binding(get: { viewModel.state.error != nil }, set: {_,_ in
            viewModel.onEvent(event: LoginEvent.OnErrorSeen())
        }),options: toastOptions, content: {
            HStack {
                Text(viewModel.state.error?.message ?? "unknown error")
            }
            .padding(20)
            .background(Color.green)
            .foregroundColor(Color.white)
            .cornerRadius(16)
        })
        .navigationBarBackButtonHidden(true)
        .onDisappear{
            viewModel.dispose()
        }
    }
}
