//
//  HomeScreen.swift
//  iosApp
//
//  Created by Mikhail Pankratov on 2023/01/30.
//  Copyright © 2023 orgName. All rights reserved.
//

import SwiftUI
import shared

struct HomeScreen: View {
    
    @ObservedObject var viewModel: IosHomeViewModel
    private let firebaseAuth: FirebaseAuthentication
    private let fireStore: FirebaseFireStore
    
    init(firebaseAuth: FirebaseAuthentication, fireStore: FirebaseFireStore) {
        self.firebaseAuth = firebaseAuth
        self.fireStore = fireStore
        self.viewModel = IosHomeViewModel(firebaseAuth: firebaseAuth, fireStore: fireStore)
    }
    
    var body: some View {
        VStack(alignment: HorizontalAlignment.center) {
            NavigationLink(destination: LoginScreen(firebaseAuth: firebaseAuth, fireStore: fireStore), isActive:
                            Binding(get: {viewModel.state.isLogOut}, set: { value in
            })
            ){
                    EmptyView()
                }.hidden()
                           
            Spacer()
            Text(viewModel.state.user.id)
            Spacer(minLength: 16)
            Text(viewModel.state.user.name)
            Spacer(minLength: 16)

            CustomButton(
                label: L.Companion().home.button.logout(),
                action: {
                viewModel.onEvent(event: HomeEvent.LogOut())
            })
            
            Spacer()
        }
        .navigationBarBackButtonHidden(true)
        .onDisappear {
            viewModel.dispose()
        }
    }
}
