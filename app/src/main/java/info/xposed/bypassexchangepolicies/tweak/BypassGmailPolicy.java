package info.xposed.bypassexchangepolicies.tweak;

import android.app.AlertDialog;
import android.os.Bundle;
import android.view.View;

import de.robv.android.xposed.XC_MethodHook;
import de.robv.android.xposed.XC_MethodReplacement;
import de.robv.android.xposed.XposedBridge;
import de.robv.android.xposed.callbacks.XC_LoadPackage;

import static de.robv.android.xposed.XposedHelpers.findAndHookMethod;
import static de.robv.android.xposed.XposedHelpers.setBooleanField;
import static de.robv.android.xposed.XposedHelpers.setIntField;

public final class BypassGmailPolicy {

    private static final String SECURITY_REQUIRED_DIALOG_CLASS = "awh";
    private static final String ON_CREATE_DIALOG_METHOD = "onCreateDialog";

    private static final String ACCOUNT_CLASS = "com.android.emailcommon.provider.Account";

    private static final String POLICY_CLASS = "com.android.emailcommon.provider.Policy";
    private static final String NORMALIZE_METHOD = "d";
    private static final String PASSWORD_MODE_FIELD = "b";
    private static final String PASSWORD_MIN_LENGTH_FIELD = "c";
    private static final String PASSWORD_MAX_FAILS_FIELD = "d";
    private static final String PASSWORD_HISTORY_FIELD = "f";
    private static final String PASSWORD_EXPIRATION_DAYS_FIELD = "e";
    private static final String PASSWORD_COMPLEX_CHARS_FIELD = "g";
    private static final String MAX_SCREEN_LOCK_TIME_FIELD = "h";
    private static final String REQUIRE_REMOTE_WIPE_FIELD = "i";
    private static final String REQUIRE_ENCRYPTION_FIELD = "j";
    private static final String REQUIRE_ENCRYPTION_EXTERNAL_FIELD = "k";
    private static final String REQUIRE_MANUAL_SYNC_ROAMING_FIELD = "l";
    private static final String DONT_ALLOW_CAMERA_FIELD = "m";
    private static final String DONT_ALLOW_ATTACHMENTS_FIELD = "n";
    private static final String DONT_ALLOW_HTML_FIELD = "o";
    private static final String MAX_ATTACHMENT_SIZE_FIELD = "p";
    private static final String MAX_TEXT_TRUNCATION_SIZE_FIELD = "q";
    private static final String MAX_HTML_TRUNCATION_SIZE_FIELD = "r";
    private static final String MAX_EMAIL_LOOKBACK_FIELD = "s";
    private static final String MAX_CALENDAR_LOOKBACK_FIELD = "t";
    private static final String PASSWORD_RECOVERY_ENABLED_FIELD = "u";

    private static final String SECURITY_POLICY_CLASS = "com.android.email.SecurityPolicy";
    private static final String IS_ACTIVE_METHOD = "a";
    private static final String GET_INACTIVE_REASONS_METHOD = "b";
    private static final String SET_ACTIVE_POLICIES_METHOD = "c";
    private static final String IS_ACTIVE_ADMIN_METHOD = "e";

    private static final String EMAIL_NOTIFICATION_CONTROLLER_CLASS = "aqh";
    private static final String SHOW_SECURITY_CHANGED_NOTIFICATION_METHOD = "c";
    private static final String SHOW_SECURITY_NEEDED_NOTIFICATION_METHOD = "b";

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

        findAndHookMethod(
            POLICY_CLASS,
            lpparam.classLoader,
            NORMALIZE_METHOD,
            new XC_MethodHook() {

                @Override
                protected void beforeHookedMethod(MethodHookParam param) throws Throwable {
                    try {
                        setIntField(param.thisObject, PASSWORD_MODE_FIELD, 0);
                        setIntField(param.thisObject, PASSWORD_MIN_LENGTH_FIELD, 0);
                        setIntField(param.thisObject, PASSWORD_MAX_FAILS_FIELD, 0);
                        setIntField(param.thisObject, PASSWORD_HISTORY_FIELD, 0);
                        setIntField(param.thisObject, PASSWORD_EXPIRATION_DAYS_FIELD, 0);
                        setIntField(param.thisObject, PASSWORD_COMPLEX_CHARS_FIELD, 0);
                        setIntField(param.thisObject, MAX_SCREEN_LOCK_TIME_FIELD, 0);
                        setBooleanField(param.thisObject, REQUIRE_REMOTE_WIPE_FIELD, false);
                        setBooleanField(param.thisObject, REQUIRE_ENCRYPTION_FIELD, false);
                        setBooleanField(param.thisObject, REQUIRE_ENCRYPTION_EXTERNAL_FIELD, false);
                        setBooleanField(param.thisObject, REQUIRE_MANUAL_SYNC_ROAMING_FIELD, false);
                        setBooleanField(param.thisObject, DONT_ALLOW_CAMERA_FIELD, false);
                        setBooleanField(param.thisObject, DONT_ALLOW_ATTACHMENTS_FIELD, false);
                        setBooleanField(param.thisObject, DONT_ALLOW_HTML_FIELD, false);
                        setIntField(param.thisObject, MAX_ATTACHMENT_SIZE_FIELD, 0);
                        setIntField(param.thisObject, MAX_TEXT_TRUNCATION_SIZE_FIELD, 0);
                        setIntField(param.thisObject, MAX_HTML_TRUNCATION_SIZE_FIELD, 0);
                        setIntField(param.thisObject, MAX_EMAIL_LOOKBACK_FIELD, 0);
                        setIntField(param.thisObject, MAX_CALENDAR_LOOKBACK_FIELD, 0);
                        setBooleanField(param.thisObject, PASSWORD_RECOVERY_ENABLED_FIELD, false);
                    } catch (Exception ex) {
                        XposedBridge.log(ex);
                    }
                }

            }
        );

        findAndHookMethod(
            SECURITY_POLICY_CLASS,
            lpparam.classLoader,
            IS_ACTIVE_METHOD,
            POLICY_CLASS,
            XC_MethodReplacement.returnConstant(true)
        );

        findAndHookMethod(
            SECURITY_POLICY_CLASS,
            lpparam.classLoader,
            GET_INACTIVE_REASONS_METHOD,
            POLICY_CLASS,
            XC_MethodReplacement.returnConstant(0)
        );

        findAndHookMethod(
            SECURITY_POLICY_CLASS,
            lpparam.classLoader,
            SET_ACTIVE_POLICIES_METHOD,
            XC_MethodReplacement.DO_NOTHING
        );

        findAndHookMethod(
            SECURITY_POLICY_CLASS,
            lpparam.classLoader,
            IS_ACTIVE_ADMIN_METHOD,
            XC_MethodReplacement.returnConstant(true)
        );

        findAndHookMethod(
            EMAIL_NOTIFICATION_CONTROLLER_CLASS,
            lpparam.classLoader,
            SHOW_SECURITY_NEEDED_NOTIFICATION_METHOD,
            ACCOUNT_CLASS,
            XC_MethodReplacement.DO_NOTHING
        );

        findAndHookMethod(
            EMAIL_NOTIFICATION_CONTROLLER_CLASS,
            lpparam.classLoader,
            SHOW_SECURITY_CHANGED_NOTIFICATION_METHOD,
            ACCOUNT_CLASS,
            XC_MethodReplacement.DO_NOTHING
        );
    }

}
