package avik.moto.util;

import java.io.IOException;
import com.motorola.avik.uiautomatoradapter.AvikTestCase;
import com.motorola.avik.uiautomatoradapter.AvikUiDevice;

import android.content.Context;
import android.content.res.Resources;
import android.content.res.Resources.NotFoundException;
import android.os.ParcelFileDescriptor;
import android.os.SystemClock;
import android.support.test.InstrumentationRegistry;
import android.support.test.uiautomator.By;
import android.support.test.uiautomator.UiDevice;
import android.support.test.uiautomator.UiObject;
import android.support.test.uiautomator.UiObjectNotFoundException;
import android.support.test.uiautomator.UiScrollable;
import android.support.test.uiautomator.UiSelector;
import android.widget.ImageView;
import avik.api.AvikConstant;

public class Motos extends AvikTestCase {

	AvikUiDevice device = this.getAvikUiDevice();
	protected UiDevice mDevice;
	Resources mResources;

	public Motos() {
		mDevice = UiDevice.getInstance(InstrumentationRegistry.getInstrumentation());
	}

	public void launchMotoAction() throws IOException{
		mDevice.executeShellCommand("am start com.motorola.actions/com.motorola.actions.settings.SettingsActivity");
		sleep(AvikConstant.NORMALWAIT);
	}
	public void dragDownNotification() throws UiObjectNotFoundException {
		mDevice.drag(0, 0, mDevice.getDisplayHeight(), mDevice.getDisplayHeight(), 300);

	}
	public void dragUpNotification() throws UiObjectNotFoundException{
		mDevice.drag(mDevice.getDisplayHeight(), mDevice.getDisplayHeight(), 0, 0, 300);
	}

	public void pressCancel() throws UiObjectNotFoundException {
		clickByObj("Cancel", "android:id/button2", 0);
		sleep(AvikConstant.SHORTWAIT);
	}

	public void clickByObj(String log, String str, int instance) throws UiObjectNotFoundException {
		writeLog(log);
		if (str.contains(":id/")) {
			if (instance == 0) {
				mDevice.findObject(new UiSelector().resourceId(str)).click();
			} else {
				mDevice.findObject(new UiSelector().resourceId(str).instance(instance)).click();
			}
		} else {
			if (instance == 0) {
				mDevice.findObject(new UiSelector().className(str)).click();
			} else {
				mDevice.findObject(new UiSelector().className(str).instance(instance)).click();
			}
		}

	}

	public void clickByText(String log,String pack, String stringkey) throws Exception {
		writeLog(log);
		String note = getResourceByPackAndStringKey(pack, stringkey);
		writeLog("=== " + note + " is displayed.");
		getListView().scrollTextIntoView(note);
		mDevice.findObject(new UiSelector().text(note)).click();

	}

	public UiObject getByText(String pack, String stringkey) throws Exception {
		UiObject mObj;
		String note = getResourceByPackAndStringKey(pack, stringkey);
		getListView().scrollTextIntoView(note);
		writeLog("=== " + note + " is displayed.");
		mObj = mDevice.findObject(new UiSelector().text(note));
		return mObj;

	}

	private String getResourceByPackAndStringKey(String pack, String stringKey) throws Exception {

		Context context = ContextFactory.getInstance().getContext(pack);
		Resources resources = context.getResources();
		int identifier = resources.getIdentifier(stringKey, "string", pack);
		if (identifier == 0) {
			return null;
		} else {
			return resources.getString(identifier);
		}
	}

	public void takeAvikScreenshot(String screenName) {
		sleep(AvikConstant.SHORTWAIT);
		device.takeAvikScreenshot(screenName);
	}

	public void pressOk() throws UiObjectNotFoundException {
		mDevice.findObject(new UiSelector().resourceId("android:id/button1")).click();
	}

	public void lunchSettings() throws IOException {
		device.executeShellCommand("am start com.android.settings");
		sleep(AvikConstant.SHORTWAIT);
	}

	public void lunchDataUsage() throws UiObjectNotFoundException, IOException {
		device.executeShellCommand("am start com.android.settings");
		sleep(AvikConstant.SHORTWAIT);
		if (mDevice.findObject(By.res("com.android.settings:id/dashboard_container"))
				.hasObject(By.res("com.android.settings:id/overflow"))) {
			mDevice.findObject(new UiSelector().resourceId("android:id/icon").instance(0)).click();
			sleep(AvikConstant.NORMALWAIT);
			mDevice.findObject(new UiSelector().resourceId("android:id/icon").instance(0)).click();
			writeLog("close suggestions in settings");
		}
		sleep(AvikConstant.SHORTWAIT);
		mDevice.findObject(new UiSelector().resourceId("com.android.settings:id/category").instance(0)).dragTo(0, 0,
				100);
		sleep(AvikConstant.SHORTWAIT);
		mDevice.findObject(new UiSelector().className(ImageView.class.getName()).instance(3)).click();
		sleep(AvikConstant.NORMALWAIT);
	}

