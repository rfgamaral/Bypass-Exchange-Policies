package info.xposed.bypassexchangepolicies.tweak;

import android.util.Log;

import de.robv.android.xposed.XC_MethodHook;
import de.robv.android.xposed.callbacks.XC_LoadPackage;

import static de.robv.android.xposed.XposedHelpers.findAndHookMethod;

public final class BypassOutlookPolicy {

    private static final String OUTLOOK_DEVICE_POLICY_CLASS = "com.acompli.accore.util.OutlookDevicePolicy";
    private static final String REQUIRES_DEVICE_MANAGEMENT_METHOD = "requiresDeviceManagement";
    private static final String IS_POLICY_APPLIED_METHOD = "isPolicyApplied";

    public static void initializeTweak(XC_LoadPackage.LoadPackageParam lpparam) {
        findAndHookMethod(
            OUTLOOK_DEVICE_POLICY_CLASS,
            lpparam.classLoader,
            REQUIRES_DEVICE_MANAGEMENT_METHOD,
            new XC_MethodHook() {

                @Override
                protected void afterHookedMethod(MethodHookParam param) throws Throwable {
                    param.setResult(false);
                }

            }
        );

        findAndHookMethod(
            OUTLOOK_DEVICE_POLICY_CLASS,
            lpparam.classLoader,
            IS_POLICY_APPLIED_METHOD,
            new XC_MethodHook() {

                @Override
                protected void afterHookedMethod(MethodHookParam param) throws Throwable {
                    param.setResult(true);
                }

            }
        );
    }

}
