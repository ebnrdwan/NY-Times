

/*===========================Core-Libraries==================================*/
object CoreLibraries {

    const val androidX = "androidx.appcompat:appcompat:${Versions.androidX}"
    const val material_design = "com.google.android.material:material:${Versions.material_design}"
    const val constraintlayout =
        "androidx.constraintlayout:constraintlayout:${Versions.constraintLayout}"
    const val lifecycle = "androidx.lifecycle:lifecycle-extensions:${Versions.lifecycle}"
    const val kotlin = "org.jetbrains.kotlin:kotlin-stdlib-jdk7:${Versions.kotlin}"
    const val ktx = "androidx.core:core-ktx:${Versions.ktx}"
    const val fragmentKtx = "androidx.fragment:fragment-ktx:${Versions.ktx}"
    const val leakCanary = "com.squareup.leakcanary:leakcanary-android:${Versions.canary}"
}



/*===========================Navigation-Libraries==================================*/

object NavigationLibraries {

    const val navigation_fragment =
        "androidx.navigation:navigation-fragment:${Versions.navigation}"
    const val navigation_UI = "androidx.navigation:navigation-ui:${Versions.navigation}"
    const val navigation_fragmentKtx =
        "androidx.navigation:navigation-fragment-ktx:${Versions.navigation}"
    const val navigation_UiKtx =
        "androidx.navigation:navigation-ui-ktx:${Versions.navigation}"
}

/*===========================UnitTest-Libraries==================================*/
object UnitTestLibraries {

    const val jUnit = "junit:junit:${Versions.jUnitVersion}"
    const val jUnitx = "androidx.test.ext:junit:${Versions.junitx}"
    const val mockitoCore = "org.mockito:mockito-core:${Versions.mockitoVersion}"
    const val mockWebserver = "com.squareup.okhttp3:mockwebserver:${Versions.mockServer}"
}


/*===========================AndroidTest-Libraries==================================*/
object InstrumentationTestLibraries {

    const val espressoCore = "androidx.test.espresso:espresso-core:${Versions.espresso}"
    const val espressoIntents = "androidx.test.espresso:espresso-intents:${Versions.espresso}"
    const val espressoIdling =
        "androidx.test.espresso:espresso-idling-resource:${Versions.espresso}"
    const val espressoContrib = "androidx.test.espresso:espresso-contrib:${Versions.espresso}"
    const val rules = "com.android.support.test:rules:${Versions.testRunnerVersion}"
    const val runners = "com.android.support.test:runner:${Versions.testRunnerVersion}"
    const val mockitoAndroid = "org.mockito:mockito-android:${Versions.mockitoVersion}"
    const val runnerClass = "androidx.test.runner.AndroidJUnitRunner"
}


/*===========================SharedTest-Libraries==================================*/
object SharedTestLibraries {

    const val fragment_test = "androidx.fragment:fragment-testing:${Versions.fragmentTest}"
    const val testingX = "androidx.arch.core:core-testing:${Versions.textX}"

}



/*===========================UI-Libraries==================================*/
object UiLibraries {

    const val dynamic_dimentions = "com.intuit.sdp:sdp-android:${Versions.sdp}"
    const val dynamic_font_size = "com.intuit.ssp:ssp-android:${Versions.sdp}"
    const val circularImage = "de.hdodenhof:circleimageview:${Versions.circularImage}"
    const val lottie = "com.airbnb.android:lottie:${Versions.lottie}"
    const val calligraphy = "uk.co.chrisjenx:calligraphy:${Versions.calligraphy}"
}


/*===========================Domain-Libraries==================================*/
object DomainLibraries {

    const val rxKotlin = "io.reactivex.rxjava2:rxkotlin:${Versions.rxKotlin}"
    const val rxAndroid = "io.reactivex.rxjava2:rxandroid:${Versions.rxAndroid}"
    const val coroutines = "org.jetbrains.kotlinx:kotlinx-coroutines-core:${Versions.coroutine}"
    const val coroutinesAndroid =
        "org.jetbrains.kotlinx:kotlinx-coroutines-android:${Versions.coroutine}"
    const val daggerAndroid = "com.google.dagger:dagger-android:${Versions.dagger}"
    const val dagger = "com.google.dagger:dagger:${Versions.dagger}"
    const val daggerAnnotation = "com.google.dagger:dagger-compiler:${Versions.dagger}"
    const val daggerProcessor = "com.google.dagger:dagger-android-processor:${Versions.dagger}"
    const val daggerSupport = "com.google.dagger:dagger-android-support:${Versions.dagger}"
    const val picasso = "com.squareup.picasso:picasso:${Versions.picasso}"
    const val glide = "com.github.bumptech.glide:glide:${Versions.glideVersion}"
    const val glideCompiler = "com.github.bumptech.glide:compiler:${Versions.glideVersion}"
    const val timber = "com.jakewharton.timber:timber:${Versions.timber}"
    const val retrofit = "com.squareup.retrofit2:retrofit:${Versions.retrofit}"
    const val retrofitRx = "com.squareup.retrofit2:adapter-rxjava2:${Versions.retrofit}"
    const val retrofitJson = "com.squareup.retrofit2:converter-gson:${Versions.retrofit}"
    const val retrofitLogger = "com.github.ihsanbal:LoggingInterceptor:${Versions.retrofitLogger}"
    const val koinViewModel = "org.koin:koin-android-viewmodel:${Versions.koin}"
    const val koin = "org.koin:koin-android:${Versions.koin}"
    const val chromTabs = "com.android.support:customtabs:${Versions.chromTabs}"
    const val paging = "androidx.paging:paging-runtime-ktx:${Versions.paging}"
}

