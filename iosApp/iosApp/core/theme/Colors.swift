//
//  Colors.swift
//  iosApp
//
//  Created by Mikhail Pankratov on 2023/02/02.
//  Copyright © 2023 orgName. All rights reserved.
//

import Foundation
import SwiftUI
import shared

extension Color {
    init(hex: Int64, alpha: Double = 1) {
            self.init(
                .sRGB,
                red: Double((hex >> 16) & 0xff) / 255,
                green: Double((hex >> 08) & 0xff) / 255,
                blue: Double((hex >> 00) & 0xff) / 255,
                opacity: alpha
            )
        }
    private static let color = Colors()
    static let lightBlue = Color(hex: color.LightBlue)
    static let darkGrey = Color(hex: color.DarkGrey)
    static let accentViolet = Color(hex: color.AccentViolet)
    static let lightBlueGrey = Color(hex: color.LightBlueGrey)
    static let textBlack = Color(hex: color.TextBlack)
    
    static let primary = Color(light: .accentViolet, dark: .accentViolet)
    static let background = Color(light: .lightBlueGrey, dark: .darkGrey)
    static let onPrimary = Color(light: .white, dark: .white)
    static let onBackground = Color(light: .textBlack, dark: .white)
    static let surface = Color(light: .white, dark: .darkGrey)
    static let onSurface = Color(light: .textBlack, dark: .white)
}

private extension Color {
    init(light: Self, dark: Self) {
        self.init(uiColor: UIColor(light: UIColor(light), dark: UIColor(dark)))
    }
}

private extension UIColor {
    convenience init(light: UIColor, dark: UIColor) {
        self.init { traits in
            switch traits.userInterfaceStyle {
            case .light, .unspecified :
                return light
            case .dark :
                return dark
            @unknown default:
                return light
            }
        }
    }
}
