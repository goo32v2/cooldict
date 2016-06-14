package com.goo32v2.cooldict.utils;

import android.support.test.espresso.IdlingResource;

/**
 * Created on 13-Jun-16. (c) CoolDict
 */

// TODO: 13-Jun-16 do this class
public class EspressoIdlingResource {

    private static final String RESOURCE = "GLOBAL";

    private static SimpleCountingIdlingResource mCountingIdlingResource =
            new SimpleCountingIdlingResource(RESOURCE);

    public static void increment() {
        mCountingIdlingResource.increment();
    }

    public static void decrement() {
        mCountingIdlingResource.decrement();
    }

    public static IdlingResource getIdlingResource() {
        return mCountingIdlingResource;
    }
}
