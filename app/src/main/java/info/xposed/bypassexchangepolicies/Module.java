package info.xposed.bypassexchangepolicies;

import de.robv.android.xposed.IXposedHookLoadPackage;
import de.robv.android.xposed.callbacks.XC_LoadPackage;
import info.xposed.bypassexchangepolicies.tweak.BypassGmailPolicy;
import info.xposed.bypassexchangepolicies.tweak.BypassOutlookPolicy;

public final class Module implements IXposedHookLoadPackage {

    public static final String PACKAGE_GOOGLE_GMAIL = "com.google.android.gm";
    public static final String PACKAGE_MICROSOFT_OUTLOOK = "com.microsoft.office.outlook";

    @Override
    public void handleLoadPackage(XC_LoadPackage.LoadPackageParam lpparam) throws Throwable {
        if (lpparam.packageName.equals(PACKAGE_GOOGLE_GMAIL)) {
            BypassGmailPolicy.initializeTweak(lpparam);
        } else if (lpparam.packageName.equals(PACKAGE_MICROSOFT_OUTLOOK)) {
            BypassOutlookPolicy.initializeTweak(lpparam);
        }
    }

}
