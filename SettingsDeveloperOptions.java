package avik.settings.nightlyauto;

import java.io.IOException;

import com.motorola.avik.uiautomatoradapter.AvikTestCase;
import com.motorola.avik.uiautomatoradapter.AvikUiDevice;

import android.support.test.InstrumentationRegistry;
import android.support.test.uiautomator.UiDevice;
import android.support.test.uiautomator.UiObjectNotFoundException;
import android.support.test.uiautomator.UiSelector;
import avik.api.AvikConstant;
import avik.settings.util.SettingsB;

public class SettingsDeveloperOptions extends AvikTestCase {
    AvikUiDevice device = this.getAvikUiDevice();
    UiDevice mDevice = UiDevice.getInstance(InstrumentationRegistry.getInstrumentation());
    SettingsB mUtil;
    String currentPackageName;
    String pack = "com.android.settingslib";// com.android.settingslib
    String pack2 = "com.android.settings";

    private void capturescreenOfSettings() throws Exception {
        try {
            mDevice.executeShellCommand(String.format("am start -n %s", "com.android.settings/.DevelopmentSettings"));
            mUtil.scrollScreensAndCapture(mUtil.getListView(), 13, "Settings_DeveloperOptions_Main");
            mUtil.getListView().scrollToBeginning(100);
            mUtil.clickByText("", "android", "bugreport_title");// take bug report
            mUtil.takeAvikScreenshot("Settings_DeveloperOptions_TakeBugReport");
            mUtil.pressBack(1);
            /*
             * mUtil.pressOk(); mUtil.takeAvikScreenshot(
             * "Settings_DeveloperOptions_TakeBugReport_Toast"); mUtil.dragDownNotification();
             * sleep(5000); mUtil.takeAvikScreenshot(
             * "Settings_DeveloperOptions_TakeBugReport_Notification"); mUtil.clickByObj("Cancel",
             * "android:id/action0", 2); mUtil.dragUpNotification(); sleep(AvikConstant.SHORTWAIT);
             */
            mUtil.clickByText("", pack2, "local_backup_password_title");// Desktop
                                                                        // backup
            mUtil.takeAvikScreenshot("Settings_DeveloperOptions_DesktopBackupPassword");
            mUtil.clickByObj("Set backup password", "com.android.settings:id/backup_pw_set_button", 0);
            mUtil.takeAvikScreenshot("Settings_DeveloperOptions_DesktopBackupPassword_NewPassword");
            mUtil.clickByText("", pack2, "local_backup_password_title");// Desktop
                                                                        // backup
            mDevice.findObject(new UiSelector().resourceId("com.android.settings:id/new_backup_pw")).setText("1234");
            mUtil.clickByObj("Set backup password", "com.android.settings:id/backup_pw_set_button", 0);
            mUtil.takeAvikScreenshot("Settings_DeveloperOptions_DesktopBackupPassword_DontMatch");
            mUtil.clickByObj("cancel", "com.android.settings:id/backup_pw_cancel_button", 0);
            mUtil.clickByText("", pack2, "local_backup_password_title");// Desktop
                                                                        // backup
            mDevice.findObject(new UiSelector().resourceId("com.android.settings:id/current_backup_pw")).setText("1111");
            mUtil.clickByObj("Set backup password", "com.android.settings:id/backup_pw_set_button", 0);
            mUtil.takeAvikScreenshot("Settings_DeveloperOptions_DesktopBackupPassword_FailurePassword");
            mUtil.clickByObj("cancel", "com.android.settings:id/backup_pw_cancel_button", 0);
            mUtil.clickByText("", pack2, "hdcp_checking_title");// HDCP checking
            mUtil.takeAvikScreenshot("Settings_DeveloperOptions_HdcpChecking");
            mUtil.pressBack(1);
            mUtil.clickByText("", pack2, "oem_unlock_enable");// OEM unlocking
            mUtil.takeAvikScreenshot("Settings_DeveloperOptions_OemUnLocking");
            mUtil.pressBack(1);
            mUtil.clickByText("", pack2, "runningservices_settings_title");// Running
                                                                           // services,show_running_services
            sleep(AvikConstant.NORMALWAIT);
            mUtil.takeAvikScreenshot("Settings_DeveloperOptions_RunningServices");
            mUtil.pressBack(1);
            mUtil.clickByText("", pack2, "select_webview_provider_title");// WebView
                                                                          // implementation
            mUtil.takeAvikScreenshot("Settings_DeveloperOptions_WebViewImplementation");
            mUtil.pressBack(1);
            mUtil.clickByText("", "com.android.systemui", "demo_mode");// Demo mode
            mUtil.takeAvikScreenshot("Settings_DeveloperOptions_DemoMode");
            mUtil.pressBack(1);
            mUtil.clickByText("", pack2, "mock_location_app");// Select mock
                                                              // location app
            mUtil.takeAvikScreenshot("Settings_DeveloperOptions_SelectMockLocationApp");
            mUtil.pressBack(1);
            mUtil.clickByText("", pack2, "select_logd_size_title");// Logger buffer
                                                                   // sizes
            mUtil.takeAvikScreenshot("Settings_DeveloperOptions_LoggerBufferSizes");
            mUtil.pressBack(1);
            mUtil.clickByText("", pack2, "select_usb_configuration_title");// Select
                                                                           // USB
                                                                           // Configuration
            mUtil.takeAvikScreenshot("Settings_DeveloperOptions_SelectUsbConfiguration");
            mUtil.pressBack(1);
            mUtil.clickByText("", pack2, "window_animation_scale_title");// Window
                                                                         // animation
                                                                         // scale
            mUtil.takeAvikScreenshot("Settings_DeveloperOptions_WwindowAnimationScale");
            mUtil.pressBack(1);
            mUtil.clickByText("", pack2, "transition_animation_scale_title");// Transition
                                                                             // animation
                                                                             // scale
            mUtil.takeAvikScreenshot("Settings_DeveloperOptions_TransitionAnimationScale");
            mUtil.pressBack(1);
            mUtil.clickByText("", pack2, "animator_duration_scale_title");// Animator
                                                                          // duration
                                                                          // scale
            mUtil.takeAvikScreenshot("Settings_DeveloperOptions_AnimatorDurationScale");
            mUtil.pressBack(1);
            mUtil.clickByText("", pack2, "overlay_display_devices_title");// Simulate
                                                                          // secondary
                                                                          // displays
            mUtil.scrollScreensAndCapture(mUtil.getListView(), 2, "Settings_DeveloperOptions_SimulateSecondaryDisplays");
            mUtil.pressBack(1);
            mUtil.clickByText("", pack2, "developer_smallest_width");// Smallest
                                                                     // width
            mUtil.takeAvikScreenshot("Settings_DeveloperOptions_SmallestWidth");
            mUtil.pressCancel();
            mUtil.clickByText("", pack2, "debug_hw_overdraw");// Debug GPU overdraw
            mUtil.takeAvikScreenshot("Settings_DeveloperOptions_DebugGpuOverdraw");
            mUtil.pressBack(1);
            mUtil.clickByText("", pack2, "show_non_rect_clip");// Debug
                                                               // non-rectangular
                                                               // clip operations
            mUtil.takeAvikScreenshot("Settings_DeveloperOptions_DebugNonRectangularClipOperations");
            mUtil.pressBack(1);
            mUtil.clickByText("", pack2, "simulate_color_space");// Simulate color
                                                                 // space
            mUtil.takeAvikScreenshot("Settings_DeveloperOptions_SimulateColorSpace");
            mUtil.pressBack(1);
            mUtil.clickByText("", pack2, "track_frame_time");// Profile GPU
                                                             // rendering
            mUtil.takeAvikScreenshot("Settings_DeveloperOptions_ProfileGpuRendering");
            mUtil.pressBack(1);
            mUtil.clickByText("", pack2, "app_process_limit_title");// Background
                                                                    // process limit
            mUtil.takeAvikScreenshot("Settings_DeveloperOptions_BackgroundProcessLimit");
            mUtil.pressBack(1);
            mUtil.clickByText("", pack2, "inactive_apps_title");// Inactive apps
            mUtil.takeAvikScreenshot("Settings_DeveloperOptions_InactiveApps");
            mUtil.pressBack(1);
        } catch (Exception e) {
            writeLog("===== crash happen ====");
            writeLog(e.toString());
            StackTraceElement[] messages = e.getStackTrace();
            int length = messages.length;
            for (int i = 0; i < length; i++) {
                System.out.println("ClassName:" + messages[i].getClassName());
                System.out.println("getFileName:" + messages[i].getFileName());
                System.out.println("getLineNumber:" + messages[i].getLineNumber());
                System.out.println("getMethodName:" + messages[i].getMethodName());
                System.out.println("toString:" + messages[i].toString());
            }
        }
    }

    public void setUp() throws IOException {
        // appendPermission(Manifest.permission.DISABLE_KEYGUARD);
        mUtil = new SettingsB();
        currentPackageName = "com.android.settings";
        mUtil.clearApp(currentPackageName);
    }

    public void tearDown() throws Exception {
        mUtil.pressBack(3);
        mUtil.forceCloseApp(currentPackageName);
        writeLog("===== End of the script");
    }

    public void delta() throws IOException, UiObjectNotFoundException {
        mDevice.executeShellCommand(String.format("am start -n %s", "com.android.settings/.DevelopmentSettings"));
        mUtil.scrollScreensAndCapture(mUtil.getListView(), 13, "Settings_DeveloperOptions_Main");
    }

    // ============END - Test Function/Module definition area.==============

    // ==================START - Script MAIN body===============================
    public void testMain() {

        try {
            capturescreenOfSettings();
            // delta();
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }
    // =================END - Script MAIN body ===============================
}
