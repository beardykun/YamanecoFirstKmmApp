import SwiftUI
import Firebase
import FirebaseFirestore
import shared

@main
struct iOSApp: App {
    
    private let firebaseAuth = FirebaseAuthentication()
    private let fireStore = FirebaseFireStore()
    
    init() {
            /*
             * Configure iOS Firebase
             * If you don't configure it, you will get the ff exception:
             * Terminating app due to uncaught exception 'NSInternalInconsistencyException', reason: 'The default
             * FirebaseApp instance must be configured before the default Authinstance can be initialized. One way
             * to ensure this is to call `FirebaseApp.configure()` in the App Delegate's
             * `application(_:didFinishLaunchingWithOptions:)` (or the `@main` struct's initializer in SwiftUI).'
             */
            FirebaseApp.configure()
            KMMResourcesLocalizationKt.localizationBundle = Bundle(for: L.self)
        }
    
	var body: some Scene {
		WindowGroup {
            NavigationView {
                StartScreen(firebaseAuth: firebaseAuth, fireStore: fireStore)
            }.accentColor(.black)
		}
	}
}
