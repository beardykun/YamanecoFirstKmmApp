//
//  IosStartViewModel.swift
//  iosApp
//
//  Created by Mikhail Pankratov on 2023/01/30.
//  Copyright © 2023 orgName. All rights reserved.
//

import Foundation
import shared

extension StartScreen {
    @MainActor class IosStartViewModel: ObservableObject {
        private let viewModel: StartViewModel
        @Published var state = StartState(currentUser: nil, status: Status.none)
        private let startAppUseCase : StartAppUseCase
        private var handle: DisposableHandle?
        @Published var navigateToLogin = false
        @Published var navigateToHome = false
        
        init(firebaseAuth: FirebaseAuthentication) {
            self.startAppUseCase = StartAppUseCase(firebaseAuthentication: firebaseAuth)
            self.viewModel = StartViewModel(startAppUseCase: startAppUseCase, coroutineScope: nil)
            
            self.handle = viewModel.state.subscribe(onCollect: {state in
                if let state = state {
                    self.state = state
                    self.navigate(state: state)
                }
            })
        }
        
        private func navigate(state: StartState) {
            print(state.status.name)
            switch state.status {
            case Status.notLoggedIn: self.navigateToLogin = true
            case Status.loggedIn: self.navigateToHome = true
            default: do {
                self.navigateToLogin = false
                self.navigateToHome = false
            }
            }
        }
        
        func redirectToNextScreen() {
            viewModel.redirectToCorrectScreen()
        }
                
        func dispose() {
            handle?.dispose()
        }
    }
}