	public void lunchBattery() throws Exception {
		device.executeShellCommand("am start com.android.settings");
		clickByText("","com.android.settings", "power_usage_summary_title");
		sleep(AvikConstant.NORMALWAIT);
	}

	public void lunchStorage() throws Exception {
		device.executeShellCommand("am start com.android.settings");
		clickByText("","com.android.settings", "storage_usb_settings");
		sleep(AvikConstant.NORMALWAIT);
	}

	public void forceCloseApp(String packageName) throws IOException {
		writeLog("=== clear " + packageName + " data");
		mDevice.executeShellCommand(String.format("am force-stop %s", packageName));
	}

	public void clearApp(String packageName) throws IOException {
		writeLog("=== " + packageName + "force closed.");
		mDevice.executeShellCommand(String.format("pm clear %s", packageName));
	}

	public void scrollScreensAndCapture(UiScrollable object, int times, String screenName) {
		object.setMaxSearchSwipes(40);
		try {
			object.scrollToBeginning(40, 10);
		} catch (UiObjectNotFoundException e) {
			e.printStackTrace();
		}
		for (int i = 0; i < times; i++) {
			device.takeAvikScreenshot(screenName + "_Scrolling" + (i + 1));
			try {
				object.scrollForward();
			} catch (UiObjectNotFoundException e) {
				writeLog("=== some errors in the script");
				e.printStackTrace();
			}
		}
	}

	public UiScrollable getListView() throws UiObjectNotFoundException {
		SystemClock.sleep(500);
		UiScrollable listView = mGetListView();
		if (!listView.exists()) {
			System.out.println("Wait 1 second for ListView appear.");
			SystemClock.sleep(500);
			listView = mGetListView();
			if (!listView.exists()) {
				System.out.println("Wait 2 second for ListView appear.");
				SystemClock.sleep(500);
				listView = mGetListView();
			}
		}
		return listView;
	}

	private UiScrollable mGetListView() throws UiObjectNotFoundException {
		String[] classnames = { //
				"Candroid.widget.ListView", //
				"Candroid.widget.ScrollView", //
				"Candroid.widget.ExpandableListView", //
				"Candroid.widget.GridView", //
				"CRandroid:id/list", "Candroid.support.v7.widget.RecyclerView" };
		UiScrollable listView = null;
		for (String className : classnames) {
			String name = className.substring(1);
			if (className.charAt(0) == 'C') {
				listView = new UiScrollable(new UiSelector().className(name));
			} else {
				listView = new UiScrollable(new UiSelector().resourceId(name));
			}
			if (listView.exists() && listView.getChildCount() != 0) {
				break;
			}
		}
		return listView;
	}

	// public boolean isPackInstalled(String packageName){
	// boolean installed = false;
	// Context context = ActivityThread.systemMain().getSystemContext();
	// PackageManager pm = context.getPackageManager();
	// try {
	// pm.getPackageInfo(packageName, PackageManager.GET_ACTIVITIES);
	// installed = true;
	// }catch(PackageManager.NameNotFoundException e){
	// System.out.println("Current package name is: " +
	// mDevice.getCurrentPackageName());
	// installed = false;
	// }
	// return installed;
	// }
	public void pressBack(int n) {
		writeLog("=== " + n + " times back.");
		for (int i = 0; i < n; i++) {
			mDevice.pressBack();
			sleep(AvikConstant.SHORTWAIT);
		}
	}

	public void openAppInfoByPack(String packageName) {

	}

	public ParcelFileDescriptor runCommand(String activity) {
		ParcelFileDescriptor fileDescriptor = InstrumentationRegistry.getInstrumentation().getUiAutomation()
				.executeShellCommand(activity);
		SystemClock.sleep(2000);
		return fileDescriptor;
	}

	public String getString(String key, String mPackage) throws NotFoundException {
		String value;
		int identifier = mResources.getIdentifier(key, "String", mPackage);
		if (identifier == 0) {
			value = null;
		} else {
			value = mResources.getString(identifier);
		}
		return value;
	}

	@Override
	public void testMain() {
		// TODO Auto-generated method stub

	}
}
