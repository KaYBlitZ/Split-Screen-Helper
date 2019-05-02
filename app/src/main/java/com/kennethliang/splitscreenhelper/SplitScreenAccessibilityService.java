package com.kennethliang.splitscreenhelper;

import android.accessibilityservice.AccessibilityService;
import android.util.Log;
import android.view.accessibility.AccessibilityEvent;

public class SplitScreenAccessibilityService extends AccessibilityService {

    @Override
    public void onAccessibilityEvent(AccessibilityEvent event) {
        Log.d("EVENT", event.toString());

        switch (event.getEventType()) {
            case AccessibilityEvent.TYPE_VIEW_LONG_CLICKED:
                if (event.getContentDescription().toString().equals("Overview")) {
                    performGlobalAction(GLOBAL_ACTION_TOGGLE_SPLIT_SCREEN);
                }
                break;
        }
    }

    @Override
    public void onInterrupt() {
    }
}
