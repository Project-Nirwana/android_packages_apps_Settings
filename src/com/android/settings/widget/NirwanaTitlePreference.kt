package com.android.settings.widget

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.widget.TextView
import com.android.settingslib.widget.LayoutPreference
import com.android.settings.R

class NirwanaTitlePreference(context: Context, attrs: AttributeSet?) : LayoutPreference(context, attrs) {

    init {
        // Parse the standard android:title attribute and split it at the first space
        val fullTitle = title?.toString() ?: ""
        val spaceIndex = fullTitle.indexOf(" ")

        val prefix = if (spaceIndex != -1) fullTitle.substring(0, spaceIndex) else fullTitle
        val suffix = if (spaceIndex != -1) fullTitle.substring(spaceIndex + 1) else ""
        val subtitleText = summary?.toString() ?: ""

        // LayoutPreference allows us to find views immediately, no ViewHolder needed!
        val prefixView = findViewById(R.id.nirwana_title_prefix) as? TextView
        val suffixView = findViewById(R.id.nirwana_title_suffix) as? TextView
        val subtitleView = findViewById(R.id.nirwana_subtitle) as? TextView

        prefixView?.text = prefix
        suffixView?.text = suffix

        if (subtitleText.isNotEmpty()) {
            subtitleView?.text = subtitleText
        } else {
            subtitleView?.visibility = View.GONE
        }
    }
}