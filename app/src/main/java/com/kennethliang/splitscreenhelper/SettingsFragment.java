package com.kennethliang.splitscreenhelper;

import android.accessibilityservice.AccessibilityServiceInfo;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.provider.Settings;
import android.view.accessibility.AccessibilityManager;

import androidx.preference.Preference;
import androidx.preference.PreferenceFragmentCompat;
import androidx.preference.SwitchPreferenceCompat;

import java.util.List;

public class SettingsFragment extends PreferenceFragmentCompat implements Preference.OnPreferenceClickListener {

    private static final String SERVICE_NAME = "com.kennethliang.splitscreenhelper.SplitScreenAccessibilityService";

    @Override
    public void onCreatePreferences(Bundle savedInstanceState, String rootKey) {
        setPreferencesFromResource(R.xml.preferences, rootKey);
        SwitchPreferenceCompat p = findPreference("enabled");
        if (p != null) {
            p.setOnPreferenceClickListener(this);
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        SwitchPreferenceCompat p = findPreference("enabled");
        if (p != null) {
            p.setChecked(isAccessibilityGranted());
        }
    }

    @Override
    public boolean onPreferenceClick(Preference preference) {
        if (preference.getKey().equals("enabled")) {
            showAccessibilityDialog();
            return true;
        }
        return false;
    }

    private boolean isAccessibilityGranted() {
        AccessibilityManager am = (AccessibilityManager) getActivity().getSystemService(Context.ACCESSIBILITY_SERVICE);
        List<AccessibilityServiceInfo> serviceList = am.getEnabledAccessibilityServiceList(AccessibilityServiceInfo.FEEDBACK_GENERIC);

        for (AccessibilityServiceInfo serviceInfo : serviceList) {
            String name = serviceInfo.getSettingsActivityName();
            if (name.equals(SERVICE_NAME)) {
                return true;
            }
        }

        return false;
    }

    private void showAccessibilityDialog() {
        String message = isAccessibilityGranted() ?
                "This will take you to the accessibility page where you can disable access to Split Screen Helper." :
                "This will take you to the accessibility page. Ensure that you allow access to Split Screen Helper.";

        new AlertDialog.Builder(getActivity())
                .setTitle("Notification")
                .setMessage(message)
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        showAccessibilityPage();
                    }
                })
                .setOnCancelListener(new DialogInterface.OnCancelListener() {
                    @Override
                    public void onCancel(DialogInterface dialog) {
                        showAccessibilityPage();
                    }
                })
                .show();
    }

    private void showAccessibilityPage() {
        Intent intent = new Intent(Settings.ACTION_ACCESSIBILITY_SETTINGS);
        startActivityForResult(intent, 0);
    }
}
