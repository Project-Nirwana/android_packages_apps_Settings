package com.android.settings.deviceinfo.aboutphone;

import android.app.WallpaperManager;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.preference.PreferenceScreen;

import com.android.settings.R;
import com.android.settings.core.BasePreferenceController;
import com.android.settingslib.widget.LayoutPreference;

public class NirwanaAboutHeaderController extends BasePreferenceController {

    private static final String TAG = "NirwanaAboutHeader";
    private static final String KEY_HEADER = "nirwana_about_header";

    public NirwanaAboutHeaderController(Context context, String preferenceKey) {
        super(context, preferenceKey);
    }

    @Override
    public int getAvailabilityStatus() {
        // ALWAYS returning AVAILABLE ensures this layout renders on the emulator unconditionally
        return AVAILABLE;
    }

    @Override
    public void displayPreference(PreferenceScreen screen) {
        super.displayPreference(screen);

        LayoutPreference headerPreference = screen.findPreference(KEY_HEADER);
        if (headerPreference != null) {
            bindViews(headerPreference);
        } else {
            Log.e(TAG, "Failed to find LayoutPreference with key: " + KEY_HEADER);
        }
    }

    private void bindViews(LayoutPreference preference) {
        // 1. Hardware & System Stubs for Emulator Bring-up
        setTextStub(preference, R.id.processor_summary, "Snapdragon (Emulator Stub)");
        setTextStub(preference, R.id.camera_summary, "Multi-cam (Stub)");
        setTextStub(preference, R.id.storage_summary, "256 GB (Stub)");
        setTextStub(preference, R.id.screen_summary, "OLED Display (Stub)");
        setTextStub(preference, R.id.device_maintainer_text, "Nirwana Test Team");
        setTextStub(preference, R.id.device_model_name, "Emulator (AOSP)");
        setTextStub(preference, R.id.device_code_name, "generic_x86_64");
        setTextStub(preference, R.id.nirwana_version_text, "Kashmera (Stub)");
        setTextStub(preference, R.id.android_version, "Android 16");
        setTextStub(preference, R.id.android_code_name, "UpsideDownCake");

        // 2. System Wallpaper Extraction
        ImageView clockBackground = preference.findViewById(R.id.clock_card_background);
        if (clockBackground != null) {
            try {
                WallpaperManager wallpaperManager = WallpaperManager.getInstance(mContext);
                Drawable wallpaperDrawable = wallpaperManager.getDrawable();
                if (wallpaperDrawable != null) {
                    clockBackground.setImageDrawable(wallpaperDrawable);
                } else {
                    Log.w(TAG, "WallpaperManager returned null drawable.");
                }
            } catch (SecurityException e) {
                Log.e(TAG, "Failed to fetch wallpaper due to permissions", e);
                // Graceful fallback for strict sandbox environments
                clockBackground.setBackgroundColor(mContext.getColor(android.R.color.darker_gray));
            }
        }
    }

    private void setTextStub(LayoutPreference preference, int viewId, String text) {
        TextView view = preference.findViewById(viewId);
        if (view != null) {
            view.setText(text);
        }
    }
}