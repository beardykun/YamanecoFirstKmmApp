//
//  IosResetPasswordViewModel.swift
//  iosApp
//
//  Created by Mikhail Pankratov on 2023/02/02.
//  Copyright © 2023 orgName. All rights reserved.
//

import Foundation
import shared

extension ResetPasswordScreen {
    @MainActor class IosResetPasswordViewModel: ObservableObject {
        
        private let resetPasswordUseCase: ResetPasswordUseCase
        private let viewModel: ResetPasswordViewModel
        @Published var state = ResetPasswordState(email: "", navigateToHome: false, error: nil)
        private var handle: DisposableHandle?
        
        init(firebaseAuthentication: FirebaseAuthentication) {
            resetPasswordUseCase = ResetPasswordUseCase(firebaseAuthentication: firebaseAuthentication)
            viewModel = ResetPasswordViewModel(resetPasswordUseCase: resetPasswordUseCase, coroutineScope: nil)
            
            
        }
        
        func observeState() {
            handle = viewModel.state.subscribe(onCollect: { state in
                if let state = state {
                    self.state = state
                }
            })
        }
        
        func onEvent(event: ResetPasswordEvent) {
            viewModel.onEvent(event: event)
        }
        
        func dispose() {
            handle?.dispose()
        }
    }
}
