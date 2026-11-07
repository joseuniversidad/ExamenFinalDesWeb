plugins { id("com.android.application") }
android {
    namespace = "com.examen.finaldesweb"
    compileSdk { version = release(37) }
    defaultConfig {
        applicationId = "com.examen.finaldesweb"
        minSdk = 24
        targetSdk = 28
        versionCode = 1
        versionName = "1.0"
        testInstrumentationRunner = "android.test.InstrumentationTestRunner"
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
}
