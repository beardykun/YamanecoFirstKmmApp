//
//  CustomButton.swift
//  iosApp
//
//  Created by Mikhail Pankratov on 2023/01/30.
//  Copyright © 2023 orgName. All rights reserved.
//

import SwiftUI

struct CustomButton: View {
    let label: String
    let action: () -> Void
    var body: some View {
        Button(label) {
            action()
        }
        .padding(.horizontal, 20)
        .padding(.vertical, 10)
        .background(Color.lightBlue)
        .clipShape(RoundedRectangle(cornerRadius: 20))
    }
}

struct CustomButton_Previews: PreviewProvider {
    static var previews: some View {
        CustomButton(label: "button lable", action: {})
    }
}
