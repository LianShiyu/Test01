package avik.settings.nightlyauto;

//123456
import java.io.IOException;
import com.motorola.avik.uiautomatoradapter.AvikTestCase;
import com.motorola.avik.uiautomatoradapter.AvikUiDevice;
import android.provider.Settings;
import android.support.test.InstrumentationRegistry;
import android.support.test.uiautomator.UiDevice;
import android.support.test.uiautomator.UiObject;
import android.support.test.uiautomator.UiObjectNotFoundException;
import android.support.test.uiautomator.UiSelector;
import avik.api.AvikConstant;
import avik.settings.util.SettingsB;

public class SettingsWiFi extends AvikTestCase {
    AvikUiDevice device = this.getAvikUiDevice();
    UiDevice mDevice = UiDevice.getInstance(InstrumentationRegistry.getInstrumentation());
    SettingsB mSettings;
    String currentPackageName;
    String WiFiName = "Avik-WiFi";

    private void capturescreenOfSettings() throws Exception {
        mDevice.executeShellCommand(String.format("am start -a %s", Settings.ACTION_WIFI_SETTINGS));
        sleep(AvikConstant.NORMALWAIT);
        UiObject offBtn = mDevice.findObject(new UiSelector().resourceId("com.android.settings:id/switch_widget"));
        if (offBtn.isChecked()) {// On
            mSettings.clickByObj("Off switch", "com.android.settings:id/switch_widget", 0);
            sleep(AvikConstant.SHORTWAIT);
            mSettings.takeAvikScreenshot("Settings_WiFi_Off_Main");
            UiObject scanningSettings = mDevice.findObject(new UiSelector().resourceId("android:id/empty"));
            mDevice.click(scanningSettings.getBounds().centerX(), scanningSettings.getBounds().centerY());
            mSettings.takeAvikScreenshot("Settings_WiFi_Off_LocationScanning");
            UiObject scanning = mDevice.findObject(new UiSelector().resourceId("android:id/switch_widget"));
            if (scanning.exists()) {
                mDevice.pressBack();
            }
            mSettings.clickByObj("On switch", "com.android.settings:id/switch_widget", 0);
        } else {
            mSettings.takeAvikScreenshot("Settings_WiFi_Off_Main");
            UiObject scanningSettings = mDevice.findObject(new UiSelector().resourceId("android:id/empty"));
            mDevice.click(scanningSettings.getBounds().centerX(), scanningSettings.getBounds().centerY());
            mSettings.takeAvikScreenshot("Settings_WiFi_Off_LocationScanning");
            UiObject scanning = mDevice.findObject(new UiSelector().resourceId("android:id/switch_widget"));
            if (scanning.exists()) {
                mDevice.pressBack();
            }
            mSettings.clickByObj("On switch", "com.android.settings:id/switch_widget", 0);

        }
        mSettings.getListView().scrollTextIntoView(WiFiName);
        mDevice.findObject(new UiSelector().text(WiFiName)).click();
        mSettings.disappearKeyboard();
        mSettings.takeAvikScreenshot("Settings_WiFi_AvikWiFi_Details");
        mSettings.clickByObj("Cancel", "android:id/button2", 0);
        mSettings.takeAvikScreenshot("Settings_WiFi");
        mSettings.clickByText("Add network", "com.android.settings", "wifi_add_network");
        mSettings.disappearKeyboard();
        mSettings.takeAvikScreenshot("Settings_WiFi_AddNetwork");
        mSettings.clickByObj("Security", "com.android.settings:id/security", 0);
        mSettings.takeAvikScreenshot("Settings_WiFi_Security_Dialog");
        mSettings.clickByObj("WEP", "android:id/text1", 1);
        mSettings.takeAvikScreenshot("Settings_WiFi_SecurityWEP_Dialog");
        mSettings.clickByObj("Security", "com.android.settings:id/security", 0);
        mSettings.clickByObj("WPA/WPA2 PSK", "android:id/text1", 2);
        mSettings.takeAvikScreenshot("Settings_WiFi_SecurityWPA_Dialog");
        mSettings.clickByObj("Security", "com.android.settings:id/security", 0);
        mSettings.clickByObj("802.1x EAP", "android:id/text1", 3);
        mSettings.scrollScreensAndCapture(mSettings.getListView(), 2, "Settings_WiFi_SecurityEAP_Dialog");
        mSettings.getListView().scrollToBeginning(100);
        mSettings.clickByObj("Phase 2 autoentication", "com.android.settings:id/phase2", 0);
        mSettings.takeAvikScreenshot("Settings_WiFi_SecurityEAP_Phase2Authentication_Dialog");
        mSettings.clickByObj("None", "android:id/text1", 0);
        mSettings.clickByObj("CA certificate", "com.android.settings:id/ca_cert", 0);
        mSettings.takeAvikScreenshot("Settings_WiFi_SecurityEAP_CACertificate_Dialog");
        mSettings.clickByObj("Use system certificates", "android:id/text1", 1);
        mSettings.getListView().scrollToEnd(100);
        mSettings.takeAvikScreenshot("Settings_WiFi_SecurityEAP_UseSystemCertificates");
        mSettings.clickByObj("CA certificate", "com.android.settings:id/ca_cert", 0);
        mSettings.clickByObj("Do not validate", "android:id/text1", 2);
        mSettings.getListView().scrollToEnd(100);
        mSettings.takeAvikScreenshot("Settings_WiFi_SecurityEAP_DoNotValidate");
        mSettings.clickByObj("CA certificate", "com.android.settings:id/ca_cert", 0);
        mSettings.clickByObj("Please select", "android:id/text1", 0);
        mSettings.getListView().scrollToBeginning(100);
        mSettings.clickByObj("EAP metod", "com.android.settings:id/method", 0);
        mSettings.takeAvikScreenshot("Settings_WiFi_SecurityEAP_EAPMethod_Dialog");
        mSettings.clickByObj("TLS", "android:id/text1", 1);
        mSettings.takeAvikScreenshot("Settings_WiFi_SecurityEAP_TLS");
        mSettings.clickByObj("User certificate", "com.android.settings:id/user_cert", 0);
        mSettings.takeAvikScreenshot("Settings_WiFi_SecurityEAP_TLS_UserCertificate_Dialog");
        mSettings.clickByObj("Please select", "android:id/text1", 0);
        mSettings.clickByObj("EAP metod", "com.android.settings:id/method", 0);
        mSettings.clickByObj("TTLS", "android:id/text1", 2);
        mSettings.clickByObj("Phase 2 authentication", "com.android.settings:id/phase2", 0);
        mSettings.takeAvikScreenshot("Settings_WiFi_SecurityEAPTTLS_Phase2Authentication_Dialog");
        mSettings.clickByObj("None", "android:id/text1", 0);
        mSettings.clickByObj("Security", "com.android.settings:id/security", 0);
        mSettings.clickByObj("None", "android:id/text1", 0);
        // Advanced options
        mSettings.clickByObj("Advanced options", "com.android.settings:id/wifi_advanced_togglebox", 0);
        mSettings.takeAvikScreenshot("Settings_WiFi_AddNetwork_AdvancedOptions");
        mSettings.clickByObj("Proxy", "com.android.settings:id/proxy_settings_fields", 0);
        mSettings.takeAvikScreenshot("Settings_WiFi_AddNetwork_AdvancedOptionsProxy_Dialog");
        mSettings.clickByObj("None", "android:id/text1", 0);
        mSettings.clickByObj("IP settings", "com.android.settings:id/ip_settings", 0);
        mSettings.takeAvikScreenshot("Settings_WiFi_AddNetwork_AdvancedOptionsIpSettings_Dialog");
        mSettings.clickByObj("DHCP", "android:id/text1", 0);
        // Manual of Proxy, and Static of IP settings
        mSettings.clickByObj("Proxy", "com.android.settings:id/proxy_settings_fields", 0);
        mSettings.clickByObj("Manual", "android:id/text1", 1);
        mSettings.getListView().scrollToEnd(100);
        mSettings.clickByObj("IP settings", "com.android.settings:id/ip_settings", 0);
        mSettings.clickByObj("Static", "android:id/text1", 1);
        mSettings.scrollScreensAndCapture(mSettings.getListView(), 3, "Settings_WiFi_AddNetwork_AdvancedOptionsProxyManual_Dialog");
        // Proxy Auto-Config of Proxy, and Static of IP settings
        mSettings.getListView().scrollToBeginning(100);
        mSettings.clickByObj("Proxy", "com.android.settings:id/proxy_settings_fields", 0);
        mSettings.clickByObj("Proxy Auto-Config", "android:id/text1", 2);
        mSettings.clickByObj("IP settings", "com.android.settings:id/ip_settings", 0);
        mSettings.clickByObj("DHCP", "android:id/text1", 0);
        mSettings.takeAvikScreenshot("Settings_WiFi_AddNetwork_AdvancedOptionsProxyProxyAutoConfig");
        mSettings.pressCancel();
    }

