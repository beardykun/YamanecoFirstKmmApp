//
//  RegisterScreen.swift
//  iosApp
//
//  Created by Mikhail Pankratov on 2023/01/30.
//  Copyright © 2023 orgName. All rights reserved.
//

import SwiftUI
import shared
import SimpleToast

struct RegisterScreen: View {
    
    @ObservedObject var viewModel: IosRegisterViewModel
    private let firebaseAuth: FirebaseAuthentication
    private let fireStore: FirebaseFireStore
    private let toastOptions = SimpleToastOptions(
        alignment: .bottom,
        hideAfter: 3,
        animation: .default,
        modifierType: .slide
    )
    
    init(firebaseAuth: FirebaseAuthentication, fireStore: FirebaseFireStore) {
        self.viewModel = IosRegisterViewModel(firebaseAuthentication: firebaseAuth, fireStore: fireStore)
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
                    Binding(get: { viewModel.state.isRegistered }, set: { value in
                        
                    })
            ) {
                EmptyView()
            }.hidden()
            
            EditText(
                titleKey: StringRes.Companion().editName,
                secured: false,
                text: Binding(get: { viewModel.state.name }, set: { value in
                    viewModel.onEvent(event: RegisterEvent.EditName(newName: value))
                })
            )
            EditText(
                titleKey: StringRes.Companion().editEmail,
                secured: false,
                text: Binding(get: { viewModel.state.email }, set: { value in
                    viewModel.onEvent(event: RegisterEvent.EditEmail(newEmail: value))
                })
            )
            EditText(
                titleKey: StringRes.Companion().editPassword,
                secured: true,
                text: Binding(get: { viewModel.state.password }, set:{ value in
                    viewModel.onEvent(event: RegisterEvent.EditPassword(newPassword: value))
                })
            )
            RoundedButton(
                label: StringRes.Companion().register_,
                action: {
                viewModel.onEvent(event: RegisterEvent.Register())
            })
        }
        .simpleToast(isPresented: Binding(get: { viewModel.state.error != nil }, set: {_,_ in
            viewModel.onEvent(event: RegisterEvent.OnErrorSeen())
        }),options: toastOptions, content: {
            HStack {
                Text(viewModel.state.error?.message ?? "unknown error")
            }
            .padding(20)
            .background(Color.green)
            .foregroundColor(Color.white)
            .cornerRadius(16)
        })
        .onAppear {
            viewModel.observeState()
        }
        .padding(.all, 16)
        .onDisappear {
            viewModel.dispose()
        }
    }
}
