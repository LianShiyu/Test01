package avik.moto.util;
//package avik.n.settings.api;
//
//import android.os.SystemClock;
//import com.android.uiautomator.core.UiObject;
//import com.android.uiautomator.core.UiObjectNotFoundException;
//import com.android.uiautomator.core.UiScrollable;
//import com.android.uiautomator.core.UiSelector;
//import com.motorola.avik.uiautomatoradapter.AvikTestCase;
//import com.motorola.avik.uiautomatoradapter.AvikUiDevice;
//import com.motorola.avik.uiautomatoradapter.AvikUtilities;
//
//public class ScripterHelper extends AvikTestCase {
//
//    private AvikTestCase test;
//    private AvikUiDevice device = getAvikUiDevice();
//    private AvikUtilities util = getAvikUtilities();
//
//    public ScripterHelper(AvikTestCase test) {
//        this.test = test;
//    }
//
//    public android.support.test.uiautomator.UiScrollable getListView() throws UiObjectNotFoundException {
//        SystemClock.sleep(500);
//        UiScrollable listView = mGetListView();
//        if (!listView.exists()) {
//            System.out.println("Wait 1 second for ListView appear.");
//            SystemClock.sleep(500);
//            listView = mGetListView();
//            if (!listView.exists()) {
//                System.out.println("Wait 2 second for ListView appear.");
//                SystemClock.sleep(500);
//                listView = mGetListView();
//            }
//        }
//        return listView;
//    }
//
//    private UiScrollable mGetListView() throws UiObjectNotFoundException {
//        String[] classnames = { //
//        "Candroid.widget.ListView",//
//                "Candroid.widget.ScrollView", //
//                "Candroid.widget.ExpandableListView",//
//                "Candroid.widget.GridView",//
//                "Randroid:id/list"//
//        };
//        UiScrollable listView = null;
//        for (String className : classnames) {
//            String name = className.substring(1);
//            if (className.charAt(0) == 'C') {
//                listView = new UiScrollable(new UiSelector().className(name));
//            } else {
//                listView = new UiScrollable(new UiSelector().resourceId(name));
//            }
//            if (listView.exists() && listView.getChildCount() != 0) {
//                break;
//            }
//        }
//        return listView;
//    }
//

//

//
//    public UiObject selectFromList(String note, UiScrollable list, String str, int instance) throws UiObjectNotFoundException {
//        int lastOrd = 0;
//        int newOrd = 1;
//        UiObject obj = null;
//        UiObject objBiggestInstance = null;
//        UiObject objTemp = null;
//        int biggestInstance = 0;
//        list.flingToBeginning(50);
//        if (str.contains(":id")) {
//            biggestInstance = list.getChildCount(new UiSelector().resourceId(str));
//        } else if (str.contains(".widget")) {
//            biggestInstance = list.getChildCount(new UiSelector().className(str));
//        }
//
//        if (instance < 9) {
//            if (str.contains(":id")) {
//                obj = list.getChildByInstance(new UiSelector().resourceId(str), instance);
//            } else if (str.contains(".widget")) {
//                obj = list.getChildByInstance(new UiSelector().className(str), instance);
//            }
//        } else if (9 < instance && instance < 18) {
//            if (str.contains(":id")) {
//                objBiggestInstance = list.getChildByInstance(new UiSelector().resourceId(str), biggestInstance - 1);
//            } else if (str.contains(".widget")) {
//                objBiggestInstance = list.getChildByInstance(new UiSelector().className(str), biggestInstance - 1);
//            }
//            list.scrollForward();
//            for (int i = 0; i < 10; i++) {
//                if (str.contains(":id")) {
//                    objTemp = list.getChildByInstance(new UiSelector().resourceId(str), i);
//                } else if (str.contains(".widget")) {
//                    objTemp = list.getChildByInstance(new UiSelector().className(str), i);
//                }
//
//                if (objTemp.getBounds() == objBiggestInstance.getBounds()) {
//                    newOrd = i;
//                    break;
//                }
//            }
//            lastOrd = newOrd + instance - biggestInstance + 1;
//            if (str.contains(":id")) {
//                obj = list.getChildByInstance(new UiSelector().resourceId(str), lastOrd);
//            } else if (str.contains(".widget")) {
//                obj = list.getChildByInstance(new UiSelector().className(str), lastOrd);
//            }
//        } else if (instance > 18) {
//            if (str.contains(":id")) {
//                objBiggestInstance = list.getChildByInstance(new UiSelector().resourceId(str), biggestInstance - 1);
//            } else if (str.contains(".widget")) {
//                objBiggestInstance = list.getChildByInstance(new UiSelector().className(str), biggestInstance - 1);
//            }
//            list.scrollForward();
//            if (str.contains(":id")) {
//                objBiggestInstance = list.getChildByInstance(new UiSelector().resourceId(str), biggestInstance - 1);
//            } else if (str.contains(".widget")) {
//                objBiggestInstance = list.getChildByInstance(new UiSelector().className(str), biggestInstance - 1);
//            }
//            list.scrollForward();
//            for (int i = 0; i < 10; i++) {
//                if (str.contains(":id")) {
//                    objTemp = list.getChildByInstance(new UiSelector().resourceId(str), i);
//                } else if (str.contains(".widget")) {
//                    objTemp = list.getChildByInstance(new UiSelector().className(str), i);
//                }
//
//                if (objTemp.getBounds() == objBiggestInstance.getBounds()) {
//                    newOrd = i;
//                    break;
//                }
//            }
//            lastOrd = newOrd + instance - biggestInstance * 2 + 2;
//            if (str.contains(":id")) {
//                obj = list.getChildByInstance(new UiSelector().resourceId(str), lastOrd);
//            } else if (str.contains(".widget")) {
//                obj = list.getChildByInstance(new UiSelector().className(str), lastOrd);
//            }
//        }
//        return obj;
//    }


//
//    private UiSelector identifySelector(String str, int instance) {
//        UiSelector selector = null;
//        if (str.contains(":id")) {
//            selector = new UiSelector().resourceId(str).instance(instance);
//        } else if (str.contains(".widget")) {
//            selector = new UiSelector().className(str).instance(instance);
//        }
//        return selector;
//    }
//
//    public void dragToObj(String note, UiScrollable list, UiObject obj) throws UiObjectNotFoundException {
//        writeLog(note);
//        int list_left = list.getVisibleBounds().left;
//        int list_top = list.getVisibleBounds().top;
//        obj.dragTo(list_left, list_top, 200);
//    }
//
//	@Override
//	public void testMain() {
//		// TODO Auto-generated method stub
//		
//	}
//}
