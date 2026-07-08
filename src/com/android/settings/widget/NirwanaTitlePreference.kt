package com.android.settings.widget

import android.content.Context
import android.util.AttributeSet
import android.widget.TextView
import androidx.preference.Preference
import androidx.preference.PreferenceViewHolder
import com.android.settings.R

class NirwanaTitlePreference(context: Context, attrs: AttributeSet?) : Preference(context, attrs) {

    private val prefix: String
    private val suffix: String
    private val subtitle: String

    init {
        layoutResource = R.layout.nirwana_title_preference
        isSelectable = false

        val fullTitle = title?.toString() ?: ""
        val spaceIndex = fullTitle.indexOf(" ")

        if (spaceIndex != -1) {
            prefix = fullTitle.substring(0, spaceIndex)
            suffix = fullTitle.substring(spaceIndex + 1)
        } else {
            prefix = fullTitle
            suffix = ""
        }

        subtitle = summary?.toString() ?: ""
    }

    override fun onBindViewHolder(holder: PreferenceViewHolder) {
        super.onBindViewHolder(holder)

        val prefixView = holder.findViewById(R.id.nirwana_title_prefix) as? TextView
        val suffixView = holder.findViewById(R.id.nirwana_title_suffix) as? TextView
        val subtitleView = holder.findViewById(R.id.nirwana_subtitle) as? TextView

        prefixView?.text = prefix
        suffixView?.text = suffix
        subtitleView?.text = subtitle
    }
}