//
//  RegisterScreen.swift
//  iosApp
//
//  Created by Mikhail Pankratov on 2023/01/30.
//  Copyright © 2023 orgName. All rights reserved.
//

import SwiftUI
import shared

struct RegisterScreen: View {
    
    @ObservedObject var viewModel: IosRegisterViewModel
    private let firebaseAuth: FirebaseAuthentication
    private let fireStore: FirebaseFireStore
    
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
                    Binding(get: {viewModel.state.isRegistered}, set: {value in
                        
                    })
            ) {
                EmptyView()
            }.hidden()
            
            CustomTextField(
                titleKey: "Name",
                secured: false,
                text: Binding(get: {viewModel.state.name}, set: { value in
                    print(value)
                    viewModel.onEvent(event: RegisterEvent.EditName(newName: value))
                })
            )
            CustomTextField(
                titleKey: "Email",
                secured: false,
                text: Binding(get: {viewModel.state.email}, set: { value in
                    viewModel.onEvent(event: RegisterEvent.EditEmail(newEmail: value))
                })
            )
            CustomTextField(
                titleKey: "Password",
                secured: true,
                text: Binding(get: {viewModel.state.password}, set:{ value in
                    viewModel.onEvent(event: RegisterEvent.EditPassword(newPassword: value))
                                })
            )
            CustomButton(
                lable: "Register",
                action: {
                viewModel.onEvent(event: RegisterEvent.Register())
            })
        }
        .padding(.all, 16)
        .onDisappear {
            viewModel.dispose()
        }
    }
}
