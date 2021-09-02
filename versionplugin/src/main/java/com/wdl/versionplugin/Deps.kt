@file:SuppressWarnings("unused")

package com.wdl.versionplugin


object Version {
    const val composeVersion = "1.0.1"
    const val coreKtxVersion = "1.6.0"
    const val appcompatVersion = "1.3.1"
    const val materialVersion = "1.3.0"
    const val junitExtVersion = "1.1.2"
    const val espressoVersion = "3.3.0"
    const val lifecycleVersion = "2.3.1"
}

object BuildVersion {
    const val compileSdk = 30
    const val minSdk = 21
    const val targetSdk = compileSdk
    const val versionCode = 1
    const val versionName = "1.0"
    const val jvmTarget = "1.8"
    const val applicationId = "com.wdl.ezz"
    const val gradleVersion = "7.0.2"
}

object Androidx {
    const val coreKtx = "androidx.core:core-ktx:${Version.coreKtxVersion}"
    const val appcompat = "androidx.appcompat:appcompat:${Version.appcompatVersion}"
    const val material = "com.google.android.material:material:${Version.materialVersion}"
    const val viewBinding = "androidx.databinding:viewbinding:${BuildVersion.gradleVersion}"
}

object Compose {
    const val ui = "androidx.compose.ui:ui:${Version.composeVersion}"
    const val activityCompose = "androidx.activity:activity-compose:${Version.composeVersion}"
    const val material = "androidx.compose.material:material:${Version.composeVersion}"
    const val toolingPreview = "androidx.compose.ui:ui-tooling-preview:${Version.composeVersion}"
    const val tooling = "androidx.compose.ui:ui-tooling:${Version.composeVersion}"
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