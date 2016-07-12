package com.goo32v2.cooldict.view;

/**
 * Created on 14-May-16. (c) CoolDict
 */
public interface BaseView {

    /**
     * Show message in View
     *
     * @param message - string to show
     */
    void showMessage(String message);

    /**
     * Show loading indicator in view
     *
     * @param trigger - true (active), false (inactive)
     */
    void showLoading(boolean trigger);

    /**
     * Finish activity with success result
     */
    void finishActivity();
}
