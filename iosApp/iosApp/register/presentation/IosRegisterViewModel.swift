//
//  IosRegisterViewModel.swift
//  iosApp
//
//  Created by Mikhail Pankratov on 2023/01/30.
//  Copyright © 2023 orgName. All rights reserved.
//

import Foundation
import shared

extension RegisterScreen {
    @MainActor class IosRegisterViewModel: ObservableObject {
        private let registerUserUseCase: RegisterUserUseCase
        private let viewModel: RegisterViewModel
        @Published var state = RegisterState(
            name: "",
            email: "",
            password: "",
            isLoading: false,
            error: nil,
            user: User(id: "", name: "", createdAt: 0),
            isRegistered: false
        )
        private var handle: DisposableHandle?

        
        init(firebaseAuthentication: FirebaseAuthentication, fireStore: FirebaseFireStore) {
            registerUserUseCase = RegisterUserUseCase(firebaseAuthentication: firebaseAuthentication, fireStore: fireStore)
            viewModel = RegisterViewModel(registerUserUseCase: registerUserUseCase, coroutineScope: nil)
            
            handle = viewModel.state.subscribe(onCollect: { state in
                if let state = state {
                    self.state = state
                    print("register new state " + state.name)
                }
            })
        }
        
        func onEvent(event: RegisterEvent) {
            viewModel.onEvent(event: event)
        }
        
        func dispose() {
            handle?.dispose()
        }
    }
}
