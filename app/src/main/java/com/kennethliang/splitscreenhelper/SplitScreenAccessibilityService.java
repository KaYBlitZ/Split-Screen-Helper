package com.kennethliang.splitscreenhelper;

import android.accessibilityservice.AccessibilityService;
import android.util.Log;
import android.view.accessibility.AccessibilityEvent;
import android.widget.Toast;

public class SplitScreenAccessibilityService extends AccessibilityService {

    @Override
    public void onAccessibilityEvent(AccessibilityEvent event) {
        Log.d("EVENT", event.toString());

        switch (event.getEventType()) {
            case AccessibilityEvent.TYPE_VIEW_LONG_CLICKED:
                if (event.getContentDescription().toString().equals("Overview")) {
                    if (!performGlobalAction(GLOBAL_ACTION_TOGGLE_SPLIT_SCREEN)) {
                        Toast.makeText(this, "Unable to enter split screen mode", Toast.LENGTH_SHORT).show();
                    }
                }
                break;
        }
    }

    @Override
    public void onInterrupt() {
    }
}
