plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.compose)
    `maven-publish`
}

android {
    namespace = "de.ixam97.carcompose"
    compileSdk = 36

    defaultConfig {
        minSdk = 29

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    kotlinOptions {
        jvmTarget = "11"
    }
}

dependencies {
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.material3)
    implementation(libs.github.scrollbar)
}

afterEvaluate {
    publishing {
        publications {
            register<MavenPublication>("release") {
                from(components["release"])
                groupId = "de.ixam97"
                artifactId = "carcompose"
                version = "0.1.0"

                pom {
                    name = "Car Compose"
                    description = "Framework based on Material 3 Compose to provide reusable UI components for car head units based on AAOS."
                    url = "https://github.com/ixam97/CarCompose"

                    licenses {
                        license {
                            name = "MIT License"
                            url = "https://mit-license.org/"
                        }
                    }

                    developers {
                        developer {
                            id = "ixam97"
                            name = "Maximilian Goldschmidt"
                            email = "ixam97@ixam97.de"
                        }
                    }
                }
            }
            repositories {
                maven { url = uri("https://jitpack.io") }
            }
        }
    }
}