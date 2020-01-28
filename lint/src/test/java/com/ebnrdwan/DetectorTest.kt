package com.ebnrdwan

import com.android.tools.lint.checks.infrastructure.TestLintTask.lint
import com.ebnrdwan.IssueRegistry.Companion.BadConfigurationProviderIssue
import com.ebnrdwan.IssueRegistry.Companion.NoisyIssue
import com.ebnrdwan.IssueRegistry.Companion.RemoveWorkManagerIntializerIssue
import com.ebnrdwan.Stubs.ANDROID_APP_CLASS
import com.ebnrdwan.Stubs.ANDROID_LOG_JAVA
import com.ebnrdwan.Stubs.APP_IMPLEMENTS_CONFIGURATION_PROVIDER
import com.ebnrdwan.Stubs.EMPTY_MANIFEST
import com.ebnrdwan.Stubs.EXPERIMENTAL_KT
import com.ebnrdwan.Stubs.LOG_WTF_KT
import com.ebnrdwan.Stubs.MANIFEST_WITH_INITIALIZER
import com.ebnrdwan.Stubs.MANIFEST_WITH_NO_INITIALIZER
import com.ebnrdwan.Stubs.OTHER_CLASS_IMPLEMENTS_CONFIGURATION_PROVIDER
import com.ebnrdwan.Stubs.TIME_TRAVEL_EXPERIMENT_KT
import com.ebnrdwan.Stubs.TIME_TRAVEL_PROVIDER_KT
import com.ebnrdwan.Stubs.USE_TIME_TRAVEL_EXPERIMENT_FROM_JAVA
import com.ebnrdwan.Stubs.WORK_MANAGER_CONFIGURATION_INTERFACE
import org.junit.Test

class DetectorTest {
    @Test
    fun testNoisyDetector() {
        lint().files(EMPTY_MANIFEST)
                .allowMissingSdk()
                .issues(NoisyIssue)
                .run()
                .expect(
                        """
                        AndroidManifest.xml: Information: This is a noisy issue. Feel free to ignore for now. [NoisyIssueId]
                        0 errors, 0 warnings
                        """.trimIndent()
                )
    }

    @Test
    fun testBadConfigurationProviderDetector_success() {
        lint().files(
                WORK_MANAGER_CONFIGURATION_INTERFACE,
                ANDROID_APP_CLASS,
                APP_IMPLEMENTS_CONFIGURATION_PROVIDER)
                .allowMissingSdk()
                .issues(BadConfigurationProviderIssue)
                .run()
                .expect("No warnings.")
    }

    @Test
    fun testBadConfigurationProviderDetector_failure() {
        lint().files(
                WORK_MANAGER_CONFIGURATION_INTERFACE,
                ANDROID_APP_CLASS,
                OTHER_CLASS_IMPLEMENTS_CONFIGURATION_PROVIDER)
                .allowMissingSdk()
                .issues(BadConfigurationProviderIssue)
                .run()
                .expect(
                        """
                        project0: Error: Only an android.app.Application can implement androidx.work.Configuration.Provider [BadConfigurationProviderId]
                        1 errors, 0 warnings
                        """.trimIndent()
                )
    }

    @Test
    fun testRemoveWorkManagerInitializerDetector_success() {
        lint().files(
                WORK_MANAGER_CONFIGURATION_INTERFACE,
                ANDROID_APP_CLASS,
                MANIFEST_WITH_NO_INITIALIZER,
                APP_IMPLEMENTS_CONFIGURATION_PROVIDER)
                .allowMissingSdk()
                .issues(RemoveWorkManagerIntializerIssue)
                .run()
                .expect("No warnings.")
    }

    @Test
    fun testRemoveWorkManagerInitializerDetector_failure_emptyManifest() {
        lint().files(
                EMPTY_MANIFEST,
                WORK_MANAGER_CONFIGURATION_INTERFACE,
                ANDROID_APP_CLASS,
                APP_IMPLEMENTS_CONFIGURATION_PROVIDER)
                .allowMissingSdk()
                .issues(RemoveWorkManagerIntializerIssue)
                .run()
                .expect(
                        """
                        AndroidManifest.xml:4: Error: If an android.app.Application implements androidx.work.Configuration.Provider, 
                        the default androidx.work.impl.WorkManagerInitializer needs to be removed from tne
                        AndroidManifest.xml file. [RemoveWorkManagerIntializerId]
                            <application>
                            ^
                        1 errors, 0 warnings
                        """.trimIndent()
                )
    }

    @Test
    fun testRemoveWorkManagerInitializerDetector_failure_manifestWithInitializer() {
        lint().files(
                MANIFEST_WITH_INITIALIZER,
                WORK_MANAGER_CONFIGURATION_INTERFACE,
                ANDROID_APP_CLASS,
                APP_IMPLEMENTS_CONFIGURATION_PROVIDER)
                .allowMissingSdk()
                .issues(RemoveWorkManagerIntializerIssue)
                .run()
                .expect(
                        """
                        AndroidManifest.xml:5: Error: If an android.app.Application implements androidx.work.Configuration.Provider, 
                        the default androidx.work.impl.WorkManagerInitializer needs to be removed from tne
                        AndroidManifest.xml file. [RemoveWorkManagerIntializerId]
                                 <provider
                                 ^
                        1 errors, 0 warnings
                      """.trimIndent()
                )
    }

    @Test
    fun testExperimentalDetector() {
        val input = arrayOf(
                EXPERIMENTAL_KT,
                TIME_TRAVEL_EXPERIMENT_KT,
                TIME_TRAVEL_PROVIDER_KT,
                USE_TIME_TRAVEL_EXPERIMENT_FROM_JAVA
        )

        /* ktlint-disable max-line-length */
        val expected = """
src/com/ebnrdwan/app/UseTimeTravelExperimentFromJava.java:18: Error: This declaration is experimental and its usage should be marked with
'@com.ebnrdwan.app.TimeTravelExperiment' or '@UseExperimental(markerClass = com.ebnrdwan.app.TimeTravelExperiment.class)' [UnsafeExperimentalUsageError]
        new TimeTravelProvider().setTime(-1);
        ~~~~~~~~~~~~~~~~~~~~~~~~
src/com/ebnrdwan/app/UseTimeTravelExperimentFromJava.java:18: Error: This declaration is experimental and its usage should be marked with
'@com.ebnrdwan.app.TimeTravelExperiment' or '@UseExperimental(markerClass = com.ebnrdwan.app.TimeTravelExperiment.class)' [UnsafeExperimentalUsageError]
        new TimeTravelProvider().setTime(-1);
                                 ~~~~~~~
2 errors, 0 warnings
        """
        /* ktlint-enable max-line-length */

        lint().files(*input)
                .allowMissingSdk()
                .issues(ExperimentalDetector.ISSUE)
                .run()
                .expect(expected.trimIndent())
    }

    @Test
    fun testLogWtfDetector() {
        /* ktlint-disable max-line-length */
        val expected = """
src/com/ebnrdwan/app/WhatATerribleFailure.kt:7: Error: Usage of Log.wtf() is prohibited [LogWtfUsageError]
        Log.wtf(clazz.name, message)
            ~~~~~~~~~~~~~~~~~~~~~~~~
1 errors, 0 warnings
            """.trimIndent()
        /* ktlint-enable max-line-length */

        lint().files(
                ANDROID_LOG_JAVA,
                LOG_WTF_KT)
                .allowMissingSdk() // The one SDK class that we need has been added manually!
                .issues(LogWtfDetector.ISSUE)
                .run()
                .expect(expected.trimIndent())
    }
}
