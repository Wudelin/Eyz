@file:SuppressWarnings("unused")

package com.wdl.versionplugin


object Version {
    const val composeVersion = "1.1.0-beta04"
    const val coreKtxVersion = "1.6.0"
    const val kotlinVersion = "1.6.0"
    const val appcompatVersion = "1.3.1"
    const val materialVersion = "1.3.0"
    const val junitExtVersion = "1.1.2"
    const val espressoVersion = "3.3.0"
    const val lifecycleVersion = "2.3.1"
    const val aRouterCompileVersion = "1.5.2"
    const val aRouterVersion = "1.2.2"
    const val hiltVersion = "2.37"
    const val hiltCompilerVersion = "1.0.0"
    const val dokitxVersion = "3.5.0"
    const val immersionBarVersion = "3.0.0"
    const val mmkvVersion = "1.2.10"
    const val okhttp3Version = "4.9.1"
    const val retrofitVersion = "2.9.0"
    const val swiperefreshlayoutVersion = "1.1.0"
    const val recyclerviewVersion = "1.1.0"
    const val constraintlayoutVersion = "2.0.4"
    const val fragmentVersion = "1.2.5"
    const val startupVersion = "1.1.0"
    const val roomVersion = "2.3.0"
}

object BuildVersion {
    const val compileSdk = 31
    const val minSdk = 21
    const val targetSdk = compileSdk
    const val versionCode = 1
    const val versionName = "1.0"
    const val jvmTarget = "1.8"
    const val applicationId = "com.wdl.ezz"
    const val gradleVersion = "7.0.0"
}

object Androidx {
    const val coreKtx = "androidx.core:core-ktx:${Version.coreKtxVersion}"
    const val appcompat = "androidx.appcompat:appcompat:${Version.appcompatVersion}"
    const val material = "com.google.android.material:material:${Version.materialVersion}"
    const val viewBinding = "androidx.databinding:viewbinding:${BuildVersion.gradleVersion}"
    const val swiperefreshlayout = "androidx.swiperefreshlayout:swiperefreshlayout:${Version.swiperefreshlayoutVersion}"
    const val recyclerview = "androidx.recyclerview:recyclerview:${Version.recyclerviewVersion}"
    const val constraintlayout = "androidx.constraintlayout:constraintlayout:${Version.constraintlayoutVersion}"
    const val fragment = "androidx.fragment:fragment-ktx:${Version.fragmentVersion}"
    const val lifecycleRuntime = "androidx.lifecycle:lifecycle-runtime-ktx:${Version.lifecycleVersion}"
    const val startupRuntime = "androidx.startup:startup-runtime:${Version.startupVersion}"
    const val lifecycleViewmodel = "androidx.lifecycle:lifecycle-viewmodel-ktx:${Version.lifecycleVersion}"
    const val lifecycleLivedata = "androidx.lifecycle:lifecycle-livedata-ktx:${Version.lifecycleVersion}"
    const val lifecycleCommonJava8 = "androidx.lifecycle:lifecycle-common-java8:${Version.lifecycleVersion}"
}

object Compose {
    const val ui = "androidx.compose.ui:ui:${Version.composeVersion}"
    const val activityCompose = "androidx.activity:activity-compose:${Version.composeVersion}"
    const val material = "androidx.compose.material:material:${Version.composeVersion}"
    const val toolingPreview = "androidx.compose.ui:ui-tooling-preview:${Version.composeVersion}"
    const val tooling = "androidx.compose.ui:ui-tooling:${Version.composeVersion}"
    const val runtime = "androidx.compose.runtime:runtime:${Version.composeVersion}"
}

object Hilt {
    const val hiltPlugin = "com.google.dagger:hilt-android-gradle-plugin:${Version.hiltVersion}"
    const val hiltAndroid = "com.google.dagger:hilt-android:${Version.hiltVersion}"
    const val hiltCompiler = "com.google.dagger:hilt-android-compiler:${Version.hiltVersion}"
    const val hiltAndroidXCompiler = "androidx.hilt:hilt-compiler:${Version.hiltCompilerVersion}"
}

object Test {
    const val junit = "junit:junit:4.+"
    const val junitExt = "androidx.test.ext:junit:${Version.junitExtVersion}"
    const val espressoCore = "androidx.test.espresso:espresso-core:${Version.espressoVersion}"
    const val composeUiTest = "androidx.compose.ui:ui-test-junit4:${Version.composeVersion}"
}

object Lifecycle {
    const val runtime = "androidx.lifecycle:lifecycle-runtime-ktx:${Version.lifecycleVersion}"
}

object ARouter {
    const val router = "com.alibaba:arouter-compiler:${Version.aRouterCompileVersion}"
    const val routerApi = "com.alibaba:arouter-api:${Version.aRouterVersion}"
}

object Dokitx {
    const val dokitx = "io.github.didi.dokit:dokitx:${Version.dokitxVersion}"
    const val dokitxNP = "io.github.didi.dokit:dokitx-no-op:${Version.dokitxVersion}"
}

object Immersionbar {
    // 基础依赖包，必须要依赖
    const val immersionbar = "com.gyf.immersionbar:immersionbar:${Version.immersionBarVersion}"

    // kotlin扩展（可选）
    const val immersionbarKTX = "com.gyf.immersionbar:immersionbar-ktx:${Version.immersionBarVersion}"

}

object MMKV{
    const val mmkv = "com.tencent:mmkv-static:${Version.mmkvVersion}"
}

object Net{
    const val okhttp3 = "com.squareup.okhttp3:okhttp:${Version.okhttp3Version}"
    const val loggingInterceptor = "com.squareup.okhttp3:logging-interceptor:${Version.okhttp3Version}"
    const val retrofit2 = "com.squareup.retrofit2:retrofit:${Version.retrofitVersion}"
    const val converterGson = "com.squareup.retrofit2:converter-gson:${Version.retrofitVersion}"
}

object Room{
    const val roomRuntime = "androidx.room:room-runtime:${Version.roomVersion}"
    const val roomCompiler = "androidx.room:room-compiler:${Version.roomVersion}"
    const val roomKtx = "androidx.room:room-ktx:${Version.roomVersion}"
}


