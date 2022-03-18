package edu.muniz.askalien.admin.native


import com.oracle.svm.core.annotate.AutomaticFeature
import org.graalvm.nativeimage.ImageSingletons
import org.graalvm.nativeimage.hosted.Feature
import org.graalvm.nativeimage.hosted.Feature.BeforeAnalysisAccess
import org.graalvm.nativeimage.impl.RuntimeClassInitializationSupport
import java.util.*

@AutomaticFeature
class NativeLanguageFeature : Feature {
    override fun beforeAnalysis(access: BeforeAnalysisAccess) {
        ImageSingletons.lookup(RuntimeClassInitializationSupport::class.java).initializeAtBuildTime(
            SupportedLocales::class.java, "__"
        )
        val availableLocales = Locale.getAvailableLocales()
        val supportedLocales = SupportedLocales()
        supportedLocales.locales = availableLocales
        ImageSingletons.add(SupportedLocales::class.java, supportedLocales)
    }

    class SupportedLocales {
        lateinit var locales: Array<Locale>
    }
}