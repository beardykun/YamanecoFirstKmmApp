//
//  CustomButton.swift
//  iosApp
//
//  Created by Mikhail Pankratov on 2023/01/30.
//  Copyright © 2023 orgName. All rights reserved.
//

import SwiftUI

struct CustomButton: View {
    let lable: String
    let action: () -> Void
    var body: some View {
        Button(lable) {
            action()
        }
        .padding(.horizontal, 20)
        .padding(.vertical, 10)
        .background(Color.blue)
        .clipShape(RoundedRectangle(cornerRadius: 20))
    }
}

struct CustomButton_Previews: PreviewProvider {
    static var previews: some View {
        CustomButton(lable: "button lable", action: {})
    }
}
