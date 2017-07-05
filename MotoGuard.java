package avik.motodisplay.nightlyauto.D52;

import java.io.IOException;
import com.motorola.avik.uiautomatoradapter.AvikTestCase;
import com.motorola.avik.uiautomatoradapter.AvikUiDevice;

import android.support.test.InstrumentationRegistry;
import android.support.test.uiautomator.UiDevice;
import android.support.test.uiautomator.UiObjectNotFoundException;
import android.support.test.uiautomator.UiScrollable;
import android.support.test.uiautomator.UiSelector;
import avik.api.AvikConstant;
import avik.moto.util.Motos;

public class MotoGuard extends AvikTestCase {
	AvikUiDevice device = this.getAvikUiDevice();
	UiDevice mDevice = UiDevice.getInstance(InstrumentationRegistry.getInstrumentation());
	avik.moto.util.Motos mUtil;
	String currentPackageName;
	
private void capturescreenOfMotoDisplay() throws Exception {
	device.executeShellCommand("am start com.lenovo.motoguard/com.lenovo.motoguard.ui.MotoGuardMainActivity");
	mUtil.takeAvikScreenshot("MotoGuard_ProtectYourDeviceAndPrivacy");
	mUtil.clickByObj("CheckBox", "com.lenovo.motoguard:id/checkbox", 0);
	mUtil.clickByObj("Start", "com.lenovo.motoguard:id/button_start", 0);
	mUtil.takeAvikScreenshot("MotoGuard_Permission_Dialog");
	mUtil.clickByObj("Allow", "com.android.packageinstaller:id/permission_allow_button", 0);
	mUtil.takeAvikScreenshot("MotoGuard_PermissionMessage_Dialog");
	mUtil.clickByObj("Allow", "com.android.packageinstaller:id/permission_allow_button", 0);
	mUtil.takeAvikScreenshot("MotoGuard_Main");
	sleep(AvikConstant.NORMALWAIT);
	device.pressMenu();
	mUtil.takeAvikScreenshot("MotoGuard_Menu");
	mUtil.clickByObj("About", "com.lenovo.motoguard:id/title", 0);
	mUtil.clickByObj("Anti-Virus", "com.lenovo.motoguard:id/app_title", 0);
	mUtil.takeAvikScreenshot("MotoGuard_AntiVirus_Scanning");
	sleep(AvikConstant.NORMALWAIT);
	mUtil.takeAvikScreenshot("MotoGuard_AntiVirus_NoVirusfound");
	mDevice.click(1007, 153);
	mUtil.takeAvikScreenshot("MotoGuard_AntiVirus_Settings");
	mUtil.clickByObj("VirusScanType", "android:id/title", 1);
        mUtil.takeAvikScreenshot("MotoGuard_AntiVirus_VirusScanType_Dialog");
        mDevice.pressBack();
//        mUtil.clickByObj("Google verify apps", "android:id/title", 5);
//        mUtil.clickByObj("ToggleOff", "com.google.android.gms:id/toggle", 0);
//        mUtil.takeAvikScreenshot("MotoGuard_AntiVirus_GoogleVerifyApps");
//        mUtil.clickByObj("ToggleOn", "com.google.android.gms:id/toggle", 0);
//        mUtil.takeAvikScreenshot("MotoGuard_AntiVirus_GoogleVerifyApps_Dialog");
//        mUtil.clickByObj("Ok", "android:id/button1", 0);
//        mDevice.pressBack();
        new UiScrollable(new UiSelector().className("android.widget.ListView")).scrollForward();
        mUtil.takeAvikScreenshot("MotoGuard_AntiVirus_Settings2");
        mUtil.clickByObj("DatabaseUpdateType", "android:id/title", 5);
        mUtil.takeAvikScreenshot("MotoGuard_AntiVirus_DatabaseUpdateType_Dialog");
        mUtil.pressBack(3);
        
        mUtil.clickByObj("WifiSecurityCheck", "com.lenovo.motoguard:id/app_title", 1);
        mUtil.takeAvikScreenshot("MotoGuard_WifiSecurity_Main");
        mDevice.pressBack();
        
        mUtil.clickByObj("Secure VPN", "com.lenovo.motoguard:id/app_title", 2);
        mUtil.takeAvikScreenshot("MotoGuard_SecureVPN_Main");
        mDevice.pressBack();
        
//        mUtil.clickByObj("App Locker", "com.lenovo.motoguard:id/app_title", 3);
//        mUtil.takeAvikScreenshot("MotoGuard_AppLocker_EnterPIN");
//        mDevice.findObject(new UiSelector().resourceId("com.lenovo.motoguard:id/password_entry")).setText("8988");
//        mDevice.pressEnter();
//        
//        mUtil.takeAvikScreenshot("MotoGuard_AppLocker_SetNow");
//        mUtil.takeAvikScreenshot("MotoGuard_AppLocker_Main");
//        mDevice.pressBack();
	}

public void delta() throws IOException, UiObjectNotFoundException{
//    mUtil.takeAvikScreenshot("MotoGuard_AppLocker_Search");
    mUtil.takeAvikScreenshot("MotoGuard_WifiSecurity_Secure");
    mUtil.takeAvikScreenshot("MotoGuard_WifiSecurity_Secure_Dialog");
    mUtil.takeAvikScreenshot("MotoGuard_WifiSecurity_UnSecure");
    mUtil.takeAvikScreenshot("MotoGuard_WifiSecurity_UnSecure_Dialog");
    mUtil.takeAvikScreenshot("MotoGuard_WifiSecurity_Risk");
    mUtil.takeAvikScreenshot("MotoGuard_WifiSecurity_Risk_Dialog");
    
    mUtil.takeAvikScreenshot("MotoGuard_AntiVirus_Virusfound");
}
	public void setUp() throws IOException {
//		appendPermission(Manifest.permission.DISABLE_KEYGUARD);
		mUtil = new Motos();
		 currentPackageName = "com.lenovo.motoguard";
		 mUtil.clearApp(currentPackageName);
	}

	public void tearDown() throws Exception {
		mUtil.pressBack(3);
		mUtil.forceCloseApp(currentPackageName);

	}
	

	// ============END - Test Function/Module definition area.==============

	// ==================START - Script MAIN body===============================
	public void testMain(){
			
					try {
						capturescreenOfMotoDisplay();
						delta();
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}

	}
	// =================END - Script MAIN body ===============================
}
