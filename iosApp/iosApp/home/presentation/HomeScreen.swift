//
//  HomeScreen.swift
//  iosApp
//
//  Created by Mikhail Pankratov on 2023/01/30.
//  Copyright © 2023 orgName. All rights reserved.
//

import SwiftUI
import shared
import SimpleToast


struct HomeScreen: View {
    
    @ObservedObject var viewModel: IosHomeViewModel
    private let firebaseAuth: FirebaseAuthentication
    private let fireStore: FirebaseFireStore
    private let toastOptions = SimpleToastOptions(
        alignment: .bottom,
        hideAfter: 3,
        animation: .default,
        modifierType: .slide
    )
    
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

            RoundedButton(
                label: StringRes.Companion().logout,
                action: {
                viewModel.onEvent(event: HomeEvent.LogOut())
            })
            
            Spacer()
        }
        .simpleToast(isPresented: Binding(get: { viewModel.state.error != nil }, set: {_,_ in
            viewModel.onEvent(event: HomeEvent.OnErrorSeen())
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
        .navigationBarBackButtonHidden(true)
        .onDisappear {
            viewModel.dispose()
        }
    }
}
