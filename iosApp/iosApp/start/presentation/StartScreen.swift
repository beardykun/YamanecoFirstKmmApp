//
//  StartScreen.swift
//  iosApp
//
//  Created by Mikhail Pankratov on 2023/01/30.
//  Copyright © 2023 orgName. All rights reserved.
//

import SwiftUI
import shared

struct StartScreen: View {
    
    @ObservedObject var viewModel: IosStartViewModel
    private let firebaseAuth: FirebaseAuthentication
    private let fireStore: FirebaseFireStore
    
    init(firebaseAuth: FirebaseAuthentication, fireStore:  FirebaseFireStore) {
        self.firebaseAuth = firebaseAuth
        self.fireStore = fireStore
        viewModel = IosStartViewModel(firebaseAuth: firebaseAuth)
    }
    
    var body: some View {
        ZStack(alignment: Alignment.center) {
            NavigationLink(destination: LoginScreen(firebaseAuth: firebaseAuth, fireStore: fireStore), isActive: $viewModel.navigateToLogin) {
                    EmptyView()
                }.hidden()
            NavigationLink(destination: HomeScreen(firebaseAuth: firebaseAuth, fireStore: fireStore), isActive: $viewModel.navigateToHome) {
                    EmptyView()
                }.hidden()
        }.onAppear(perform: {
            viewModel.observeState()
            viewModel.redirectToNextScreen()
        })
        .onDisappear {
            viewModel.dispose()
        }
    }
}