    public void captureOfAdvanced() throws UiObjectNotFoundException, IOException {
        try {
            // mDevice.executeShellCommand(String.format("am start -a %s",
            // Settings.ACTION_WIFI_SETTINGS));
            mSettings.clickByObj("advanced", "android.widget.TextView", 1);
            mSettings.takeAvikScreenshot("Settings_WiFi_Advanced");
            // mSettings.clickByObj("Reset default networks",
            // "android:id/summary", 0);
            // mSettings.takeAvikScreenshot("Settings_WiFi_Advanced_ResetDefaultNetwork_Toast");
            mSettings.clickByObj("Keep Wi-Fi on during sleep", "android:id/summary", 2);
            mSettings.takeAvikScreenshot("Settings_WiFi_Advanced_KeepWiFiOnDuringSleep");
            mSettings.pressCancel();
            mSettings.pressBack(1);
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

    public void captureOfSettings() throws IOException, UiObjectNotFoundException {
        // mDevice.executeShellCommand(String.format("am start -a %s",
        // Settings.ACTION_WIFI_SETTINGS));
        try {
            mDevice.pressMenu();
            mSettings.takeAvikScreenshot("Settings_WiFi_Settings_Menu");
            mSettings.clickByObj("Advanced", "android:id/title", 1);
            mSettings.takeAvikScreenshot("Settings_WiFi_Settings");
            mSettings.clickByObj("WPS Pin Entry", "android:id/title", 3);
            mSettings.takeAvikScreenshot("Settings_WiFi_Settings_WpsPinEntry_Dialog");
            mSettings.clickByObj("Cancel", "com.android.settings:id/wps_dialog_btn", 0);
            mSettings.clickByObj("WPS Push Button", "android:id/title", 2);
            mSettings.takeAvikScreenshot("Settings_WiFi_Settings_WpsPushButton_Dialog");
            mSettings.clickByObj("Cancel", "com.android.settings:id/wps_dialog_btn", 0);
            mSettings.clickByObj("Wi-Fi Direct", "android:id/title", 1);
            mSettings.takeAvikScreenshot("Settings_WiFi_SettingsDirect");
            mSettings.clickByObj("create group", "android:id/summary", 0);
            mSettings.takeAvikScreenshot("Settings_WiFi_SettingsDirect_CreateGrop_Dialog");
            mSettings.pressOk();
            mSettings.takeAvikScreenshot("Settings_WiFi_SettingsDirect_GroupCreated");
            mSettings.clickByObj("disconnect group", "android:id/summary", 0);
            mSettings.takeAvikScreenshot("Settings_WiFi_SettingsDirect_Disconnect");
            mSettings.pressOk();
            mSettings.clickByObj("Group name", "android:id/title", 1);
            mSettings.takeAvikScreenshot("Settings_WiFi_SettingsDirect_ForgetGroup");
            mSettings.pressOk();
            mDevice.pressMenu();
            mSettings.takeAvikScreenshot("Settings_WiFi_SettingsDirect_Menu");
            mSettings.clickByObj("Configure device", "android:id/title", 0);
            mSettings.disappearKeyboard();
            mSettings.takeAvikScreenshot("Settings_WiFi_SettingsDirect_Configure");
            mSettings.clickByObj("device number", "com.android.settings:id/device_limit", 0);
            mSettings.takeAvikScreenshot("Settings_WiFi_SettingsDirect_Configure_LimitNumber");
            mSettings.clickByObj("2 Devices", "android:id/text1", 0);
            mSettings.clickByObj("device timeout", "com.android.settings:id/device_timeout", 0);
            mSettings.takeAvikScreenshot("Settings_WiFi_SettingsDirect_Configure_LimitTime");
            mSettings.clickByObj("15 minutes", "android:id/text1", 0);
            mSettings.clickByObj("Auto connect", "com.android.settings:id/auto_connect", 0);
            mSettings.takeAvikScreenshot("Settings_WiFi_SettingsDirect_Configure_AutoConenct");
            mSettings.pressCancel();
            mSettings.pressBack(1);
            mSettings.clickByObj("Install certificates", "android:id/title", 0);
            // Sd Card
            // mSettings.clickByObj("sansung SD card", "android:id/title", 3);
            // mSettings.takeAvikScreenshot("Settings_WiFi_SettingsInstallCertificates_SdCard");
            // Download
            // mDevice.executeShellCommand(String.format("am start -a %s",
            // Settings.ACTION_WIFI_SETTINGS));
            // mDevice.pressMenu();
            // mSettings.clickByObj("Advanced", "android:id/title", 1);
            // mSettings.clickByObj("Install certificates", "android:id/title",
            // 0);
            sleep(AvikConstant.SHORTWAIT);
            mSettings.takeAvikScreenshot("Settings_WiFi_SettingsInstallCertificates_Openfrom");
            mSettings.clickByObj("item list", "android.widget.ImageButton", 0);
            sleep(AvikConstant.SHORTWAIT);
            mSettings.clickByObj("Downloads", "android:id/title", 1);
            sleep(AvikConstant.SHORTWAIT);
            mSettings.clickByObj("Downloads", "android:id/title", 1);
            mDevice.pressMenu();
            mSettings.takeAvikScreenshot("Settings_WiFi_SettingsInstallCertificates_Downloads_ShowFileSize_Dialog");

            mSettings.clickByObj("Show file size", "android:id/title", 0);
            mDevice.pressMenu();
            mSettings.takeAvikScreenshot("Settings_WiFi_SettingsInstallCertificates_Downloads_HideFileSize_Dialog");
            mDevice.pressBack();
            mSettings.clickByObj("Sort type", "com.android.documentsui:id/menu_sort", 0);
            mSettings.takeAvikScreenshot("Settings_WiFi_SettingsInstallCertificates_Downloads_SortType_Dialog");
            mDevice.pressBack();
            mSettings.clickByObj("Image button", "android.widget.ImageButton", 0);
            // Device
            mSettings.clickByObj("XT1650", "android:id/title", 2);
            mSettings.takeAvikScreenshot("Settings_WiFi_SettingsInstallCertificates_Device");
            mSettings.clickByObj("Back button", "android.widget.ImageButton", 0);
            sleep(AvikConstant.NORMALWAIT);
            mSettings.clickByObj("Recent", "android:id/title", 0);
            mDevice.pressMenu();
            mSettings.clickByObj("Hide file size", "android:id/title", 0);
            mSettings.pressBack(4);
            writeLog("=== the script is executed in the end");
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
        mSettings = new SettingsB();
        currentPackageName = "com.android.settings";
        mSettings.clearApp(currentPackageName);
    }

    public void tearDown() throws Exception {
        mDevice.executeShellCommand(String.format("am start -a %s", Settings.ACTION_WIFI_SETTINGS));
        sleep(AvikConstant.NORMALWAIT);
        UiObject offBtn = mDevice.findObject(new UiSelector().resourceId("com.android.settings:id/switch_widget"));
        if (offBtn.isChecked()) {
            writeLog("already On");
            mSettings.clickByObj("Off switch", "com.android.settings:id/switch_widget", 0);
        } else {
            writeLog("already off");
        }
        mSettings.pressBack(3);
        mSettings.forceCloseApp(currentPackageName);
        writeLog("===== End of the script");
    }

    public void delta() {

    }

    // ============END - Test Function/Module definition area.==============

    // ==================START - Script MAIN body===============================
    public void testMain() {

        try {
            // delta();
            capturescreenOfSettings();
            captureOfAdvanced();
            captureOfSettings();
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
    // =================END - Script MAIN body ===============================
}
