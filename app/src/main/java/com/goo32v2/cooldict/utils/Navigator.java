package com.goo32v2.cooldict.utils;

import android.content.Context;

import com.goo32v2.cooldict.view.activities.DictionaryManagerActivity;
import com.goo32v2.cooldict.view.activities.SettingsActivity;

/**
 * Created on 02-Jul-16. (c) CoolDict
 */

public class Navigator {

    // inject app context
    private Context context;

    public Navigator(Context context) {
        this.context = context;
    }

    public void navigateToDictionaryManagerActivity(){
        DictionaryManagerActivity.startActivity(context);
    }

    public void navigateToSettingsActivity() {
        SettingsActivity.startActivity(context);
    }
//
//    public void navigateToWordManagerActivity(WordModel model) {
//        WordManagerActivity.startActivity(context, model);
//    }
//
//    public void navigateToWordDetailActivity(WordModel model) {
//        WordDetailActivity.startActivity(context, model);
//    }
}