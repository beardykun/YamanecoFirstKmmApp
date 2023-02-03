//
//  iOSLoginViewModel.swift
//  iosApp
//
//  Created by Mikhail Pankratov on 2023/01/26.
//  Copyright © 2023 orgName. All rights reserved.
//

import Foundation
import shared

extension LoginScreen {
    @MainActor class iOSLoginViewModel: ObservableObject {
        
        private let viewModel: LoginViewModel
        @Published var state = LoginState(email: "", password: "", isLoading: false, userId: nil, error: nil)
        private let loginUseCase: LoginUserUseCase
        private var handle: DisposableHandle?
    
        
        init(firebaseAuth: FirebaseAuthentication) {
            loginUseCase = LoginUserUseCase(firebaseAuthentication: firebaseAuth)
            viewModel = LoginViewModel(registerUserUseCase: loginUseCase, coroutineScope: nil)
        }
        
        func observeState() {
            handle = viewModel.state.subscribe(onCollect: { state in
                if let state = state {
                    self.state = state
                }
            })
        }
        
        func onEvent(event: LoginEvent) {
            viewModel.onEvent(event: event)
        }
        
        func dispose() {
            handle?.dispose()
        }
    }
}
