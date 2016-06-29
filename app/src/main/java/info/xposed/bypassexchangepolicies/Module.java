package info.xposed.bypassexchangepolicies;

import de.robv.android.xposed.IXposedHookLoadPackage;
import de.robv.android.xposed.callbacks.XC_LoadPackage;
import info.xposed.bypassexchangepolicies.tweak.BypassGmailPolicy;

public final class Module implements IXposedHookLoadPackage {

    public static final String PACKAGE_GOOGLE_GMAIL = "com.google.android.gm";

    @Override
    public void handleLoadPackage(XC_LoadPackage.LoadPackageParam lpparam) throws Throwable {
        if (lpparam.packageName.equals(PACKAGE_GOOGLE_GMAIL)) {
            BypassGmailPolicy.initializeTweak(lpparam);
        }
    }

}
