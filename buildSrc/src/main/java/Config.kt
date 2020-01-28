/*
 * App configuration
 */
object Config {
    const val applicationId = "com.ebnrdwan.task"
    const val rawPackageName = "com.ebnrdwan.task"
    const val minSdkVersion = Versions.minSdkVersion
    const val targetSdkVersion = Versions.targetSdkVersion
    const val compileSdkVersion = Versions.compileSdkVersion
    const val testInstrumentationRunner = "android.support.test.runner.AndroidJUnitRunner"
    const val versionCode = 1
    const val versionName = "1.0.0"
}

/*
 * Auto generated buildConfig fileds
 */
object Fields {
    const val rootUrl = "ROOT_URL"
    const val pName = "PACKAGE_NAME"

}

/*
 * Flavor Dimensions
 */
object Dimensions {
    const val version = "version"
}

/*
 * Product Flavors
 */

object Dev {
    const val suffix = ".development"
    const val versionNameSuffix = suffix
    const val applicationIdSuffix = suffix
}