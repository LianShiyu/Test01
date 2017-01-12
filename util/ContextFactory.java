/*
 * Copyright (C) 2016 Motorola Mobility Inc.
 * All Rights Reserved.
 * Motorola Confidential Restricted.
 */
package avik.moto.util;

import android.app.ActivityThread;
import android.content.Context;
import android.content.pm.PackageManager.NameNotFoundException;
import android.os.Looper;
import android.util.Log;

import java.util.HashMap;
import java.util.Map;

/**
 * A factory for {@link Context} objects.<br>
 * This singleton class is responsible for creating or retrieving
 * <code>Context</code> objects for its clients. There are no guarantees
 * concerning the source of the objects (created) returned, a given
 * implementation may create new <code>Context</code> objects or, if possible,
 * it may use <code>Context</code> objects created by the system. Therefore
 * clients should be aware that the returned context may be shared.
 * 
 * @author Irineu Moura (vpjk36)
 *
 */
public class ContextFactory {

    private static ContextFactory INSTANCE;

    public static ContextFactory getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new ContextFactory();
        }
        return INSTANCE;
    }

    /**
     * System context used to created another context objects by calling
     * <code>Context.createPackageContext(String, int)</code>.
     */
    private final Context systemContext;

    /**
     * A cache for the created context objects.
     */
    private final Map<String, Context> cache;

    private ContextFactory() {
        cache = new HashMap<String, Context>();
        Looper.prepare(); // Required by ActivityThread.systemMain()
        systemContext = ActivityThread.systemMain().getSystemContext();
    }

    /**
     * Returns a {@link Context} object for the package with
     * <code>APP_PACKAGE_NAME</code>.<br>
     * Objects returned by this method may be cached so that consecutive calls
     * for the same package name may return the same {@link Context} object.<br>
     * If an application with <code>APP_PACKAGE_NAME</code> doesn't exist then a
     * <code>null</code> value will be returned.
     * 
     * @param packageName
     *            Package name for which a context should be created.
     * @return
     */
    public Context getContext(String packageName) {
        Context context = null;
        if (cache.containsKey(packageName)) {
            context = cache.get(packageName);
        } else {
            try {
                /*
                 * What happens to the created Context objects when we change
                 * the locale?
                 */
                context = systemContext.createPackageContext(packageName, Context.CONTEXT_IGNORE_SECURITY);
                cache.put(packageName, context);
            } catch (NameNotFoundException e) {
                e.printStackTrace();
            }
        }
        return context;
    }

    public Context getSystemContext() {
        return systemContext;
    }

}