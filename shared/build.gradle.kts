plugins {
    kotlin("multiplatform")
    kotlin("native.cocoapods")
    id("com.android.library")
    kotlin("plugin.serialization") version Deps.kotlinVersion
    id ("dev.jamiecraane.plugins.kmmresources") version Deps.kmmResourcesVersion
}

kotlin {
    android()

    listOf(
        iosX64(),
        iosArm64(),
        iosSimulatorArm64()
    ).forEach {
        it.binaries.framework {
            baseName = "shared"
            isStatic = true // TODO setting this true solves this err --> ld: framework not found FirebaseAuth
        }
    }

    cocoapods {
        summary = "Some description for the Shared Module"
        homepage = "Link to the Shared Module homepage"
        version = "1.0"
        ios.deploymentTarget = "14.1"
        podfile = project.file("../iosApp/Podfile")
        framework {
            isStatic = true
            baseName = "shared"
        }
    }

    sourceSets {
        val commonMain by getting {
            dependencies {
                implementation(Deps.ktorCore)
                implementation(Deps.ktorSerialization)
                implementation(Deps.ktorSerializationJson)
                implementation(Deps.sqlDelightRuntime)
                implementation(Deps.sqlDelightCoroutinesExtensions)
                implementation(Deps.kotlinDateTime)
                implementation("dev.gitlive:firebase-auth:1.6.2")
                implementation("dev.gitlive:firebase-firestore:1.6.2")
            }
        }
        val commonTest by getting {
            dependencies {
                implementation(kotlin("test"))
            }
        }
        val androidMain by getting
        val androidTest by getting
        val iosX64Main by getting
        val iosArm64Main by getting
        val iosSimulatorArm64Main by getting
        val iosMain by creating {
            dependsOn(commonMain)
            iosX64Main.dependsOn(this)
            iosArm64Main.dependsOn(this)
            iosSimulatorArm64Main.dependsOn(this)
        }
        val iosX64Test by getting
        val iosArm64Test by getting
        val iosSimulatorArm64Test by getting
        val iosTest by creating {
            dependsOn(commonTest)
            iosX64Test.dependsOn(this)
            iosArm64Test.dependsOn(this)
            iosSimulatorArm64Test.dependsOn(this)
        }
    }
}


kmmResourcesConfig {
    androidApplicationId.set("com.example.yamanecofirstkmmapp")
    packageName.set("com.example.yamanecofirstkmmapp.shared.localization")
    defaultLanguage.set("en")
    input.set(File(project.projectDir.path, "generic.yaml"))
    output.set(project.projectDir)
    //srcFolder.set("src") // Optional, defaults to 'src'
    //generatedClassName.set("KMMResourcesLocalization.kt") // Optional, defaults to 'KMMResourcesLocalization.kt'
    //androidStringsPrefix.set("_generated") // Optional, defaults to '_generated'
    //androidSourceFolder.set("main") // The location of the android sources in the shared module (Optional, defaults to androidMain)
    useDefaultTranslationIfNotInitialized.set(true) // When true, outputs the texts of the default language in the Android generated actual declarations instead of an empty String
}
val plutil = tasks["executePlutil"] // This one is only needed for iOS

val generateLocalizations = tasks["generateLocalizations"]
tasks

val packForXcode by tasks.creating(Sync::class) {
    group = "build"
    val mode = System.getenv("CONFIGURATION") ?: "DEBUG"
    val sdkName = System.getenv("SDK_NAME") ?: "iphonesimulator"
    val targetName = "ios" + if (sdkName.startsWith("iphoneos")) "Arm64" else "X64"
    val framework = kotlin.targets.getByName<org.jetbrains.kotlin.gradle.plugin.mpp.KotlinNativeTarget>(targetName).binaries.getFramework(mode)
    inputs.property("mode", mode)
    dependsOn(framework.linkTask)
    val targetDir = File(buildDir, "xcode-frameworks")//findProperty("configuration.build.dir")
    println("TAGGER targetDir = $targetDir")
    println("TAGGER ${project.rootDir}/shared/src/commonMain/resources/ios")
    if (targetDir == null) {
        System.err.println("configuration.build.dir is not defined. Please pass this property from the XCode build.")
    }
    from({ framework.outputDirectory })
    into(targetDir)

//    This is added to the packForXCode task. commonMain/resources/ios is the location of the generated Localizable.strings files.
    doLast {
        copy {
            from("${project.rootDir}/src/commonMain/resources/ios")
            into("${targetDir}/shared.framework")
        }
    }
}

tasks {
/*
     * This sets up dependencies between the plutil task and compileKotlinIos* tasks. This
     * way common is recompiled if something in generic.yaml changes (so new ios resources
     * are generated). If the generic.yaml file is not changed, the resources are considered
     * up to date by Gradle.

 */
    named("compileKotlinIosX64") {
        dependsOn(plutil)
    }
    named("preBuild") {
        dependsOn(generateLocalizations)
    }
    named("linkPodReleaseFrameworkIosX64").configure {
        dependsOn(generateLocalizations)
    }
}

android {
    namespace = "com.example.yamanecofirstkmmapp"
    compileSdk = 33
    defaultConfig {
        minSdk = 24
        targetSdk = 33
    }
}