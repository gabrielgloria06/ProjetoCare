    plugins {
        id("com.android.application")
    }

    android {
        namespace = "com.example.myapplication"
        compileSdk = 34

        defaultConfig {
            applicationId = "com.example.myapplication"
            minSdk = 24
            targetSdk = 34
            versionCode = 1
            versionName = "1.0"

            testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
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

        buildFeatures{
            viewBinding = true
        }
        compileOptions {
            sourceCompatibility = JavaVersion.VERSION_1_8
            targetCompatibility = JavaVersion.VERSION_1_8
        }
    }

        dependencies {
        implementation("androidx.appcompat:appcompat:1.7.0")
        implementation("com.google.android.material:material:1.12.0")
        testImplementation("junit:junit:4.13.2")
        androidTestImplementation("androidx.test.ext:junit:1.2.1")
        androidTestImplementation("androidx.test.espresso:espresso-core:3.6.1")
        implementation("com.squareup.retrofit2:retrofit:2.9.0")
        implementation("com.squareup.retrofit2:converter-gson:2.9.0")
        implementation("io.jsonwebtoken:jjwt-api:0.11.2")
        implementation("androidx.core:core:1.13.1")
        implementation ("org.json:json:20210307")
            implementation ("androidx.work:work-runtime:2.8.1")

            implementation("io.jsonwebtoken:jjwt-impl:0.11.2")
        implementation("io.jsonwebtoken:jjwt-jackson:0.11.2")
        implementation("com.squareup.okhttp3:okhttp:4.9.3")
        implementation ("com.fasterxml.jackson.core:jackson-core:2.15.0")
        implementation( "com.fasterxml.jackson.core:jackson-databind:2.15.0")
        implementation ("com.fasterxml.jackson.dataformat:jackson-dataformat-xml:2.15.0")
        implementation ("javax.xml.stream:stax-api:1.0-2")
        implementation ("org.jsoup:jsoup:1.15.3")
            implementation ("com.google.android.gms:play-services-maps:19.0.0")
            implementation ("com.google.android.gms:play-services-location:21.3.0")
            implementation( "com.google.android.libraries.places:places:4.0.0")
            implementation("com.google.maps:google-maps-services:2.0.0")
            implementation("androidx.appcompat:appcompat:1.7.0")
            implementation("com.google.android.material:material:1.12.0")
            implementation("androidx.recyclerview:recyclerview:1.3.2")
            testImplementation("junit:junit:4.13.2")
            implementation( "com.squareup.okhttp3:okhttp:4.9.3")
            implementation ("com.google.code.gson:gson:2.10.1")
            androidTestImplementation("androidx.test.ext:junit:1.2.1")
            androidTestImplementation("androidx.test.espresso:espresso-core:3.6.1")
            implementation ("com.squareup.retrofit2:retrofit:2.9.0")
            implementation ("com.squareup.retrofit2:converter-gson:2.9.0")
            implementation ("com.google.code.gson:gson:2.10.1")


    }
