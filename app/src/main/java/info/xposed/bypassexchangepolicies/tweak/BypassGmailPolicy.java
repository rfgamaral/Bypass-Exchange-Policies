package info.xposed.bypassexchangepolicies.tweak;

import android.app.AlertDialog;
import android.os.Bundle;
import android.view.View;

import de.robv.android.xposed.XC_MethodHook;
import de.robv.android.xposed.XposedBridge;
import de.robv.android.xposed.callbacks.XC_LoadPackage;

import static de.robv.android.xposed.XposedHelpers.findAndHookMethod;

public final class BypassGmailPolicy {

    private static final String SECURITY_REQUIRED_DIALOG_CLASS = "awh";
    private static final String ON_CREATE_DIALOG_METHOD = "onCreateDialog";

    public static void initializeTweak(final XC_LoadPackage.LoadPackageParam lpparam) {
        findAndHookMethod(
            SECURITY_REQUIRED_DIALOG_CLASS,
            lpparam.classLoader,
            ON_CREATE_DIALOG_METHOD,
            Bundle.class,
            new XC_MethodHook() {

                @Override
                protected void afterHookedMethod(MethodHookParam param) throws Throwable {
                    try {
                        AlertDialog alertDialog = (AlertDialog) param.getResult();

                        alertDialog.create();

                        alertDialog.findViewById(
                            alertDialog.getContext().getResources().getIdentifier("android:id/parentPanel", null, null)
                        ).getRootView().setVisibility(View.INVISIBLE);

                        alertDialog.getButton(AlertDialog.BUTTON_POSITIVE).performClick();

                        param.setResult(alertDialog);
                    } catch (Exception ex) {
                        XposedBridge.log(ex);
                    }
                }

            }
        );
    }

}
