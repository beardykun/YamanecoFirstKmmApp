//
//  IosHomeViewModel.swift
//  iosApp
//
//  Created by Mikhail Pankratov on 2023/01/30.
//  Copyright © 2023 orgName. All rights reserved.
//

import Foundation
import shared

extension HomeScreen {
    @MainActor class IosHomeViewModel:  ObservableObject {
        
        private let getUserUseCase: GetUserUseCase
        private let logoutUserUseCase: LogoutUserUseCase
        private let viewModel: HomeViewModel
        @Published var state = HomeState(user: User(id: "", name: "", createdAt: 0), isLogOut: false, error: nil)
        private var handle: DisposableHandle?
        @Published var isLogout = false
        
        init(firebaseAuth: FirebaseAuthentication, fireStore: FirebaseFireStore) {
            self.getUserUseCase = GetUserUseCase(fireStore: fireStore, firebaseAuthentication: firebaseAuth)
            self.logoutUserUseCase = LogoutUserUseCase(firebaseAuthentication: firebaseAuth)
            self.viewModel = HomeViewModel(getUserUseCase: getUserUseCase, logoutUserUseCase: logoutUserUseCase, coroutineScope: nil)
        }
        
        func observeState() {
            handle = viewModel.state.subscribe(onCollect: { state in
                if let state = state {
                    self.state = state
                    self.isLogout = state.isLogOut
                }
            })
        }
        
        func onEvent(event: HomeEvent) {
            viewModel.onEvent(event: event)
        }
        
        func dispose() {
            handle?.dispose()
        }
    }
}
