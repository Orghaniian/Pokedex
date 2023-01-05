package com.orghaniian.pokedex.ui.utils

import android.view.View
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import com.orghaniian.pokedex.R
import com.orghaniian.data.model.Type

fun LinearLayout.bindType(type: Type?) {
    if (type != null ) {
        val typeName = resources.getString(type.stringResourceID)
        findViewById<TextView>(R.id.name).text = typeName
        findViewById<ImageView>(R.id.icon).apply {
            setImageDrawable(type.getIconDrawable(context))
            contentDescription = context
                .getString(R.string.pokemon_type_image_content_description, typeName)
        }
    } else {
        visibility = View.INVISIBLE
    }
}
