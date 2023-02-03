//
//  ResetPasswordScreen.swift
//  iosApp
//
//  Created by Mikhail Pankratov on 2023/02/02.
//  Copyright © 2023 orgName. All rights reserved.
//

import SwiftUI
import shared
import SimpleToast

struct ResetPasswordScreen: View {
    
    @ObservedObject var viewModel: IosResetPasswordViewModel
    private let firebaseAuth: FirebaseAuthentication
    private let fireStore: FirebaseFireStore
    private let toastOptions = SimpleToastOptions(
        alignment: .bottom,
        hideAfter: 3,
        animation: .default,
        modifierType: .slide
    )
    
    init(firebaseAuth: FirebaseAuthentication, fireStore: FirebaseFireStore) {
        self.viewModel = IosResetPasswordViewModel(firebaseAuthentication: firebaseAuth)
        self.firebaseAuth = firebaseAuth
        self.fireStore = fireStore
    }
    
    var body: some View {
        VStack(alignment: HorizontalAlignment.center) {
            NavigationLink(destination: LoginScreen(firebaseAuth: firebaseAuth, fireStore: fireStore), isActive:
                            Binding(get: {viewModel.state.navigateToHome}, set: { value in
                
            })
            ){
                    EmptyView()
            }.hidden()
            Spacer()
            
            EditText(titleKey: StringRes.Companion().editPassword, secured: false, text: Binding(
                get:{ viewModel.state.email },
                set: { value in
                    viewModel.onEvent(event: ResetPasswordEvent.EditEmail(email: value))
                }))
            RoundedButton(
                label: StringRes.Companion().reset,
                action: {
                    viewModel.onEvent(event: ResetPasswordEvent.OnResetPasswordClick())
                }
            )
            Spacer()
        }
        .simpleToast(isPresented: Binding(get: { viewModel.state.error != nil }, set: {_,_ in
            viewModel.onEvent(event: ResetPasswordEvent.OnErrorSeen())
        }),options: toastOptions, content: {
            HStack {
                Text(viewModel.state.error?.message ?? "unknown error")
            }
            .padding(20)
            .background(Color.green)
            .foregroundColor(Color.white)
            .cornerRadius(16)
        })
        .padding(.all, 16)
        .onAppear {
            viewModel.observeState()
        }
        .onDisappear{
            viewModel.dispose()
        }
    }
}

