package com.goo32v2.cooldict.settings;

import android.os.Bundle;
import android.preference.PreferenceFragment;

import com.goo32v2.cooldict.R;

/**
 * Created on 21-Jun-16. (c) CoolDict
 */

public class SettingsFragment extends PreferenceFragment {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addPreferencesFromResource(R.xml.preferences);
        setHasOptionsMenu(true);
    }
}
