plugins {
    id("com.android.application")
}

android {
    namespace = "com.example.wanderwege_myapplication"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.example.wanderwege_myapplication"
        minSdk = 26
        targetSdk = 32
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"

        javaCompileOptions{
            annotationProcessorOptions {
                arguments["room.schemaLocation"] = "$projectDir/schemas".toString()
            }
        }
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
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    buildFeatures {
        viewBinding = true
    }
}

dependencies {
    implementation("org.osmdroid:osmdroid-android:6.1.17")
    implementation("org.osmdroid:osmdroid-wms:6.1.17")
    implementation("org.osmdroid:osmdroid-mapsforge:6.1.17")
    implementation("androidx.appcompat:appcompat:1.6.1")

    implementation("com.google.android.material:material:1.11.0")
    implementation("androidx.constraintlayout:constraintlayout:2.1.4")
    implementation("androidx.navigation:navigation-fragment:2.7.6")
    implementation("androidx.navigation:navigation-ui:2.7.6")
    implementation("com.google.ar.sceneform:filament-android:1.17.1")
    implementation("com.google.firebase:firebase-database:20.3.0")
    // implementation("com.google.firebase:firebase-firestore:24.10.0")
    implementation("com.google.android.engage:engage-core:1.3.1")
    implementation("androidx.room:room-common:2.6.1")
    implementation("androidx.room:room-runtime:2.6.1")
    implementation("androidx.test.espresso:espresso-contrib:3.5.1")
    implementation("androidx.test:rules:1.5.0")
    testImplementation("junit:junit:4.13.2")
    testImplementation("org.junit.jupiter:junit-jupiter:5.8.1")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    annotationProcessor("androidx.room:room-compiler:2.6.1")
    implementation("androidx.coordinatorlayout:coordinatorlayout:1.2.0")
    testImplementation("junit:junit:4.13.2")


    // Espresso
    androidTestImplementation("androidx.test:runner:1.5.2")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")
    androidTestImplementation("org.mockito:mockito-core:4.2.0")
    androidTestImplementation("org.mockito:mockito-android:3.11.2")


    //Mockito
    testImplementation("org.mockito:mockito-core:4.2.0")
    // Optional -- mockito-kotlin
    testImplementation("org.mockito.kotlin:mockito-kotlin:5.2.1")
    // Optional -- Mockk framework
    testImplementation("io.mockk:mockk:1.13.9")

}